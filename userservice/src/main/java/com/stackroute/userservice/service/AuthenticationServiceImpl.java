package com.stackroute.userservice.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.userservice.domain.User;
import com.stackroute.userservice.exception.UserAlreadyExistException;
import com.stackroute.userservice.exception.UserNotAvailableException;
import com.stackroute.userservice.repository.AuthenticationRepository;

@Service
public class AuthenticationServiceImpl implements AuthenticationService{

//	@Autowired
//	AuthenticationRepository authRepo;
	
	private final  transient AuthenticationRepository authRepo;
	@Autowired
	public AuthenticationServiceImpl(final AuthenticationRepository authRepo) {
		super();
		this.authRepo = authRepo;
	}
	
	public String saveUser(User user) {
		Optional<User> currentUser= authRepo.findById(user.getUserId());
		if(currentUser.isPresent()) throw new UserAlreadyExistException("User id already exists");
		authRepo.save(user);
		return "User successfully registered to the App";
	}
	
	public User getUserDetails(String userId, String password) {
		User user =authRepo.findByUserIdAndPassword(userId, password);
		if(null == user) throw new UserNotAvailableException("User not found ");
		return user;
	}

	/*@Override
	public User updateUser(User user, String userId) {
		// TODO Auto-generated method stub
//		User user =authRepo.findByUserIdAndPassword(userId, password);
//		if(null == user) throw new UserNotAvailableException("User not found ");
		//Optional<User> user1 = authRepo.findById(userId);
		//User user1 =  authRepo.findById(userId).get();
		//user1.getUserId();
		//System.out.println(user1.getUserId());
		user.setUserId(user.getUserId());
		
		user.setFirstName(user.getFirstName());
		user.setLastName(user.getLastName());
		user.setProfile(user.getProfile());
		user.setPassword(user.getPassword());
		//System.out.println("hi" + user1.get().getPassword());
		
//		user.setUserid(user1.getUserid());
//		System.out.println(user.getUserid());
//		System.out.println(user.getUsername());
		return authRepo.save(user);

//		return user;
	}*/
	

	@Override
	public User updateUser(User user,String userId) throws UserNotAvailableException {
		try {
		Optional<User> user1= authRepo.findById(userId);
			if(user1.isPresent()) {	
				user1.get().setFirstName(user.getFirstName());
				user1.get().setLastName(user.getLastName());
				user1.get().setPassword(user.getPassword());
				//user1.get().setProfile(user.getProfile());
				User user2=user1.get();
				authRepo.save(user2);
				
				return user2;
			}
			else
			{
				return null;
			}
			
	   }catch(Exception e) {
		e.printStackTrace();
		throw e;
	  }
		
	}
	
	@Override
	public User viewUser(String userId) throws UserNotAvailableException {
		try {
		Optional<User> user1= authRepo.findById(userId);
			if(user1.isPresent()) {	
				User user2=user1.get();
				return user2;
			}
			else
			{
				return null;
			}
			
	   }catch(Exception e) {
		e.printStackTrace();
		throw e;
	  }
		
	}
	
}
