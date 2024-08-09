package com.thc.smspr2.security;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JwtTokenDto {
	private String accessToken;
	private String refreshToken;
}