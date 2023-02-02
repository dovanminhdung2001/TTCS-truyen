package com.javainuse.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_chapter")
@EntityListeners(AuditingEntityListener.class)
public class ChapterEntity extends BaseEntity implements Serializable {
    private Integer chap;
    private Long storyId;
    private String title;
    private String content;
    private Integer totalLike;
}
