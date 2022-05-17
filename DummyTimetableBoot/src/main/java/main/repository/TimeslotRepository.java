package main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import main.model.Timeslot;

@Repository
public interface TimeslotRepository extends JpaRepository<Timeslot, Integer> {
	
	public Timeslot findById(int id);
	
	/// FIXME: Understand the naming rule for user_id ???? UserId 
	public List<Timeslot> findByUserId(@Param("user_id") int userId);
	
	@Query("from Timeslot t where (t.start_at <= :start_at and :start_at <= t.end_at) or (t.start_at <= :end_at and :end_at <= t.end_at)")
	public List<Timeslot> findOverlap(@Param("start_at") long startAt, @Param("end_at") long endAt);
	
	@Query("from Timeslot t where t.end_at > :after_timestamp and t.end_at < :before_timestamp")
    public List<Timeslot> findBetweenRange(@Param("after_timestamp") long afterTimestamp, @Param("before_timestamp") long beforeTimestamp);
		
	@Query("from Timeslot t where t.end_at > :after_timestamp and t.end_at < :before_timestamp and user_id = :user_id")
	public List<Timeslot> findBetweenRangeAndByUserId(@Param("after_timestamp") long afterTimestamp, @Param("before_timestamp") long beforeTimestamp, @Param("user_id") int userId);
}
