package com.javainuse.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChapterIdChapNameDTO {
    private Long chapterId;
    private Integer chap;
    private String name;
}
