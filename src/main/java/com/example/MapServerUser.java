package com.example;

public class MapServerUser {
	private final String userId;
	private final String password;

	public MapServerUser(String userId, String password) {
		this.userId = userId;
		this.password = password;
	}

	public String getUserId() {
		return userId;
	}

	public String getPassword() {
		return password;
	}
}
