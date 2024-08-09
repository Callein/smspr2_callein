package com.thc.smspr2.security;

import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;

public interface AuthService {
	
	Algorithm getTokenAlgorithm();
	String createAccessToken(String tbuserId);
	String verifyAccessToken(String accessToken) throws JWTVerificationException;
	String createRefreshToken(String tbuserId);
	void revokeRefreshToken(String tbuserId);
	String verifyRefreshToken(String refreshToken) throws JWTVerificationException;
	JwtTokenDto issueAccessToken(String refreshToken) throws JWTVerificationException;

}