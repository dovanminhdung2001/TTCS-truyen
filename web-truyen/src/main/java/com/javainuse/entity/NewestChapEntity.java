package com.javainuse.entity;

import com.javainuse.util.DateUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@NoArgsConstructor
@Table(name = "tbl_newest_chap")
public class NewestChapEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long storyId;
    private Long chapterId;
    private Integer chap;
    private String createdDate;

    public NewestChapEntity(Long storyId, ChapterEntity chapter) {
        this.storyId = storyId;
        this.chapterId = chapter.getId();
        this.chap = chapter.getChap();
        this.createdDate = DateUtils.sdtf.format(chapter.getCreatedDate());
    }
}
