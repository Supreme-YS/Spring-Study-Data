package spring.springstudy.repository;

import spring.springstudy.domain.Member;

import java.util.*;

// 구현체 생성
public class MemoryMemberRepository implements MemberRepository {

    // 구현을 해야하니까 어딘가 메모리에 저장을 해놔야한다.
    private static Map<Long, Member> store = new HashMap<>(); // 실무에선 동시성 문제가 발생할 수 있다.
    private static long sequence = 0L; // 연속된 key값을 생성, 역시 동시성 문제가 발생할 수 있다.

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(long id) {
        return Optional.ofNullable(store.get(id)); // null 값이 넘어올 수 있기 때문에 Optional.ofNullable로 감싸준다.
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream() // 루프를 돌면서
                .filter(member -> member.getName().equals(name)) // 필터링을 통해 같은 값만을
                .findAny(); // 하나라도 찾아서 반환하고, null 이어도 optional이기 떄문에 괜찮다.
        }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
