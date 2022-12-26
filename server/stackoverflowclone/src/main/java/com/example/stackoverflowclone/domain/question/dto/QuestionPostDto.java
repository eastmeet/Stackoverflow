package com.example.stackoverflowclone.domain.question.dto;

import com.example.stackoverflowclone.domain.tag.entity.Tag;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Map;

@Data
@Builder
public class QuestionPostDto {
    private String email; // 리펙토리 포인트 (시큐리티 연결시)
    @NotBlank(message = "Null값과 공백을 허용할 수 없습니다.")
    private String questionTitle;
    private String questionProblemBody;
    private String questionTryOrExpectingBody;
    private List<QuestionPostTagDto> tag;
}
