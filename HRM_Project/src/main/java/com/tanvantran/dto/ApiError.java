package com.tanvantran.dto;

import java.time.LocalDateTime;
import java.util.List;

public class ApiError {
	private LocalDateTime timestamp;
	private int status; // 404, 400, 500...
	private String error; // "Not Found", "Bad Request"...
	private String message; // thông báo chi tiết
	private String path; // URI bị lỗi
	private List<String> validationErrors; // (optional) lỗi validate
	
	public ApiError(LocalDateTime timestamp, int status, String error, String message, String path,
			List<String> validationErrors) {
		super();
		this.timestamp = timestamp;
		this.status = status;
		this.error = error;
		this.message = message;
		this.path = path;
		this.validationErrors = validationErrors;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public List<String> getValidationErrors() {
		return validationErrors;
	}

	public void setValidationErrors(List<String> validationErrors) {
		this.validationErrors = validationErrors;
	}
	
}
