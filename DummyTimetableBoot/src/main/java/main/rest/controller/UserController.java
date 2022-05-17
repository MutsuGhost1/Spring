package main.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import main.error.ErrorResponse;
import main.model.User;
import main.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("/users")
    public ResponseEntity<List<User>> getUser() {
    	return ResponseEntity
    			.status(HttpStatus.OK)
    			.body(userService.getAllUser());
    }
	
	@PostMapping("/users")
	public ResponseEntity<User> addUser(@RequestBody User user) {
		/// error handling
		if(null == user.getName() || user.getName().isBlank())
			throw new IllegalArgumentException("addUser: user name can't be Blank");
		user.setId(0);
		userService.createAccount(user);
    	return ResponseEntity
    			.status(HttpStatus.CREATED)
    			.body(user);
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorResponse> exceptionHandler(IllegalArgumentException e) {
		return ErrorResponse.newResponseEntity(
				HttpStatus.BAD_REQUEST.value(),
				e.getMessage(),
				System.currentTimeMillis());
	}
}
