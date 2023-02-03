package com.javainuse.service;

import com.javainuse.entity.ChapterEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ChapterSv {
    Optional<ChapterEntity> findById(Long chapterId);
    Page<ChapterEntity> findAllByStoryId(Long storyId, Pageable pageable);

    ChapterEntity save(ChapterEntity chapter);
}
