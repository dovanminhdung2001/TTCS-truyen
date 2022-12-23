package com.javainuse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_kind_of_story")
@EntityListeners(AuditingEntityListener.class)
public class StoryKindEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private Long storyId;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "kind_id", referencedColumnName = "id")
    private KindEntity kind;
}
