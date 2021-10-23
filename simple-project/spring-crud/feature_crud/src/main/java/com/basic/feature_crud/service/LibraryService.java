package com.basic.feature_crud.service;

import com.basic.feature_crud.model.Author;
import com.basic.feature_crud.model.Book;
import com.basic.feature_crud.repository.AuthorRepository;
import com.basic.feature_crud.repository.BookRepository;
import com.basic.feature_crud.repository.LendRepository;
import com.basic.feature_crud.repository.MemeberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
// lombok이 초기화 되지 않은 필드를 생성합니다. 이를 통해서 의존성 주입(DI)를 할 수 있습니다.
public class LibraryService {
    private final AuthorRepository authorRepository;
    private final MemeberRepository memeberRepository;
    private final LendRepository lendRepository;
    private final BookRepository bookRepository;

    public Book readBook(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            return book.get();
        }

        throw new EntityNotFoundException(
                "Cant find any book under given ID");
    }

    public List<Book> readBooks() {
        return bookRepository.findAll();
    }

    public Book readBook(String isbn) {
        Optional<Book> book = bookRepository.findByIsbn(isbn);
        if (book.isPresent()) {
            return book.get();
        }

        throw new EntityNotFoundException(
                "Cant find any book under given ISBN");
    }
}