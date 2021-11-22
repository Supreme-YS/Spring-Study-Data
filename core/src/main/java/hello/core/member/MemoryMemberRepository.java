package hello.core.member;

import java.util.HashMap;
import java.util.Map;

/**
 * 원래는 분리하는게 낫지만, 편의상 같은 member 패키지 안에 생성
 */
public class MemoryMemberRepository implements MemberRepository {

    // 저장소를 위한 Map
    // concurrent-hashmap을 써야함. 동시성 문제 때문에..
    private static Map<Long, Member> store = new HashMap<>();

    @Override
    public void save(Member member) {
        store.put(member.getId(), member); // put(key, value) key : id, value : member 객체
    }

    @Override
    public Member findById(Long memberId) {

        return store.get(memberId);
    }
}
