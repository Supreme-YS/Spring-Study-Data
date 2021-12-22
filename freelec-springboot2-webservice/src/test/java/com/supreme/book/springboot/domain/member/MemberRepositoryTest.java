package com.supreme.book.springboot.domain.member;

import lombok.extern.java.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log
public class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    public void 회원_추가_권한_테스트() {
        for (int i = 0; i < 100; i++) {
            Member member = new Member();
            member.setUid("user" + i);
            member.setUpw("pw" + i);
            member.setUemail("test@" + i);

            MemberRole memberRole = new MemberRole();
            if (i <= 80) {
                memberRole.setRoleName("BASIC");
            } else if (i <= 90) {
                memberRole.setRoleName("MANAGER");
            } else {
                memberRole.setRoleName("ADMIN");
            }
            member.setRoles(Arrays.asList(memberRole));
            memberRepository.save(member);
        }
    }

    @Test
    public void testMember() {
        Optional<Optional<Member>> result = Optional.ofNullable(memberRepository.findById(85L));
        result.ifPresent(member -> log.info("member" + member));
    }
}
