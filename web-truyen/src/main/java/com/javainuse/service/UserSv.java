package com.javainuse.service;

import com.javainuse.entity.UserEntity;
import com.javainuse.model.req.ChangeUserInforForm;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

public interface UserSv extends BaseCRUD<UserEntity> {
    UserEntity findByEmail(String email);

    UserEntity save(ChangeUserInforForm form);

    UserEntity save(Long userId, MultipartFile avatar) throws IOException;
}
