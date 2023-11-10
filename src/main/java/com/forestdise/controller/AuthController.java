package com.forestdise.controller;

import com.forestdise.dto.SellerLoginDTO;
import com.forestdise.dto.SellerRegisterDTO;
import com.forestdise.dto.UserLoginDTO;
import com.forestdise.dto.UserRegisterDTO;
import com.forestdise.entity.Seller;
import com.forestdise.entity.User;
import com.forestdise.entity.VerificationToken;
import com.forestdise.repository.UserRepository;
import com.forestdise.repository.VerificationTokenRepository;
import com.forestdise.service.EmailService;
import com.forestdise.service.SellerService;
import com.forestdise.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin(origins = "https://forestdise.vercel.app")
@RequestMapping("/api")
public class AuthController {
    @Autowired
    private UserService userService;
    @Autowired
    private SellerService sellerService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private VerificationTokenRepository verificationTokenRepository;
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginDTO userLoginDto) {
        String token = userService.login(userLoginDto);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserRegisterDTO userRegisterDto){
        User user = userService.register(userRegisterDto);
        userService.createVerificationToken(user);
        VerificationToken verificationToken = verificationTokenRepository.findByUser(user);
        String token = verificationToken.getToken();
        emailService.sendConfirmEmail(user, token);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/register/confirmation")
    public void verifyUser(@RequestParam(name = "token")String token, HttpServletResponse response) throws Exception{
            VerificationToken verificationToken = verificationTokenRepository.findByToken(token);
            userService.verifyToken(verificationToken, response);
            response.sendRedirect("https://forestdise.vercel.app/confirm?status=success");
    }

    @PostMapping("/login/seller")
    public ResponseEntity<?> loginSeller(@RequestBody SellerLoginDTO sellerLoginDto) {
        String token = sellerService.login(sellerLoginDto);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/register/seller")
    public ResponseEntity<?> registerSeller(@RequestBody SellerRegisterDTO sellerRegisterDto){
        Seller seller = sellerService.register(sellerRegisterDto);
        return ResponseEntity.ok(seller);
    }
}
