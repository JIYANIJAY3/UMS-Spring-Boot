package com.inexture.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inexture.model.UserProfileBean;

public interface UserProfileDao extends JpaRepository<UserProfileBean, Integer> {
	
	List<UserProfileBean> findById(int userId);

	void deleteById(int imageId);
}
