package com.studyolle.modules.tag;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class TagService {

    private final TagRepository tagRepository;

    public Tag findOrCreateNew(String tagTitle) {
        // 태그 레파지토리에서 파라미터로 받은 타이틀을 값을 검색한다
        // JPA 기술
        Tag tag = tagRepository.findByTitle(tagTitle);
        // 만일, 위의 메서드로 인해 나온 결과값이 null (태그 값이 처음 사용된 것이라면)
        if (tag == null) {
            // 레파지토리에 저장하고 Tag.class의 title 변수를 빌드한다. Tag.class의 id 값은 현재 불필요하다.
            tag = tagRepository.save(Tag.builder().title(tagTitle).build());
        }
        return tag;
    }

}
