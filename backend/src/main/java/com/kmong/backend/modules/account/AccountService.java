package com.kmong.backend.modules.account;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class AccountService {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    public Account signUp(Account account) {
        String encodedPassword = passwordEncoder.encode(account.getPassword());
        account.setPassword(encodedPassword);
        return accountRepository.save(account);
    }

    public boolean validationLogin(String email, String password) {
        Account loginAccount = accountRepository.findByEmail(email);

        if (loginAccount == null) {
            return false;
        }
        if (!passwordEncoder.matches(password, loginAccount.getPassword())) {
            return false;
        }
        return true;
    }

    public Optional<Account> findById(Long accountId) {
        return accountRepository.findById(accountId);
    }
}
