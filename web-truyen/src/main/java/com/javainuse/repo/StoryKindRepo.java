package com.javainuse.repo;

import com.javainuse.entity.StoryKindEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoryKindRepo extends JpaRepository<StoryKindEntity,Long> {
    List<StoryKindEntity> findAllByStoryId(Long storyId);

}
