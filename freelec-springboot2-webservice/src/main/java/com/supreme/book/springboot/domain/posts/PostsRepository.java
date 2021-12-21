package com.supreme.book.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
// JpaRepository<Entity Class, PK type>
public interface PostsRepository extends JpaRepository<Posts, Long> {
}
