package com.javainuse.model.res;

import com.javainuse.model.dto.StoryHomePageDTO;
import com.javainuse.model.dto.StoryIdNamePathDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HomePageRes {
    private Page<StoryHomePageDTO> storyHomePageDTOPage;
    private List<StoryIdNamePathDTO> storyIdNamePathDTOList;
}
