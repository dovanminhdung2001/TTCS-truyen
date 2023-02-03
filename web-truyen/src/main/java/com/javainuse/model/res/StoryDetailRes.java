package com.javainuse.model.res;

import com.javainuse.entity.StoryEntity;
import com.javainuse.model.dto.ChapterByStoryDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class StoryDetailRes {
    private StoryEntity story;
    private List<ChapterByStoryDTO> chapterByStoryDTOList;

    public StoryDetailRes(StoryEntity story) {
        this.story = story;
    }
}
