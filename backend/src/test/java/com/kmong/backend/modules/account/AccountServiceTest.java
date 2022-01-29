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
        Account account = new Account(1L, "dudtjr1225@gmail.com", "testpassword", AccountRole.ROLE_USER) ;
        //when
        Account newAccount = accountService.signUp(account);
        //then
        Assertions.assertThat(newAccount.getAccountRole()).isEqualTo(AccountRole.ROLE_USER);
    }

    @Test
    void validationLogin() {
        //given
        String email = "dudtjr1225@gmail.com";
        String password = "testpassword";

        Account account = new Account(1L, "dudtjr1225@gmail.com", "testpassword", AccountRole.ROLE_USER);
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