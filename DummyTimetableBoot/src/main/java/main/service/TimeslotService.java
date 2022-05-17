package main.service;

import java.util.List;

import main.model.Timeslot;

public interface TimeslotService {
    public List<Timeslot> findAll();
    public List<Timeslot> findAllByUserId(int userId);
    public List<Timeslot> findAllByUserIdAndRange(int userId, Long start_after, Long end_before);

    public void addTimeslot(int userId, Timeslot timeSlot);
	public void delTimeslot(int userId, int id);
}
