package com.kmong.backend.modules.account;

import com.kmong.backend.modules.account.dto.AccountRes;
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

    public AccountRes signUp(Account newAccount) {
        String encodedPassword = passwordEncoder.encode(newAccount.getPassword());
        newAccount.setPassword(encodedPassword);
        accountRepository.save(newAccount);
        return AccountRes.toAccountRes(newAccount);
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
