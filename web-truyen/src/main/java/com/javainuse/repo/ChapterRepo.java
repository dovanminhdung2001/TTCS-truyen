package com.javainuse.repo;

import com.javainuse.entity.ChapterEntity;
import com.javainuse.model.dto.ChapterByStoryDTO;
import com.javainuse.util.DateUtils;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChapterRepo extends JpaRepository<ChapterEntity, Long> {
    @Query("select count(c.storyId) from ChapterEntity c where  c.storyId = :storyId")
    Integer getChapNumOfStory(@Param("storyId") Long storyId);

    @Query("select new com.javainuse.model.dto.ChapterByStoryDTO(c.id, c.title, c.chap, c.createdDate) from ChapterEntity c where  c.storyId = :storyId")
    List<ChapterByStoryDTO> getListChapterByStory(@Param("storyId") Long storyId);
}
