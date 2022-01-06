package com.ireland.ager.api.account;

public interface AccountService {
    AccountRes insertaccount(Account newAccount);
    AccountRes updateaccount(AccountUpdatePatchReq accountUpdatePatchReq);
    AccountRes findaccountByAccountEmail(String accountEmail);
    AccountRes findaccountByAccessToken(String accessToken);

}

