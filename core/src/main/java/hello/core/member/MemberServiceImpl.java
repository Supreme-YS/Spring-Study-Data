package hello.core.member;

public class MemberServiceImpl implements MemberService {

    // 가입하고 회원을 찾기 위해 인터페이스
    private final MemberRepository memberRepository = new MemoryMemberRepository();
    // 구현체를 일단 선택해준다. NullPointerException 방지, 메모리 버전의 저장소..!


    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
