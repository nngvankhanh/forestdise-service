package com.forestdise.repository;

import com.forestdise.entity.Cart;
import com.forestdise.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findCartByUser(User user);
    Cart findByUserId(Long userId);
}
