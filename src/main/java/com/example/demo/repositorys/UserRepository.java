package com.example.demo.repositorys;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.models.User;

@Repository
public class UserRepository {

	public static final String KEY = "user";
	@Autowired
	private RedisTemplate<String, Object> template;

	public User save(User user) {
		try {
			template.opsForHash().put(KEY, user.getId(), user);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return user;
	}

	public Map<Object, Object> findAll() {
		return template.opsForHash().entries(KEY);
	}

	public User findUserBydId(int id) {
		return (User) template.opsForHash().get(KEY, id);
	}
	
	public Long deleteUserById(int id) {
		return template.opsForHash().delete(KEY, id);
	}
	
	public User updateUser(User user) {
		return save(user);
	}

}
