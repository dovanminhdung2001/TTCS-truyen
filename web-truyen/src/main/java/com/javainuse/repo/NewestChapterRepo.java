package com.javainuse.repo;

import com.javainuse.entity.NewestChapEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NewestChapterRepo extends JpaRepository<NewestChapEntity, Long> {
    Optional<NewestChapEntity> findByStoryId(Long storyId);
}
