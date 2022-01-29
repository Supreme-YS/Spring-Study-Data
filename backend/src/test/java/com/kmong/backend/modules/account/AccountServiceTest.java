package com.kmong.backend.modules.account;

import org.assertj.core.api.Assertions;
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
        Account account = new Account();
        account.setId(1L);
        account.setEmail("dudtjr1225@gmail.com");
        account.setPassword("testpw");
        //when
        Account newAccount = accountService.signUp(account);
        Account newAccountByEmail = accountRepository.findByEmail(account.getEmail());
        //then
        Assertions.assertThat(newAccountByEmail.getEmail()).isEqualTo("dudtjr1225@gmail.com");
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