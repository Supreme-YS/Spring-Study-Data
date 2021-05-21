package spring.springstudy.repository;

import spring.springstudy.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    // repository에 네가지 기능을 생성
    Member save(Member member);
    Optional<Member> findById(long id);
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
