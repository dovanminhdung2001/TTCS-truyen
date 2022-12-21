package com.javainuse.service;

import com.javainuse.entity.KindEntity;

public interface KindSv extends BaseCRUD<KindEntity>{
    void deleteById (Long id);
}
