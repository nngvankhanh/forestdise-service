package com.forestdise.controller.back_office;

import com.forestdise.converter.UserConverter;
import com.forestdise.dto.UserDTO;
import com.forestdise.entity.User;
import com.forestdise.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "https://forestdise.vercel.app")
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserConverter userConverter;

    @GetMapping("/{user_id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable("user_id") Long userId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        UserDTO userDTO = userConverter.convertEntityToDTO(user);
        return ResponseEntity.ok(userDTO);
    }
}
