package com.example.restapi.dto;

import lombok.Data;

import java.util.List;

@Data
public class BookLendRequest {
    private Long bookId;
    private Long memberId;
}
