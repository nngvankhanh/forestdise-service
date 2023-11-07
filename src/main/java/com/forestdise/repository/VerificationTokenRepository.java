package com.forestdise.repository;

import com.forestdise.entity.User;
import com.forestdise.entity.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VerificationTokenRepository extends JpaRepository<VerificationToken,Long> {
    VerificationToken findByUser(User user);
    VerificationToken findByToken(String token);
}
