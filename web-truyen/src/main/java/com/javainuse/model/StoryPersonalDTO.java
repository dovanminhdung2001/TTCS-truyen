package com.javainuse.model;

import com.javainuse.entity.StoryEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StoryPersonalDTO {
    StoryEntity  story;
    Integer chapNum = 0;
    Integer totalLike = 0;

    public StoryPersonalDTO(StoryEntity x, Integer chapNumOfStory) {
        story = x;
        chapNum = chapNumOfStory;
    }
}
