package com.javainuse.model.dto;

import com.javainuse.util.DateUtils;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class ChapterByStoryDTO {
    private Long id;
    private String title;
    private Integer chap;
    private String createDate;

    public ChapterByStoryDTO(Long id, String title, Integer chap, Date createDate) {
        this.id = id;
        this.title = title;
        this.chap = chap;
        this.createDate = formatCreatedDate(createDate);
    }

    private String formatCreatedDate(Date  createdDate) {
        Date now = DateUtils.today();
        Long day = 24 * 60 * 60 * 1000L;
        Long hour = 60 * 60 * 1000L;
        Long minute = 60 * 1000L;
        Long diff = now.getTime() - createdDate.getTime() ;

        if (diff >= 20 * day)
            return DateUtils.sdf.format(createdDate);

        if (diff >= day)
            return diff / day + " ngày trước";

        if (diff >= hour)
            return diff / hour + " giờ trước";

        return diff / minute + " phút trước";
    }
}
