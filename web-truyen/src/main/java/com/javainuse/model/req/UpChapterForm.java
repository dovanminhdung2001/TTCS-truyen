package com.javainuse.model.req;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpChapterForm {
    private Long id;
    private Long storyId;
    private String title;
    private String content;
}
