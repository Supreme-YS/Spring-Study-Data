package com.supreme.book.springboot.domain.posts;

// Posts 클래스로 Database를 접근하게 해줄 JpaRepository 이다. = PostsRepository
// 보통 ibatis나 MyBatis 등에서 Dao라고 불리는 DB Layer 접근자
// JPA에선 Repository라고 부르며 인터페이스로 생성한다.
// JpaRepository<Entity 클래스, PK 타입> 을 상속하면 기본적인 CRUD 메소드를 자동으로 생성한다.

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepository extends JpaRepository<Posts, Long> {
}
