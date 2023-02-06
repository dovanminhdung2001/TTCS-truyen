package com.javainuse.repo;

import com.javainuse.entity.StoryEntity;
import com.javainuse.model.dto.StoryHomePageDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StoryRepo extends JpaRepository<StoryEntity, Long> {
    List<StoryEntity> findAllByAuthorId(Long authorId);
    List<StoryEntity> findAllByNameContainingIgnoreCase(String name);
    Page<StoryEntity> findAllByNameContainingIgnoreCase(Pageable pageable, String name);
    Page<StoryEntity> findAllByAuthorId(Pageable pageable, Long userId);
    @Query("select new com.javainuse.model.dto.StoryHomePageDTO " +
            "(s.id, i.path, s.name, n.chapterId, n.chap, n.createdDate  ) " +
            "from StoryEntity s, ImageEntity i, NewestChapEntity n, StoryKindEntity k " +
            "where s.id = n.storyId and i.id = s.avatar.id and k.storyId = s.id and k.kind.id = :kindId "  +
            "order by n.id desc ")
    Page<StoryHomePageDTO> filterByKindId(@Param("kindId") Long kindId, Pageable pageable);
    @Query("select new com.javainuse.model.dto.StoryHomePageDTO(s.id, i.path, s.name, n.chapterId, n.chap, n.createdDate  )  " +
            "from StoryEntity s, ImageEntity i, NewestChapEntity n " +
            "where s.id = n.storyId and i.id = s.avatar.id and s.name like %:storyName% " +
            "order by n.id desc ")
    Page<StoryHomePageDTO> filterByNameContain(@Param("storyName") String storyName, Pageable pageable);


    @Query("select new com.javainuse.model.dto.StoryHomePageDTO " +
            "(s.id, i.path, s.name, n.chapterId, n.chap, n.createdDate  ) " +
            "from StoryEntity s, ImageEntity i, NewestChapEntity n, StoryKindEntity k " +
            "where s.id = n.storyId and i.id = s.avatar.id and k.storyId = s.id "  +
            "order by n.id desc ")
    List<StoryHomePageDTO> testQuery();

}
