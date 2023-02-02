package com.javainuse.controller;

import com.javainuse.entity.UserEntity;
import com.javainuse.model.req.ChangeUserInforForm;
import com.javainuse.service.impl.UserSvImpl;
import com.javainuse.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@CrossOrigin
@RestController
@RequestMapping("api/v1/user")
public class UserAPI {
    @Autowired
    UserSvImpl userSv;
    @Autowired

    @Bean
    public Pageable pageable() {
        return new PageRequest(0, 20);
    }

    @GetMapping("/list")
    public ResponseEntity<?> list (Pageable pageable) {
        return ResponseUtil.ok(userSv.findAll( pageable));
    }

    @GetMapping("/personal")
    public ResponseEntity<?> getById (@RequestParam Long userId) {
        return ResponseUtil.ok(userSv.findById(userId).get());
    }

    @PostMapping
    public ResponseEntity<?> save (@RequestBody ChangeUserInforForm form) {
        return ResponseUtil.ok(userSv.save(form));
    }

    @PostMapping("/avatar")
    public ResponseEntity<?> changeAvt (@RequestParam Long userId, @RequestParam MultipartFile file) throws IOException {
        UserEntity user = userSv.findById(userId).get();

        if (user == null)
            return ResponseUtil.ok(new UserEntity());

        return ResponseUtil.ok(userSv.save(userId, file));
    }
}
