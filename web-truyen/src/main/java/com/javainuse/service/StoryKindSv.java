package com.javainuse.service;

import com.javainuse.entity.StoryKindEntity;

import java.util.List;

public interface StoryKindSv {
    List<StoryKindEntity> findAllByStoryId (Long storyId) ;
}
