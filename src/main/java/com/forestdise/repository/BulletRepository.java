package com.forestdise.repository;

import com.forestdise.entity.Bullet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BulletRepository extends JpaRepository<Bullet, Long> {
}
