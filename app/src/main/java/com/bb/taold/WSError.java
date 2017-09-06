package com.bb.taold;

public class WSError extends Throwable {

	
	private String message;

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
