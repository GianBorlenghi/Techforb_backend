package com.entrega_tech.Model;

public class JWTAuthResponseDTO {
	private String accessToken;
	private String tokenType;
	private Long id_user;
	
	public JWTAuthResponseDTO(String accessToken, String tokenType, Long id_user) {
		super();
		this.accessToken = accessToken;
		this.tokenType = tokenType;
		this.id_user = id_user;
		
	}
	
	public String getAccessToken() {
		return accessToken;
	}


	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
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
