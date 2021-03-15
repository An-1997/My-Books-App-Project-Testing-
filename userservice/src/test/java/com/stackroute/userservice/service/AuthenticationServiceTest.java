   package com.stackroute.userservice.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.Optional;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;
import com.stackroute.userservice.domain.User;
import com.stackroute.userservice.exception.UserAlreadyExistException;
import com.stackroute.userservice.exception.UserNotAvailableException;
import com.stackroute.userservice.repository.AuthenticationRepository;


@RunWith(SpringRunner.class)
public class AuthenticationServiceTest {
	@Mock
	private AuthenticationRepository AR;
	@InjectMocks
	private AuthenticationServiceImpl service;
	User user, user1;
	Optional<User> optUser;
	@Before
	public void setUp() throws Exception {
		user = new User();
		user.setUserId("123");
		user.setPassword("group2");
		user.setFirstName("bookApp");
		user.setLastName("bookappGroup2");
		optUser = Optional.of(user);
		user1 = new User();
		user1.setUserId("1234");
		user1.setPassword("group223");
		user1.setFirstName("bookApp11");
		user1.setLastName("bookappGroup22");
	}
	@After
	public void tearDown() throws Exception {
	}
	@Test
	public void saveUserTestSucess() {
		String s = service.saveUser(user1);
		assertEquals("User successfully registered to the App", s);
	}
	@Test(expected = UserAlreadyExistException.class)
	public void saveUserTestFailure() throws UserAlreadyExistException {
		when(AR.findById(Mockito.anyString())).thenReturn(optUser);
		service.saveUser(user);
		verify(AR).findById(Mockito.anyString());
	}
	@Test
	public void getUserDetailsTest() throws UserAlreadyExistException {
		when(AR.findByUserIdAndPassword(Mockito.anyString(), Mockito.anyString())).thenReturn(user);
		User u = service.getUserDetails("123", "group2");
		assertEquals("bookApp", u.getFirstName());
	}
	@Test(expected = UserNotAvailableException.class)
	public void getUserDetailsFailure() {
		User s = service.getUserDetails("null", "null");
		assertEquals("bookApp", s.getFirstName());
	}
	@Test(expected = UserNotAvailableException.class)
	public void getUserDetailsFailure1() {
		when(AR.findByUserIdAndPassword("123", "group2")).thenReturn(user);
		User s = service.getUserDetails("1234", "passw");
		assertEquals(user.getFirstName(), s.getFirstName());
	}
	@Test(expected = UserNotAvailableException.class)
	public void getUserDetailsFailure2() {
		when(AR.findByUserIdAndPassword("123", "group2")).thenReturn(user);
		User s = service.getUserDetails("123", "null");
		assertEquals(user.getFirstName(), s.getFirstName());
	}
	@Test(expected = UserNotAvailableException.class)
	public void getUserDetailsNullTest3() {
		when(AR.findByUserIdAndPassword("123", "group2")).thenReturn(user);
		User s = service.getUserDetails("null", "group2");
		assertEquals(user.getFirstName(), s.getFirstName());
	}

	
}
