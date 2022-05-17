package main.service;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.model.Timeslot;
import main.model.User;
import main.repository.TimeslotRepository;

@Service
@Transactional
public class TimeslotServiceImpl implements TimeslotService {
	
	private long SECOND4DAY = 60*60*24;
	
	@Autowired
	TimeslotRepository timeslotRepository;
	
	@Autowired
	UserService userService;

	@Override
	public List<Timeslot> findAllByUserId(int userId) {
		userService.findById(userId);
		return timeslotRepository.findByUserId(userId);
	}

	@Override
	public List<Timeslot> findAllByUserIdAndRange(int userId, Long start_after, Long end_before) {
		/// check parameter
		if(null == start_after || null == end_before)
			throw new IllegalArgumentException(String.format("None of start_after(%s), end_before(%s) can be null", start_after, end_before));
		userService.findById(userId);
		return timeslotRepository.findBetweenRangeAndByUserId(start_after, end_before, userId);
	}

	@Override
	public void addTimeslot(int userId, Timeslot timeSlot) {
		User user = userService.findById(userId);
		/// 1. start_at > now
		if(timeSlot.getStart_at() <= LocalDateTime.now().toLocalTime().toSecondOfDay())
			throw new IllegalArgumentException("start_at must greater than now");
		
		/// 2. end_at > start_at
		if(timeSlot.getEnd_at() <= timeSlot.getStart_at())
			throw new IllegalArgumentException("end_at must greater than start_at");

		/// 3. end_at - start_at <= 24 hours
		if(timeSlot.getEnd_at() - timeSlot.getStart_at() > SECOND4DAY)
			throw new IllegalArgumentException("end_at - start_at must less than or equal 24 hours");
		/// 4. overlapped
		List<Timeslot> overlapTimeslots = timeslotRepository.findOverlap(timeSlot.getStart_at(), timeSlot.getStart_at());
		if(overlapTimeslots.size() != 0)
			throw new IllegalArgumentException(String.format("Request Timeslot %s is overlapped with %s", timeSlot, overlapTimeslots.get(0)));
		
		timeSlot.setUser(user);
		timeslotRepository.save(timeSlot);
	}

	@Override
	public void delTimeslot(int userId, int timeslotId) {
		userService.findById(userId);
		if(!timeslotRepository.existsById(timeslotId))
			return;
		timeslotRepository.deleteById(timeslotId);
	}

	@Override
	public List<Timeslot> findAll() {
		return timeslotRepository.findAll();
	}
}
