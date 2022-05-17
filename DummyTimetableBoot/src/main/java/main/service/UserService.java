package main.service;

import java.util.List;

import main.model.User;

public interface UserService {
    public void createAccount(User user);
    public boolean isAccountExist(String name);
    public List<User> getAllUser();
    public User findById(int id);
}
