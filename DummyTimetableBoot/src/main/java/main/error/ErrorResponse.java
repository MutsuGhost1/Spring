package main.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ErrorResponse {
	
    private int httpStatus;
    
    private String message;
    
    private long timestamp;
    
    public static ResponseEntity<ErrorResponse> newResponseEntity(int httpStatus, String message, long timestamp) {
		ErrorResponse error = new ErrorResponse();
		
		error.setHttpStatus(HttpStatus.BAD_REQUEST.value());
		error.setMessage(message);
		error.setTimestamp(System.currentTimeMillis());
		
		return ResponseEntity
				.status(HttpStatus.BAD_REQUEST)
				.body(error);   	
    }
    
	public int getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(int httpStatus) {
		this.httpStatus = httpStatus;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
    
}
