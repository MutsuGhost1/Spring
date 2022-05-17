package main.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import main.error.ErrorResponse;
import main.model.Timeslot;
import main.service.TimeslotService;

@RestController
@RequestMapping("/api/users")
public class TimeslotController {
	
	@Autowired
	TimeslotService timeslotService;

	@GetMapping("/test")
    public String getHome() {
		return "Welcome !!!";
	}
	
	
	@GetMapping("/time-slots")
	public ResponseEntity<List<Timeslot>> getTimeslot() {
		return ResponseEntity.status(HttpStatus.OK)
				             .body(timeslotService.findAll());
	}
	
	
	@GetMapping("/{user_id}/time-slots")
	public ResponseEntity<List<Timeslot>> getTimeslot(@PathVariable(name="user_id") int userId, @RequestParam(required=false) Long after_timestamp, @RequestParam(required=false) Long before_timestamp) {
		if(null == before_timestamp && null == after_timestamp)
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(timeslotService.findAllByUserId(userId));
		else
            return ResponseEntity
            		.status(HttpStatus.OK)
            		.body(timeslotService.findAllByUserIdAndRange(userId, after_timestamp, before_timestamp));
	}
	
	@PostMapping("/{user_id}/time-slots")
	public ResponseEntity<Timeslot> addTimeslot(@PathVariable(name="user_id") int userId, @RequestBody Timeslot timeSlot) {
		timeslotService.addTimeslot(userId, timeSlot);
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(timeSlot);
	}
	
	@DeleteMapping("/{user_id}/time-slots/{time_slot_id}")
	public ResponseEntity<Void> delTimesolt(@PathVariable(name="user_id") int userId, @PathVariable(name="time_slot_id") int timeslotId) {
		timeslotService.delTimeslot(userId, timeslotId);
        return ResponseEntity
        		.status(HttpStatus.OK)
        		.body(null);
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorResponse> exceptionHandler(IllegalArgumentException e) {
		return ErrorResponse.newResponseEntity(
				HttpStatus.BAD_REQUEST.value(),
				e.getMessage(),
				System.currentTimeMillis());
	}
	
	/// Define Exception
	///   > IllegalParameter
	///   > UserNotFound
	///   > UserAlreadyExists
	///   > TimeslotNotFound
	///   > TimeslotOverlap
}

