package spring.springstudy.service;

import spring.springstudy.domain.Member;
import spring.springstudy.repository.MemberRepository;
import spring.springstudy.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {

    private final MemberRepository memberRepository = new MemoryMemberRepository();

    /* 회원가입 */
    public Long join(Member member) {
        // 같은 이름이 있는 중복 회원은 안된다.
        // Optional<Member> result  = memberRepository.findByName(member.getName());
        // result.ifPresent(m -> {
        //    throw new IllegalStateException("이미 존재하는 회원입니다.");
        // });
        // 로직이 있는 경우엔 아래처럼 메서드로 추출하는게 낫다. 따라서 control + t -> method 로 extract!
        validateDuplicateMember(member); // 중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /* 전체 회원 조회 */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
