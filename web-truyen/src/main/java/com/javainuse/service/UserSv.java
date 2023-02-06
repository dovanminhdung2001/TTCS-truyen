package com.javainuse.service;

import com.javainuse.entity.UserEntity;
import com.javainuse.model.req.ChangePasswordForm;
import com.javainuse.model.req.ChangeUserNameForm;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UserSv extends BaseCRUD<UserEntity> {
    UserEntity findByEmail(String email);

    UserEntity updateName (ChangeUserNameForm form);
    UserEntity updatePassword (ChangePasswordForm form);

    UserEntity save(Long userId, MultipartFile avatar) throws IOException;
}
