package com.javainuse.service.impl;

import com.javainuse.entity.KindEntity;
import com.javainuse.repo.KindRepo;
import com.javainuse.service.KindSv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KindSvImpl implements KindSv {
    @Autowired
    KindRepo kindRepo;
    @Override
    public Optional findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Page findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List findAll() {
        return kindRepo.findAll();
    }

    @Override
    public KindEntity save(KindEntity kindEntity) {
        return null;
    }

    @Override
    public void deleteById(Long id) {
        kindRepo.deleteById(id);
    }
}
