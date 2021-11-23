package hello.core.member;

public class MemberServiceImpl implements MemberService {

    // 가입하고 회원을 찾기 위해 인터페이스
    // 구현체를 일단 선택해준다. NullPointerException 방지, 메모리 버전의 저장소..!
//    private final MemberRepository memberRepository = new MemoryMemberRepository();
    // 하지만, 현재 추상화에도 의존하고 있고, 구현체에도 의존하고 있으므로 DIP 위반. 좋지 않은 코드다.

    // AppConfig를 이용
    // 현재 MemoryMemberRepository에 대한 코드가 없다. AppConfig에서 넣어주고 있음
    // 생성자를 통한 주입이라고 해서 생성자 주입이라고 한다.
    private final MemberRepository memberRepository;
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
