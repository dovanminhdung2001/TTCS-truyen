package com.javainuse.controller;

import com.javainuse.entity.UserEntity;
import com.javainuse.model.req.ChangePasswordForm;
import com.javainuse.model.req.ChangeUserNameForm;
import com.javainuse.service.impl.UserSvImpl;
import com.javainuse.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/name")
    public ResponseEntity<?> updateName (@RequestBody ChangeUserNameForm form) {
        return ResponseUtil.ok(userSv.updateName(form));
    }

    @PostMapping("/password")
    public ResponseEntity<?> updatePass (@RequestBody ChangePasswordForm form) {
        return ResponseUtil.ok(userSv.updatePassword(form));
    }

    @PostMapping("/avatar")
    public ResponseEntity<?> changeAvt (@RequestParam Long userId, @RequestParam MultipartFile file) throws IOException {
        UserEntity user = userSv.findById(userId).get();

        if (user == null)
            return ResponseUtil.ok(new UserEntity());

        return ResponseUtil.ok(userSv.save(userId, file));
    }
}
