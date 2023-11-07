package com.forestdise.service.impl;

import com.forestdise.configuration.JwtTokenUtil;
import com.forestdise.constraint.Role;
import com.forestdise.dto.UserLoginDTO;
import com.forestdise.dto.UserRegisterDTO;
import com.forestdise.entity.User;
import com.forestdise.entity.VerificationToken;
import com.forestdise.exception.RedirectException;
import com.forestdise.repository.UserRepository;
import com.forestdise.repository.VerificationTokenRepository;
import com.forestdise.security.JwtUserDetailsService;
import com.forestdise.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;
    @Autowired
    private VerificationTokenRepository tokenRepository;


    @Override
    public String login(UserLoginDTO userLoginDto) {
        User user = userRepository.findByEmail(userLoginDto.getEmail());

        if(user == null){
            throw new UsernameNotFoundException("User not found");
        } else if(!user.isEnabled()){
            throw new UsernameNotFoundException("User has not been confirmed");
        }
        if(!passwordEncoder.matches(userLoginDto.getPassword(), user.getPassword())){
            throw new AuthenticationServiceException("Wrong password");
        }
        return jwtTokenUtil.generateToken(user);
    }

    @Override
    @Transactional
    public User register(UserRegisterDTO userRegisterDto) {
        jwtUserDetailsService.loadUserByUsername(userRegisterDto.getEmail());
        String password = userRegisterDto.getPassword();
        User user = new User();
        user.setClientName(userRegisterDto.getClientName());
        user.setEmail(userRegisterDto.getEmail());
        user.setPassword(passwordEncoder.encode(password));
        user.setRole("ROLE_".concat(Role.USER.toString()));
        userRepository.save(user);
        return user;
    }

    @Override
    public User findById(Long id) {
        User user = userRepository.findById(id).orElse(null);
        return user;
    }

    @Override
    public void createVerificationToken(User user) {
        Date expireDate = calculateExpiryDate(60*24);
        String token = UUID.randomUUID().toString();
        VerificationToken userToken = new VerificationToken();
        userToken.setUser(user);
        userToken.setToken(token);
        userToken.setExpiryDate(expireDate);
        tokenRepository.save(userToken);
    }

    @Override
    public boolean checkExpiryDate(VerificationToken verificationToken){
        Calendar cal = Calendar.getInstance();
        if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
            return true;
        } else{
            return false;
        }
    }

    @Override
    public void verifyToken(VerificationToken verificationToken, HttpServletResponse response) throws Exception{

        if(verificationToken != null ){
            boolean isExpired = checkExpiryDate(verificationToken);
            if(!isExpired){
                User user = verificationToken.getUser();
                user.setEnabled(true);
                userRepository.save(user);
            } else {
                throw new RedirectException("Expired token", "http://localhost:3000/confirm?status=error");
            }
        } else {
            throw new RedirectException("Invalid token", "http://localhost:3000/confirm?status=error");
        }
    }

    public Date calculateExpiryDate(int expiryTimeInMinutes) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Timestamp(cal.getTime().getTime()));
        cal.add(Calendar.MINUTE, expiryTimeInMinutes);
        return new Date(cal.getTime().getTime());
    }
}