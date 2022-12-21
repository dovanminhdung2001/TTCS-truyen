package com.javainuse.service.impl;

import com.javainuse.entity.*;
import com.javainuse.model.req.UpStoryForm;
import com.javainuse.repo.ImageRepo;
import com.javainuse.repo.KindRepo;
import com.javainuse.repo.StoryKindRepo;
import com.javainuse.repo.StoryRepo;
import com.javainuse.service.StorySv;
import com.javainuse.util.DateUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StorySvImpl implements StorySv {
    @Autowired
    StoryRepo storyRepo;
    @Autowired
    KindRepo kindRepo;
    @Autowired
    StoryKindRepo storyKindRepo;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    ImageRepo imageRepo;
    @Override
    public Optional<StoryEntity> findById(Long id) {
        return storyRepo.findById(id);
    }

    @Override
    public Page<StoryEntity> findAll(Pageable pageable) {
        return storyRepo.findAll(pageable);
    }

    @Override
    public List<StoryEntity> findAll() {
        return storyRepo.findAll();
    }

    @Override
    public StoryEntity save(StoryEntity storyEntity) {
        return storyRepo.save(storyEntity);
    }

    @Override
    public StoryEntity save(UpStoryForm form) {
        StoryEntity result = new StoryEntity();

        if (form.getId() != null) {   // Trường hợp update truyện
            result = storyRepo.findById(form.getId()).get();
            if (result == null) return new StoryEntity();
            List<StoryKindEntity> oldKinds = storyKindRepo.findAllByStoryId(form.getId()); // Xóa ds thể loại cũ
            for (StoryKindEntity x : oldKinds) {
                x.setKind(null);
                x.setStoryId(null);
                storyKindRepo.delete(x);
            }
        } else {                                                    // Nếu up truyện mới
            result.setAvatar(imageRepo.findById(1l).get());         // set default avt
        }
        List<StoryKindEntity> newKinds = new ArrayList<>();         // Lưu ds thể loại mới
        List<KindEntity> allKinds = kindRepo.findAll();

        modelMapper.map(form, result);                          // map thông tin sang
        result = storyRepo.save(result);                        // lưu truyện
        for (KindEntity x : form.getListKind()) {
            for (KindEntity y : allKinds) {
                if (x.getId() == y.getId()) {
                    newKinds.add(new StoryKindEntity(null, result.getId(), y));
                    storyKindRepo.save(new StoryKindEntity(null, result.getId(), y));
                    break;
                }
            }
        }
        result.setListKinds(newKinds);
        return result;
    }

    @Override
    public StoryEntity updateAvatar(Long storyId, MultipartFile avatar) throws IOException {
        Path imagePath = Paths.get("static\\story");

        StoryEntity story = storyRepo.findById(storyId).get();

        if (story == null)
            return new StoryEntity();

        String avtName = avatar.getOriginalFilename();
        String[] fileInfo = avtName.split("\\.");
        String type = fileInfo[fileInfo.length -1];

        if (type.equalsIgnoreCase("png") || type.equalsIgnoreCase("jpg") ||type.equalsIgnoreCase("jpeg")) {
            String newAvtName = DateUtils.dateUpFile() + avatar.getOriginalFilename();

            if (story.getAvatar().getId() != 1l) {
                File oldAvt = new File(story.getAvatar().getPath());
                oldAvt.delete();
            }

            Files.copy(avatar.getInputStream(), imagePath.resolve(newAvtName));
            ImageEntity newAtv = imageRepo.save(new ImageEntity(null, newAvtName, "/upload/" + newAvtName));

            story.setAvatar(newAtv);
            story = storyRepo.save(story);
            return story;
        }

        return new StoryEntity() ;
    }

    @Override
    public List<StoryEntity> findByNameContain(String name) {
        return storyRepo.findAllByNameContainingIgnoreCase(name);
    }

    @Override
    public Page<StoryEntity> findByNameContain(Pageable pageable, String name) {
        return storyRepo.findAllByNameContainingIgnoreCase(pageable, name);
    }
}
