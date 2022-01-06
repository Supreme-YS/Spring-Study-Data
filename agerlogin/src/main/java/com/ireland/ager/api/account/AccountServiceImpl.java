package com.ireland.ager.api.account;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class AccountServiceImpl extends AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public AccountRes findaccountByAccountEmail(String accountEmail) {
        
        Optional<Account> optionalaccount = accountRepository.findById(accountEmail);
        return optionalaccount.map(account -> AccountRes.of(account)).orElse(null);
    }

    @Override
    public AccountRes findaccountByAccessToken(String accessToken) {
        
        Optional<Account> optionalaccount = accountRepository.findaccountByAccessToken(accessToken);
        return optionalaccount.map(account -> AccountRes.of(account)).orElse(null);
    }

    @Override
    public AccountRes insertaccount(Account newAccount) {

        newAccount.setProfileNickname(newAccount.getaccountName());
        accountRepository.save(newAccount);
        return AccountRes.of(newAccount);
    }

    @Override
    public AccountRes updateaccount(AccountUpdatePatchReq accountUpdatePatchReq) {

        Optional<Account> optionalUpdateaccount = accountRepository.findById(accountUpdatePatchReq.getAccountEmail());
        Account updatedaccount = optionalUpdateaccount.map(account -> accountUpdatePatchReq.toaccount(account)).orElse(null);
        if(updatedaccount != null) accountRepository.save(updatedaccount);

        return AccountRes.of(updatedaccount);
    }

}
