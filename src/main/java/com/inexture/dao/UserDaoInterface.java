package com.inexture.dao;

import com.inexture.model.UserBean;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface UserDaoInterface extends JpaRepository<UserBean, Integer> {

	boolean existsByEmail(String email);

	UserBean findByEmail(String email);

	UserBean findById(int userId);

	List<UserBean> findAll();

	void deleteById(int UserId);

	UserBean findByEmailAndAnswer(String email,String answer);

	@Transactional
	@Modifying
	@Query("update UserBean set password=:password where UserId=:UserId")
	void updatePassword(@Param("UserId") int userId,@Param("password") String password);
}
