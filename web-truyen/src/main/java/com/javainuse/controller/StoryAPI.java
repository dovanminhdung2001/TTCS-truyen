package com.javainuse.controller;

import com.javainuse.model.req.UpStoryForm;
import com.javainuse.repo.ImageRepo;
import com.javainuse.service.impl.StorySvImpl;
import com.javainuse.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@CrossOrigin
@RestController
@RequestMapping("api/v1/story")
public class StoryAPI {
    @Autowired
    StorySvImpl storySv;
    @Autowired
    ImageRepo imageRepo;
    @PostMapping
    private ResponseEntity<?> save (@RequestBody UpStoryForm form) {
        return ResponseUtil.ok(storySv.save(form));
    }

    @PostMapping("/avatar")
    private ResponseEntity<?> updateAvatar (@RequestParam Long storyId, @RequestParam MultipartFile file) throws IOException {
        return ResponseUtil.ok(storySv.updateAvatar(storyId, file));
    }

    @PostMapping("/up-chapter")
    private ResponseEntity<?> upChapter (
            @RequestParam Long id,
            @RequestParam Integer chap,
            @RequestParam Long storyId,
            @RequestParam String title,
            @RequestParam MultipartFile content
    ) {
//        storySv.upChapter(id, chap, storyId, title, content);
        return ResponseUtil.ok("");
    }

    @GetMapping("/v2/id")
    private ResponseEntity<?> findById (@RequestParam Long storyId) {
        return ResponseUtil.ok(storySv.findById(storyId));
    }

    @GetMapping("/v2/name")
    private ResponseEntity<?> findByName (@RequestParam Pageable pageable, @RequestParam String name) {
        return ResponseUtil.ok(storySv.findByNameContain(pageable, name));
    }

    @GetMapping("/v2/removeImage")
    private ResponseEntity<?> removeImage(@RequestParam Long imageId) {
        imageRepo.deleteById(imageId);
        return ResponseUtil.ok("ok");
    }
}
