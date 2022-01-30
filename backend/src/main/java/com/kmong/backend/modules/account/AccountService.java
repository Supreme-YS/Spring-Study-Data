package com.kmong.backend.modules.account;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class AccountService implements UserDetailsService {

    private final AccountRepository accountRepository;

    public Account signUp(Account account) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (!validateDuplicateAccount(account)) {
            String encodedPassword = passwordEncoder.encode(account.getPassword());
            account.setPassword(encodedPassword);
            return accountRepository.save(account);
        } else {
            return new Account();
        }
    }

    private boolean validateDuplicateAccount(Account account) {
        return accountRepository.existsByEmail(account.getEmail());
    }

    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        Optional<Account> userEntityWrapper = accountRepository.findByEmail(userEmail);
        Account userEntity = userEntityWrapper.get();

        List<GrantedAuthority> authorities = new ArrayList<>();

        if (("admin@example.com").equals(userEmail)) {
            authorities.add(new SimpleGrantedAuthority(AccountRole.ROLE_ADMIN.getDescription()));
        } else {
            authorities.add(new SimpleGrantedAuthority(AccountRole.ROLE_USER.getDescription()));
        }
        return new User(userEntity.getEmail(), userEntity.getPassword(), authorities);
    }

    public List<Account> findAllAccount() {
        return accountRepository.findAll();
    }

    public Optional<Account> findById(Long accountId) {
        return accountRepository.findById(accountId);
    }

    public boolean validationLogin(String email, String password) {

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Optional<Account> loginAccount = accountRepository.findByEmail(email);

        if (loginAccount == null) {
            return false;
        }
        if (!passwordEncoder.matches(password, loginAccount.get().getPassword())) {
            return false;
        }
        return true;
    }
}
