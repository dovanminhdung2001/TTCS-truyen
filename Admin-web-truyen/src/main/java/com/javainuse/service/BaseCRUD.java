package com.javainuse.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.List;

public interface BaseCRUD<T>{
    Optional<T> findById (Long id);
    Page<T> findAll(Pageable pageable);
    List<T> findAll();
    T save (T t);
}
