package com.kmong.backend.modules.account;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.util.List;
import java.util.Optional;

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
        Optional<Account> newAccountByEmail = accountService.findById(account.getId());
        //then
        Assertions.assertThat(newAccountByEmail.get().getEmail()).isEqualTo("dudtjr1225@gmail.com");
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
        String encodedPassword = accountRepository.findByEmail(email).get().getEmail();

        //then
        assertAll(
                () -> assertNotEquals(password, encodedPassword),
                () -> assertTrue(passwordEncoder.matches(password, encodedPassword))
        );
    }

    @Test
    void findAllAccount() {
        //given
        Account account1 = new Account();
        account1.setId(1L);
        account1.setEmail("dudtjr1225@gmail.com");
        account1.setPassword("testpw");
        Account newAccount1 = accountService.signUp(account1);

        Account account2 = new Account();
        account2.setId(2L);
        account2.setEmail("dudtjr1225@test.com");
        account2.setPassword("testpw");
        Account newAccount2 = accountService.signUp(account2);

        //when
        List<Account> allAccount = accountService.findAllAccount();

        //then
        Assertions.assertThat(allAccount.size()).isEqualTo(2);
    }

    @Test
    void findById() {
        //given
        Account account = new Account();
        account.setId(1L);
        account.setEmail("dudtjr1225@gmail.com");
        account.setPassword("testpw");
        Account newAccount = accountService.signUp(account);

        //when
        Optional<Account> accountById = accountService.findById(newAccount.getId());
        //then
        Assertions.assertThat(accountById).isNotEmpty();
    }
}