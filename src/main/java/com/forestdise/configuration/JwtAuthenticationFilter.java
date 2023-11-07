package com.forestdise.configuration;

import com.forestdise.entity.Seller;
import com.forestdise.entity.User;
import com.forestdise.repository.SellerRepository;
import com.forestdise.repository.UserRepository;
import com.forestdise.security.JwtUserDetailsService;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final String AUTHORIZATION = "Authorization";

    private static final String BEARER = "Bearer ";


    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SellerRepository sellerRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        final String requestTokenHeader = request.getHeader(AUTHORIZATION);

        String id = null;
        String jwtToken = null;
        // JWT Token is in the form "Bearer token". Remove Bearer word and get only the Token
        if (requestTokenHeader != null && requestTokenHeader.startsWith(BEARER)) {
            jwtToken = requestTokenHeader.substring(7);
            try {
                if(jwtTokenUtil.isTokenExpired(jwtToken)){
                    throw new ExpiredJwtException(null, null, "Jwt expired");
                }
                id = jwtTokenUtil.getIdFromToken(jwtToken);

            } catch (IllegalArgumentException e) {
                System.out.println("Unable to get JWT Token");
            } catch (ExpiredJwtException e) {
                System.out.println("JWT Token has expired");
            }
        } else {
            logger.warn("JWT Token does not begin with Bearer String");
        }

        //Once we get the token validate it.
        if (id != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            User user = userRepository.findById(Long.parseLong(id)).orElse(null);
            Seller seller = sellerRepository.findById(Long.parseLong(id)).orElse(null);
            if(user == null){
                if(seller == null){
                    throw new UsernameNotFoundException("User not found!");
                }
            }
            //filterPermissionAndPath(request.getRequestURL(), request.getMethod(), user.getRoles());

            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken;
            if(user == null){
                usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        seller,
                        null,
                        Collections.singleton(new SimpleGrantedAuthority(seller.getRole().toString())));
            } else{
                usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        user,
                        null,
                        Collections.singleton(new SimpleGrantedAuthority(user.getRole().toString())));
            }
            usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            // After setting the Authentication in the context, we specify
            // that the current user is authenticated. So it passes the Spring Security Configurations successfully.
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }
        chain.doFilter(request, response);
    }

}

