package com.ireland.trading.service;

import com.ireland.trading.domain.Member;
import com.ireland.trading.dto.MemberSignupRequestDto;
import com.ireland.trading.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public String signup(MemberSignupRequestDto request) {

        boolean existMember = memberRepository.existsById(request.getEmail());

        // 이미 회원이 존재할 경우
        if (existMember) {
            return null;
        }

        Member member = new Member(request);
        member.encryptPassword(passwordEncoder);

        memberRepository.save(member);
        return member.getEmail();
    }
}
