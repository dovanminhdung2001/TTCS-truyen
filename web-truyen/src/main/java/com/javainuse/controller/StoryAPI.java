package com.javainuse.controller;

import com.javainuse.model.req.UpChapterForm;
import com.javainuse.model.req.UpStoryForm;
import com.javainuse.repo.ImageRepo;
import com.javainuse.repo.StoryRepo;
import com.javainuse.service.impl.StorySvImpl;
import com.javainuse.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;

@CrossOrigin
@RestController
@RequestMapping("api/v1/story")
public class StoryAPI {
    @Autowired
    StorySvImpl storySv;
    @Autowired
    ImageRepo imageRepo;
    @Autowired
    StoryRepo storyRepo;
    @PostMapping
    private ResponseEntity<?> save (@RequestBody UpStoryForm form) {
        return ResponseUtil.ok(storySv.save(form));
    }

    @PostMapping("/avatar")
    private ResponseEntity<?> updateAvatar (@RequestParam Long storyId, @RequestParam MultipartFile file) throws IOException {
        return ResponseUtil.ok(storySv.updateAvatar(storyId, file));
    }

    @PostMapping("/up-chapter")
    private ResponseEntity<?> upChapter (@RequestBody UpChapterForm upChapterForm) {
        storySv.upChapter(upChapterForm);
        return ResponseUtil.ok("");
    }

    @GetMapping("/v2/id")
    private ResponseEntity<?> findById (@RequestParam Long storyId) {
        return ResponseUtil.ok(storySv.findByStoryId(storyId));
    }

    @GetMapping("/v2/removeImage")
    private ResponseEntity<?> removeImage(@RequestParam Long imageId) {
        imageRepo.deleteById(imageId);
        return ResponseUtil.ok("ok");
    }

    @GetMapping("/personal")
    private ResponseEntity<?> findAllByUserId(@RequestParam Long userId, Pageable pageable) {
        return ResponseUtil.ok(storySv.findAllByUserId(userId, pageable));
    }

    @GetMapping("/v2/home")
    private ResponseEntity<?> getHomePage(Pageable pageable) throws ParseException {
        return ResponseUtil.ok(storySv.getHomePage(pageable));
    }

    @GetMapping("/v2/kind")
    private ResponseEntity<?> findByKindId(@RequestParam Long kindId, Pageable pageable) throws ParseException {
        return ResponseUtil.ok(storySv.findAllByKindId(kindId, pageable));
    }

    @GetMapping("/v2/name")
    private ResponseEntity<?> findByNameContain(@RequestParam String storyName, Pageable pageable) throws ParseException {
        return ResponseUtil.ok(storySv.findAllByNameContain(storyName, pageable));
    }

    @GetMapping("/v2/check")
    private ResponseEntity<?> check() {
        return ResponseUtil.ok(storyRepo.testQuery());
    }
}