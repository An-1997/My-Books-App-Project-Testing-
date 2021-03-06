package com.stackroute.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.userservice.domain.User;

@Repository
public interface AuthenticationRepository extends JpaRepository<User,String> {
	public User findByUserIdAndPassword(String userId, String password);
}
