package com.stackroute.userservice.service;

import com.stackroute.userservice.domain.User;
import com.stackroute.userservice.exception.UserNotAvailableException;

public interface AuthenticationService {
	public String saveUser(User user);
	
	public User getUserDetails(String userId, String password);

	

	public User updateUser(User user, String userId)throws UserNotAvailableException;

	public User viewUser(String userId) throws UserNotAvailableException;;

	
}
