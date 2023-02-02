package com.javainuse.repo;

import com.javainuse.entity.ChapterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ChapterRepo extends JpaRepository<ChapterEntity, Long> {
    @Query("select count(c.storyId) from ChapterEntity c where  c.storyId = :storyId")
    Integer getChapNumOfStory(@Param("storyId") Long storyId);
}
