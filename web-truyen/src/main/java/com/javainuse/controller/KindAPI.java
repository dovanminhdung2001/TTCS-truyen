package com.javainuse.controller;

import com.javainuse.service.impl.KindSvImpl;
import com.javainuse.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("api/v1/kind")
public class KindAPI {
    @Autowired
    KindSvImpl kindSv;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseUtil.ok(kindSv.findAll());
    }

    @DeleteMapping
    public ResponseEntity<?> deleteById (@RequestParam Long id) {
        kindSv.deleteById(id);
        return ResponseEntity.ok("ok");
    }
}
