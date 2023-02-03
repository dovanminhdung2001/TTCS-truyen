package com.javainuse.controller;

import com.javainuse.service.impl.ChapterSvImpl;
import com.javainuse.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("api/v1/chapter")
public class ChapterApi {
    @Autowired
    private ChapterSvImpl chapterSv;
    @GetMapping("/v2/id")
    public ResponseEntity<?> getById (@RequestParam Long chapterId) {
        return ResponseUtil.ok(chapterSv.findById(chapterId));
    }
}
