package com.example.restapi.service;


import com.example.restapi.dto.AuthorCreationRequest;
import com.example.restapi.dto.BookCreationRequest;
import com.example.restapi.dto.BookLendRequest;
import com.example.restapi.dto.MemberCreationRequest;

import com.example.restapi.entity.*;

import com.example.restapi.repository.AuthorRepository;
import com.example.restapi.repository.BookRepository;
import com.example.restapi.repository.LendRepository;
import com.example.restapi.repository.MemberRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor // final 필드가 포함된 생성자를 생성 // 의존성 주입을 하기 위해서
public class LibraryService {

    private final AuthorRepository authorRepository;
    private final MemberRepository memberRepository;
    private final LendRepository lendRepository;
    private final BookRepository bookRepository;

    // Api 설계
    // 책 조회
    public Book readBook(Long id) {
        Optional<Book> book = bookRepository.findById(id);

        if (book.isPresent()) {
            return book.get();
        } else {
            throw new EntityNotFoundException("Can't find any book given by ID");
        }
    }

    // 전체 책 조회
    public List<Book> readBooks() {
        return bookRepository.findAll();
    }

    // ISBN으로 책 조회
    public Book readBook(String isbn) {
        Optional<Book> book = bookRepository.findByIsbn(isbn);
        if (book.isPresent()) {
            return book.get();
        } else {
            throw new EntityNotFoundException("Can't find any book given by ISBN");
        }
    }

    // 책 생성
    public Book createBook(BookCreationRequest book) {
        Optional<Author> author = authorRepository.findById(book.getAuthorId());

        // 저자가 존재하지 않는다면 에러 발생
        if (!author.isPresent()) {
            throw new EntityNotFoundException("Author Not Found");
        }
        // 저자가 존재한다면, 새 책 객체 생성하고
        Book bookToCreate = new Book();
        BeanUtils.copyProperties(book, bookToCreate);
        bookToCreate.setAuthor(author.get());
        return bookRepository.save(bookToCreate);
    }

    // 책 삭제
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    // 멤버 생성
    public Member createMember(MemberCreationRequest request) {
        Member member = new Member();
        BeanUtils.copyProperties(request, member);
        return memberRepository.save(member);
    }

    // 멤버 업데이트
    public Member updateMember(Long id, MemberCreationRequest request) {
        Optional<Member> optionalMember = memberRepository.findById(id);
        if (!optionalMember.isPresent()) {
            throw new EntityNotFoundException(
                    "Member not present in the database");
        }

        Member member = optionalMember.get();
        member.setLastName(request.getLastName());
        member.setFirstName(request.getFirstName());
        return memberRepository.save(member);
    }

    public Author createAuthor(AuthorCreationRequest request) {
        Author author = new Author();
        BeanUtils.copyProperties(request, author);
        return authorRepository.save(author);
    }

    public List<String> lendABook(BookLendRequest request) {

        Optional<Member> memberForId =
                memberRepository.findById(request.getMemberId());
        if (!memberForId.isPresent()) {
            throw new EntityNotFoundException(
                    "Member not present in the database");
        }
        Member member = memberForId.get();
        if (member.getStatus() != MemberStatus.ACTIVE) {
            throw new RuntimeException(
                    "User is not active to proceed a lending.");
        }
        List<String> booksApprovedToBurrow = new ArrayList<>();

        request.getBookIds().forEach(bookId -> {
            Optional<Book> bookForId = bookRepository.findById(bookId);
            if (!bookForId.isPresent()) {
                throw new EntityNotFoundException(
                        "Cant find any book under given ID");
            }

            Optional<Lend> burrowedBook =
                    lendRepository.findByBookAndStatus(
                            bookForId.get(), LendStatus.BURROWED);

            if (!burrowedBook.isPresent()) {
                booksApprovedToBurrow.add(bookForId.get().getName());
                Lend lend = new Lend();
                lend.setMember(memberForId.get());
                lend.setBook(bookForId.get());
                lend.setStatus(LendStatus.BURROWED);
                lend.setStartOn(Instant.now());
                lend.setDueOn(Instant.now().plus(30, ChronoUnit.DAYS));
                lendRepository.save(lend);
            }
        });
        return booksApprovedToBurrow;
    }
}
