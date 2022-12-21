package com.javainuse.service;

import com.javainuse.entity.StoryEntity;
import com.javainuse.model.req.UpStoryForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface StorySv extends BaseCRUD<StoryEntity> {
    public StoryEntity save(UpStoryForm form);
    public StoryEntity updateAvatar(Long storyId, MultipartFile avatar) throws IOException;
    public List<StoryEntity> findByNameContain(String name);
    public Page<StoryEntity> findByNameContain(Pageable pageable, String name);

}
