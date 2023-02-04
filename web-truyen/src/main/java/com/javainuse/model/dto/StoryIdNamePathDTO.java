package com.javainuse.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoryIdNamePathDTO {
    private Long storyId;
    private String name;
    private String avatarPath;
}
