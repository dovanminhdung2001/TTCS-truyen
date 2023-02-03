package com.javainuse.model.dto;

import com.javainuse.util.DateUtils;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class ChapterByStoryDTO {
    private Long id;
    private String title;
    private Integer chap;
    private String createDate;

    public ChapterByStoryDTO(Long id, String title, Integer chap, Date createDate) {
        this.id = id;
        this.title = title;
        this.chap = chap;
        this.createDate = DateUtils.sdtf.format(createDate);
    }
}
