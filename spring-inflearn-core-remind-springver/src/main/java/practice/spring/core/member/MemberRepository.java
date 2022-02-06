package practice.spring.core.member;

public interface MemberRepository {
    // 메서드만 정의
    void save(Member member);

    Member findById(Long memberId);
}
