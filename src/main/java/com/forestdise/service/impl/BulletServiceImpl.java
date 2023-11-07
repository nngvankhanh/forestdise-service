package com.forestdise.service.impl;

import com.forestdise.entity.Bullet;
import com.forestdise.entity.Product;
import com.forestdise.repository.BulletRepository;
import com.forestdise.repository.ProductRepository;
import com.forestdise.service.BulletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class BulletServiceImpl implements BulletService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private BulletRepository bulletRepository;
    @Override
    public List<Bullet> createBullet(List<String> bulletlist, Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(()-> new EntityNotFoundException("Product is not found"));
        if(product != null){
            List<Bullet> bulletList = new ArrayList<>();
            for(String element :bulletlist ){
                Bullet bullet = new Bullet();
                bullet.setName(element);
                bullet.setProduct(product);
                bulletRepository.save(bullet);
                bulletList.add(bullet);
            }
        }
        return product.getBulletList();
    }
}
