package com.studyolle.modules.study.form;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class StudyForm {

    public static final String VALID_PATH_PATTERN = "^[ㄱ-ㅎ가-힣a-z0-9_-]{2,20}$";

    @NotBlank
    @Length(min = 2, max = 20)
    @Pattern(regexp = VALID_PATH_PATTERN)
    // 사용자 스터디 커스텀 URL
    private String path;

    @NotBlank
    @Length(max = 50)
    // 스터디 제목
    private String title;

    @NotBlank
    @Length(max = 100)
    // 스터디 짧은 설명
    private String shortDescription;

    @NotBlank
    // 스터디 상세 설명
    private String fullDescription;

}
