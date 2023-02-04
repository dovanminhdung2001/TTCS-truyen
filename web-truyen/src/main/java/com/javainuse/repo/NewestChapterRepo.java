package com.javainuse.repo;

import com.javainuse.entity.NewestChapEntity;
import com.javainuse.model.dto.StoryHomePageDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface NewestChapterRepo extends JpaRepository<NewestChapEntity, Long> {
    Optional<NewestChapEntity> findByStoryId(Long storyId);

    @Query("select new com.javainuse.model.dto.StoryHomePageDTO(s.id, i.path, s.name, n.chapterId, n.chap, n.createdDate  )  from StoryEntity s, ImageEntity i, NewestChapEntity n " +
            "where s.id = n.storyId and i.id = s.avatar.id " +
            "order by n.id desc ")
    Page<StoryHomePageDTO> getHomePage(Pageable pageable);
}
