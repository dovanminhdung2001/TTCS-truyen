package com.javainuse.repo;

import com.javainuse.entity.StoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StoryRepo extends JpaRepository<StoryEntity, Long> {
     List<StoryEntity> findAllByAuthorId(Long authorId);
     List<StoryEntity> findAllByNameContainingIgnoreCase(String name);
     Page<StoryEntity> findAllByNameContainingIgnoreCase(Pageable pageable, String name);
     Page<StoryEntity> findAllByAuthorId(Pageable pageable, Long userId);
}
