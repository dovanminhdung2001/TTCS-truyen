package com.javainuse.service.impl;

import com.javainuse.entity.UserEntity;
import com.javainuse.repo.UserRepo;
import com.javainuse.service.UserSv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserSvImpl implements UserSv {
    @Autowired
    UserRepo userRepo;
    @Override
    public Optional<UserEntity> findById(Long id) {
        return userRepo.findById(id);
    }

    @Override
    public Page<UserEntity> findAll(Pageable pageable) {
        return userRepo.findAll(pageable);
    }

    @Override
    public List<UserEntity> findAll() {
        return userRepo.findAll();
    }

    @Override
    public UserEntity save(UserEntity userEntity) {
        return userRepo.save(userEntity);
    }
}
