package com.example.restapi.dto;

import lombok.Data;

@Data
public class MemberCreationRequest {
    private String firstName;
    private String lastName;
    private String status;
}
