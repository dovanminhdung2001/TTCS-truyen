package com.javainuse.service.impl;

import com.javainuse.entity.ChapterEntity;
import com.javainuse.model.dto.ChapterIdChapNameDTO;
import com.javainuse.model.res.ReadChapterRes;
import com.javainuse.repo.ChapterRepo;
import com.javainuse.service.ChapterSv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChapterSvImpl  implements ChapterSv {
    @Autowired
    private ChapterRepo chapterRepo;

    @Override
    public ReadChapterRes findById(Long chapterId) {
        ChapterEntity chapter = chapterRepo.findById(chapterId).get();
        ReadChapterRes res = new ReadChapterRes();
        Long prev = null;
        Long next = null;
        List<ChapterIdChapNameDTO> list = chapterRepo.getAllByStoryId(chapter.getStoryId());

        for (ChapterIdChapNameDTO x : list) {
            if (x.getChap() == chapter.getChap() - 1) {
                prev = x.getChapterId();
            } else if (x.getChap() == chapter.getChap() + 1) {
                next = x.getChapterId();
                break;
            }
        }

        res.setChapter(chapter);
        res.setMaxChap(chapterRepo.getChapNumOfStory(chapter.getStoryId()));
        res.setChapterIdChapNameDTOList(list);
        res.setPrevChapterId(prev);
        res.setNextChapterId(next);

        return res;
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
