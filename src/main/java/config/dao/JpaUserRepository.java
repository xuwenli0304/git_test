package config.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import config.pojo.UserDB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Repository
public interface JpaUserRepository extends JpaRepository<UserDB, Long> {

	// @PersistenceContext
    // public EntityManager entityManager = null;


	@Query("from user_db where user_name like concat('%', ?1, '%') " 
        + "and note like concat('', ?2, '%')")
	public List<UserDB> findUsers(String userName, String note);
	
	/**
	 * 按用户名称模糊查询
	 * @param userName 用户名
	 * @return 用户列表
	 */
	List<UserDB> findByUserNameLike(String userName);

	/**
	 * 根据主键查询
	 * @param id -- 主键
	 * @return 用户
	 */
	UserDB getUserById(Long id);

	/**
	 * 按照用户名称或者备注进行模糊查询
	 * @param userName 用户名
	 * @param note 备注
	 * @return 用户列表
	 */
	List<UserDB> findByUserNameLikeOrNoteLike(String userName, String note);
}
