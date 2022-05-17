package main.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;


import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
	@NotBlank(message = "name must be not blank")
	private String name;
	
	@JsonIgnore
	@OneToMany(mappedBy="user", cascade=CascadeType.ALL)
    List<Timeslot> timeslots;

	public User() {}
	
	public User(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Timeslot> getTimeslots() {
		return timeslots;
	}

	public void setTimeslots(List<Timeslot> timeslots) {
		this.timeslots = timeslots;
	}

	@Override
	public String toString() {
		/// FIXME: failed to lazily initialize a collection of role: main.model.User.timeslots
		return "User [id=" + id + ", name=" + name + /*", timeslots=" + timeslots + */ "]";
	}
	
}
