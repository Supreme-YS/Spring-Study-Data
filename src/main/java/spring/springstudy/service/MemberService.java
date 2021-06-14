package spring.springstudy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import spring.springstudy.domain.Member;
import spring.springstudy.repository.MemberRepository;

import java.util.List;
import java.util.Optional;

// @Service // @Autowired와 연결 , 스프링 컨테이너에 서비스로 등록
         // @Service, Repository, Controller 안에 @Component라는 Annotation이 있다.
         // 그래서 Component Scan 이라는 용어를 쓴다.

@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired // MemberService를 Spring이 생성할 때 '너는 MemberRepository가 필요하구나'해서 스프링 컨테이너에 있는 MemberRepository를 넣어준다.
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

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
