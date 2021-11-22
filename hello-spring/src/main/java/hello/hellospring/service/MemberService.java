package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// command + shift + t = 테스트 단축키

// @Service // Bean으로 등록해주는 행위
public class MemberService {

    private final MemberRepository memberRepository;

   // @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /* 회원가입 */
    public Long join(Member member) {
        // 같은 이름이 있는 중복 회원은 안된다

        /* 기존 코드
        Optional<Member> result = memberRepository.findByName(member.getName()); // command + option + v
        result.ifPresent(m -> { // 어떤 값이 있으면!
            throw new IllegalStateException("이미 존재하는 회원입니다");
        }); */

// control + T 리팩토링 도구

        /* 비교 코드 -> 메서드*/
        validateDuplicateMember(member);

        memberRepository.save(member); // 중복회원 검증
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
