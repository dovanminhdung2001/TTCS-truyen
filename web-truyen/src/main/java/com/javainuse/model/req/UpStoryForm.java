package com.javainuse.model.req;

import com.javainuse.entity.KindEntity;
import lombok.Data;

import java.util.List;

@Data
public class UpStoryForm {
    private Long id;
    private Long authorId;
    private String name;
    private String content;
    private List<KindEntity> listKind;
}
