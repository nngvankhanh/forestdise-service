package com.forestdise.service;

import com.forestdise.entity.User;

public interface EmailService {
    void sendConfirmEmail(User user, String token);
    void sendPaymentEmail(User user);
}
