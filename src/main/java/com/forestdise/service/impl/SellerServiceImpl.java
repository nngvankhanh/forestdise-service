package com.forestdise.service.impl;

import com.forestdise.configuration.JwtTokenUtil;
import com.forestdise.constraint.Role;
import com.forestdise.dto.SellerLoginDTO;
import com.forestdise.dto.SellerRegisterDTO;
import com.forestdise.entity.Seller;
import com.forestdise.repository.SellerRepository;
import com.forestdise.security.JwtUserDetailsService;
import com.forestdise.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    private SellerRepository sellerRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;


    @Override
    public String login(SellerLoginDTO sellerLoginDto) {
        Seller seller = sellerRepository.findByEmail(sellerLoginDto.getEmail());

        if(seller == null){
            throw new UsernameNotFoundException("User not found");
        }
        if(!passwordEncoder.matches(sellerLoginDto.getPassword(), seller.getPassword())){
            throw new AuthenticationServiceException("Wrong password");
        }
        return jwtTokenUtil.generateSellerToken(seller);
    }

    @Override
    @Transactional
    public Seller register(SellerRegisterDTO sellerRegisterDto) {
        jwtUserDetailsService.loadSellerByEmail(sellerRegisterDto.getEmail());
        String password = sellerRegisterDto.getPassword();
        Seller seller = new Seller();
        seller.setSellerName(sellerRegisterDto.getSellerName());
        seller.setEmail(sellerRegisterDto.getEmail());
        seller.setPassword(passwordEncoder.encode(password));
        seller.setRole("ROLE_".concat(Role.SELLER.toString()));
        sellerRepository.save(seller);
        return seller;
    }

    @Override
    public Seller findById(Long id) {
        Seller seller = sellerRepository.findById(id).orElse(null);
        return seller;
    }
}

