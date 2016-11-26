package kubo.service;

import kubo.domain.User;

public interface UserService {
	public User getUser();

	public User getUser(Integer id);

	public User getUserByName(String name);
	
	public int addUser(User user);
}
