package com.javainuse.service;

import com.javainuse.entity.ChapterEntity;
import com.javainuse.model.res.ReadChapterRes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ChapterSv {
    ReadChapterRes findById(Long chapterId);
    Page<ChapterEntity> findAllByStoryId(Long storyId, Pageable pageable);

    ChapterEntity save(ChapterEntity chapter);
}
