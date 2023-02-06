package com.javainuse.service.impl;

import com.javainuse.entity.ImageEntity;
import com.javainuse.entity.UserEntity;
import com.javainuse.model.req.ChangePasswordForm;
import com.javainuse.model.req.ChangeUserNameForm;
import com.javainuse.repo.ImageRepo;
import com.javainuse.repo.UserRepo;
import com.javainuse.service.UserSv;
import com.javainuse.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
public class UserSvImpl implements UserSv {
    @Autowired
    UserRepo userRepo;
    @Autowired
    ImageRepo imageRepo;
    @Autowired
    private PasswordEncoder bcryptEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;

    private final Path imagePath = Paths.get("static\\upload");
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

    @Override
    public UserEntity findByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    @Override
    public UserEntity updateName (ChangeUserNameForm form) {
        UserEntity user = userRepo.findById(form.getId()).get();

        if (user == null)
            return new UserEntity();

        user.setName(form.getName());

        return userRepo.save(user);
    }

    @Override
    public UserEntity updatePassword(ChangePasswordForm form) {
        UserEntity user = userRepo.findById(form.getUserId()).get();

        if (user == null)
            return new UserEntity();

        System.out.println("form: " + bcryptEncoder.encode(form.getOldPass()));
        System.out.println("data: " + user.getPassword());

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), form.getOldPass()));
            if (form.getNewPass().equals(form.getRepeat())) {
                user.setPassword(bcryptEncoder.encode(form.getNewPass()));
                return userRepo.save(user);
            }
            return new UserEntity();
        } catch (Exception e) {
            return new UserEntity();
        }
    }

    @Override
    public UserEntity save(Long userId, MultipartFile avatar) throws IOException {
        UserEntity user = userRepo.findById(userId).get();

        if (user == null)
            return new UserEntity();

        String avtName = avatar.getOriginalFilename();
        String[] fileInfo = avtName.split("\\.");
        String type = fileInfo[fileInfo.length -1];

        if (type.equalsIgnoreCase("png") || type.equalsIgnoreCase("jpg") ||type.equalsIgnoreCase("jpeg")) {
            String newAvtName = DateUtils.dateUpFile() + avatar.getOriginalFilename();

            if (user.getAvatar().getId() != 1l) {
                File oldAvt = new File("static\\upload\\" + user.getAvatar().getName());
                oldAvt.delete();
                Long imageId = user.getAvatar().getId();
                user.setAvatar(null);
                userRepo.save(user);
                imageRepo.deleteById(imageId);
            }

            Files.copy(avatar.getInputStream(), imagePath.resolve(newAvtName));
            ImageEntity newAtv = imageRepo.save(new ImageEntity(null, newAvtName, "upload/" + newAvtName));

            user.setAvatar(newAtv);
            user = userRepo.save(user);
            return user;
        }

        return new UserEntity() ;
    }
}
