package main.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.model.User;
import main.repository.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	
	@Override
	public void createAccount(User user) {
		if(isAccountExist(user.getName()))
			throw new IllegalArgumentException(String.format("User: name=%s already exists", user.getName()));
		userRepository.save(user);
	}

	@Override
	public boolean isAccountExist(String name) {
		return userRepository.findByName(name) != null;
	}

	@Override
	public List<User> getAllUser() {
		return userRepository.findAll();
	}

	@Override
	public User findById(int id) {
		System.out.println(String.format("findById(%d)".formatted(id)));
		return userRepository.findById(id)
				.orElseThrow(()-> new IllegalArgumentException(String.format("User: id=%d not exists", id)));
	}

}
