package com.javainuse.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoryHomePageDTO {
    private Long storyId;
    private String path;
    private String storyName;
    private Long chapterId;
    private Integer chap;
    private String createdDate;
}
