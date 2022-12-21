package com.javainuse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_story")
public class StoryEntity extends BaseEntity implements Serializable {
    @Column
    private Long authorId;
    private String name;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "avatarId", referencedColumnName = "id")
    private ImageEntity avatar;
    private Integer status = 0;
    private Integer totalStar = 0;
    private Integer totalVote = 0;
    private String content;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "storyId", referencedColumnName = "id")
    private List<StoryKindEntity> listKinds = new ArrayList<>();

    public StoryEntity(Long authorId, String name, String content) {
        this.authorId = authorId;
        this.name = name;
        this.content = content;
    }
}
