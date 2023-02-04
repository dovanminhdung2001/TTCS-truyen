package com.javainuse.model.res;

import com.javainuse.entity.ChapterEntity;
import com.javainuse.model.dto.ChapterIdChapNameDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReadChapterRes {
    private ChapterEntity chapter;
    private Integer maxChap;
    private List<ChapterIdChapNameDTO> chapterIdChapNameDTOList;
}
