package com.javainuse.model.dto;

import com.javainuse.util.DateUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.ParseException;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoryHomePageDTO {
    private Long storyId;
    private String path;
    private String storyName;
    private Long chapterId;
    private Integer chap;
    private String createdDate;

    public void setCreated() throws ParseException {
        Date cre = DateUtils.sdtf.parse(createdDate);
        createdDate = formatCreatedDate(cre);
    }

    private String formatCreatedDate(Date  createdDate) {
        Date now = DateUtils.today();
        Long day = 24 * 60 * 60 * 1000L;
        Long hour = 60 * 60 * 1000L;
        Long minute = 60 * 1000L;
        Long diff = now.getTime() - createdDate.getTime() - 12 * hour;

        if (diff >= 20 * day)
            return DateUtils.sdf.format(createdDate);

        if (diff >= day)
            return diff / day + " ngày trước";

        if (diff >= hour)
            return diff / hour + " giờ trước";

        return diff / minute + " phút trước";
    }
}
