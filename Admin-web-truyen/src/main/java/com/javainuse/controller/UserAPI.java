package com.javainuse.controller;

import com.javainuse.service.impl.UserSvImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("api/v1/user")
public class UserAPI {
    @Autowired
    UserSvImpl userSv;
    @GetMapping("/list")
    public ResponseEntity<?> list (Pageable pageable) {
        return ResponseEntity.ok(userSv.findAll(pageable));
    }
}
