package com.example.common.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginRequestDto {
    private String email;
    private String password;
}
