package com.javainuse.service.impl;

import com.javainuse.entity.ChapterEntity;
import com.javainuse.repo.ChapterRepo;
import com.javainuse.service.ChapterSv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChapterSvImpl  implements ChapterSv {
    @Autowired
    private ChapterRepo chapterRepo;

    @Override
    public Optional<ChapterEntity> findById(Long chapterId) {
        return chapterRepo.findById(chapterId);
    }

    @Override
    public Page<ChapterEntity> findAllByStoryId(Long storyId, Pageable pageable) {
        return null;
    }

    @Override
    public ChapterEntity save(ChapterEntity chapter) {
        return null;
    }
}
