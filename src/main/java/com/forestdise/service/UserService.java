package com.forestdise.service;

import com.forestdise.dto.UserLoginDTO;
import com.forestdise.dto.UserRegisterDTO;
import com.forestdise.entity.User;
import com.forestdise.entity.VerificationToken;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;

@Service
public interface UserService {
    String login(UserLoginDTO userLoginDTO);
    User register(UserRegisterDTO userRegisterDTO);
    User findById (Long id);
    void createVerificationToken(User user);
    boolean checkExpiryDate(VerificationToken verificationToken);
    void verifyToken(VerificationToken verificationToken, HttpServletResponse response) throws Exception;
}
