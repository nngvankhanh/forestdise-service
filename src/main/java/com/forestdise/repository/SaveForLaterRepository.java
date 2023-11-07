package com.forestdise.repository;

import com.forestdise.entity.Cart;
import com.forestdise.entity.SaveForLater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaveForLaterRepository extends JpaRepository<SaveForLater, Long> {
    List<SaveForLater> findSaveForLaterByCart(Cart cart);
}
