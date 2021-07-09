package com.basic.feature_crud.service;

import com.basic.feature_crud.repository.AuthorRepository;
import com.basic.feature_crud.repository.BookRepository;
import com.basic.feature_crud.repository.LendRepository;
import com.basic.feature_crud.repository.MemeberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
// lombok이 초기화 되지 않은 필드를 생성합니다. 이를 통해서 의존성 주입(DI)를 할 수 있습니다.
public class LibraryService {

    private final AuthorRepository authorRepository;
    private final MemeberRepository memeberRepository;
    private final LendRepository lendRepository;
    private final BookRepository bookRepository;

}

