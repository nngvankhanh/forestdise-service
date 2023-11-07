package com.forestdise.service;

import com.forestdise.entity.Bullet;

import java.util.List;

public interface BulletService {
    List<Bullet> createBullet(List<String> bulletlist, Long productId);

}
