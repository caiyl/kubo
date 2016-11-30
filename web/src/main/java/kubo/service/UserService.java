package kubo.service;

import kubo.domain.User;

import java.util.List;

public interface UserService {
	public User getUser();

	public User getUser(Integer id);

	public List<User> getUserByName(String name);
	
	public int addUser(User user);
}
