package com.ireland.ager.api.account;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class AccountRes {
    String accountEmail;
    String profileNickname;
    String accountName;
    String phoneNumber;
    String profileImageUrl;
    String profileDescription;
    String gender;
    String ageRange;
    String accessToken;
    String refreshToken;

    public static AccountRes of(Account account) {

        return AccountRes.builder()
                .accountEmail(account.getAccountEmail())
                .profileNickname(account.getProfileNickname())
                .accountName(account.getaccountName())
                .phoneNumber(account.getPhoneNumber())
                .profileImageUrl(account.getProfileImageUrl())
                .profileDescription(account.getProfileDescription())
                .gender(account.getGender())
                .ageRange(account.getAgeRange())
                .accessToken(account.getAccessToken())
                .refreshToken(account.getRefreshToken())
                .build();
    }
}
