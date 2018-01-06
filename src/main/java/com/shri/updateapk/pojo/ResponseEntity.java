package com.shri.updateapk.pojo;

public class ResponseEntity {

	private Object response;

	private ResponseStatus status;

	private String userToken;

	public ResponseEntity() {
	}

	public ResponseEntity(Object response, ResponseStatus status, String userToken) {
		super();
		this.response = response;
		this.status = status;
		this.userToken = userToken;
	}

	public Object getResponse() {
		return response;
	}

	public void setResponse(Object response) {
		this.response = response;
	}

	public ResponseStatus getStatus() {
		return status;
	}

	public void setStatus(ResponseStatus status) {
		this.status = status;
	}

	public String getUserToken() {
		return userToken;
	}

	public void setUserToken(String userToken) {
		this.userToken = userToken;
	}

	@Override
	public String toString() {
		return "ResponseEntity [response=" + response + ", status=" + status + ", userToken=" + userToken + "]";
	}

}
