package com.javainuse.service;

import com.javainuse.entity.StoryEntity;
import com.javainuse.model.dto.StoryPersonalDTO;
import com.javainuse.model.req.UpChapterForm;
import com.javainuse.model.req.UpStoryForm;
import com.javainuse.model.res.HomePageRes;
import com.javainuse.model.res.StoryDetailRes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public interface StorySv extends BaseCRUD<StoryEntity> {
    public StoryDetailRes findByStoryId(Long storyId);
    public StoryEntity save(UpStoryForm form);
    public StoryEntity updateAvatar(Long storyId, MultipartFile avatar) throws IOException;
    public List<StoryEntity> findByNameContain(String name);
    public void upChapter(UpChapterForm upChapterForm);

    public Page<StoryPersonalDTO> findAllByUserId(Long userId, Pageable pageable);

    HomePageRes getHomePage(Pageable pageable) throws ParseException;

    HomePageRes findAllByKindId(Long kindId, Pageable pageable) throws ParseException;

    HomePageRes findAllByNameContain(String storyName, Pageable pageable) throws ParseException;
}
