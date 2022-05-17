package main.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
///import javax.validation.constraints.Future;

import com.fasterxml.jackson.annotation.JsonIgnore;

///import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "timeslots")
///@JsonIgnoreProperties(ignoreUnknown = true)
public class Timeslot {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	///@Future
    private long start_at;
    
    private long end_at;
    
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public long getStart_at() {
		return start_at;
	}

	public void setStart_at(long start_at) {
		this.start_at = start_at;
	}

	public long getEnd_at() {
		return end_at;
	}

	public void setEnd_at(long end_at) {
		this.end_at = end_at;
	}

	@Override
	public String toString() {
		return "Timeslot [id=" + id + ", start_at=" + start_at + ", end_at=" + end_at + ", user=" + user + "]";
	}
    
}
