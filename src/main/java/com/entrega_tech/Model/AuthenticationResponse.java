package com.entrega_tech.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data


@Builder
public class AuthenticationResponse {

	private String token;
	private String tokenType;
	private Long id_user;
	public AuthenticationResponse(String token, String tokenType, Long id_user) {
		super();
		this.token = token;
		this.tokenType = tokenType;
		this.id_user = id_user;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getTokenType() {
		return tokenType;
	}
	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}
	public Long getId_user() {
		return id_user;
	}
	public void setId_user(Long id_user) {
		this.id_user = id_user;
	}

	
}