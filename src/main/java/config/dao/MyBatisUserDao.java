package config.dao;

import org.springframework.stereotype.Repository;

import config.pojo.UserDB;


/**** imports ****/
@Repository
public interface MyBatisUserDao {
	public UserDB getUser(Long id);
}