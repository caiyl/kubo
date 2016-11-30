package kubo.service.impl;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kubo.dao.UserMapper;
import kubo.domain.User;
import kubo.service.AbstractService;
import kubo.service.UserService;

@Service
@Transactional
public class UserServiceImpl extends AbstractService<User, Integer> implements UserService {
	@Autowired
	private UserMapper userMapper;


	public User getUser() {
		User user = new User();
		// 查询数据
		user.setPassword("123");
		return user;
	}

	public User getUser(Integer id) {
		return selectByPrimaryKey(id);
	}

	public List<User> getUserByName(String name) {
		return userMapper.selectByUserName(name);

	}

	@Override
	@Transactional
	public int addUser(User user) {
		
		
        User user2 = new User();
        
        user2.setPassword("1");
//		insertSelective(user2);
//		int a = 4/0;
		return 1;
		
	}

	@Override
	public void setBaseMapper() {
	}
	
	@PostConstruct
	public void init() {
		super.setBaseMapper(userMapper);
	}

}
