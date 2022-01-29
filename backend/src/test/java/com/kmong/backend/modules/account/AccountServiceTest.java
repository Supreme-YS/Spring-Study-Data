package com.kmong.backend.modules.account;

import com.kmong.backend.modules.account.dto.AccountRes;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class AccountServiceTest {

    @Autowired
    private AccountService accountService;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    void signUp() {
        //given
        Account newAccount = new Account();
        newAccount.setId(1L);
        newAccount.setEmail("dudtjr1225@gmail.com");
        newAccount.setPassword("testpw");
        //when
        AccountRes accountRes = accountService.signUp(newAccount);
        Account byEmail = accountRepository.findByEmail(accountRes.getEmail());
        //then
        Assertions.assertThat(byEmail.getEmail()).isEqualTo("dudtjr1225@gmail.com");
    }

    @Test
    void validationLogin() {
        //given
        Account account = new Account();
        String email = "dudtjr1225@gmail.com";
        String password = "testpw";
        account.setEmail("dudtjr1225@gmail.com");
        account.setPassword("testpw");
        //when
        accountService.signUp(account);
        String encodedPassword = accountRepository.findByEmail(email).getPassword();

        //then
        assertAll(
                () -> assertNotEquals(password, encodedPassword),
                () -> assertTrue(passwordEncoder.matches(password, encodedPassword))
        );
    }
}