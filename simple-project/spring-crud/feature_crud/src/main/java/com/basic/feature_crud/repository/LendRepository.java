package com.basic.feature_crud.repository;

import com.basic.feature_crud.model.Book;
import com.basic.feature_crud.model.Lend;
import com.basic.feature_crud.model.LendStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LendRepository extends JpaRepository<Lend, Long> {
    Optional<Lend> findByBookAndStatus(Book book, LendStatus status);
}
