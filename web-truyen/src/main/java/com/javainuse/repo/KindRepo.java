package com.javainuse.repo;

import com.javainuse.entity.KindEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KindRepo extends JpaRepository<KindEntity, Long> {
}
