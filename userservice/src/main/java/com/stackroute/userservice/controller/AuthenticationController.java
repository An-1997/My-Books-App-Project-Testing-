package com.stackroute.userservice.controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
//import java.nio.file;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.stackroute.userservice.domain.User;
import com.stackroute.userservice.exception.UserAlreadyExistException;
import com.stackroute.userservice.exception.UserNotAvailableException;
import com.stackroute.userservice.service.AuthenticationService;
import com.stackroute.userservice.service.FileUploadUtil;
import com.stackroute.userservice.service.TokenGeneratorService;
import com.stackroute.userservice.util.AuthenticationConstants;

import io.jsonwebtoken.Jwts;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;


@RestController
@CrossOrigin(origins="*")
@RequestMapping(AuthenticationConstants.ROOT_PATH)
public class AuthenticationController {


	private final AuthenticationService authService;	
	private final TokenGeneratorService  tokenGenerator;	

	public AuthenticationController(AuthenticationService authService, TokenGeneratorService  tokenGenerator) {
		this.tokenGenerator=tokenGenerator;
		this.authService=authService;		
	}

	@ApiOperation(value="registerUser", notes="Register user for My Favourite Books App")
	@PostMapping(path = AuthenticationConstants.REGISTER_PATH) 
	public ResponseEntity<?>  registerUser(@ApiParam(name="User", value="User", required=true) @RequestBody User user){
		ResponseEntity<String> reponse;
		try {
			if(null == user.getFirstName() || user.getFirstName().isEmpty() ) {
				return new ResponseEntity<String>(AuthenticationConstants.FIRST_NAME_MSG,HttpStatus.BAD_REQUEST);
			}
			if(null == user.getLastName() || user.getLastName().isEmpty()) {
				return new ResponseEntity<String>(AuthenticationConstants.LAST_NAME_MSG,HttpStatus.BAD_REQUEST);
			}
			if(null == user.getUserId() || user.getUserId().isEmpty() ||
					null == user.getPassword() || user.getPassword().isEmpty()) {
				return new ResponseEntity<String>(AuthenticationConstants.USERID_MSG,HttpStatus.BAD_REQUEST);
			}
//			if(null == user.getProfile() || user.getProfile().isEmpty()) {
//				return new ResponseEntity<String>(AuthenticationConstants.PROFILE_MSG,HttpStatus.BAD_REQUEST);
//			}
			 

			reponse = new ResponseEntity<>(authService.saveUser(user),HttpStatus.CREATED);
			
			
		}catch(UserAlreadyExistException e) {
			return new ResponseEntity<String>(e.getErrorMessage(),HttpStatus.CONFLICT);
		}
		return reponse;
	}
	
	
	/* @ApiOperation(value="registerUser", notes="Register user for My Favourite Books App")
	@PostMapping(path = AuthenticationConstants.REGISTER_PATH,consumes = {"multipart/mixed"}) 
	public ResponseEntity<?>  registerUser(@ApiParam(name="User", value="User", required=true) @RequestBody User user,@RequestParam("image") MultipartFile multipartFile) throws IOException{
		ResponseEntity<String> reponse;
		try {
			if(null == user.getFirstName() || user.getFirstName().isEmpty() ) {
				return new ResponseEntity<String>(AuthenticationConstants.FIRST_NAME_MSG,HttpStatus.BAD_REQUEST);
			}
			if(null == user.getLastName() || user.getLastName().isEmpty()) {
				return new ResponseEntity<String>(AuthenticationConstants.LAST_NAME_MSG,HttpStatus.BAD_REQUEST);
			}
			if(null == user.getUserId() || user.getUserId().isEmpty() ||
					null == user.getPassword() || user.getPassword().isEmpty()) {
				return new ResponseEntity<String>(AuthenticationConstants.USERID_MSG,HttpStatus.BAD_REQUEST);
			}
			if(null == user.getProfile() || user.getProfile().isEmpty()) {
				return new ResponseEntity<String>(AuthenticationConstants.PROFILE_MSG,HttpStatus.BAD_REQUEST);
			}
			 String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		        user.setProfile(fileName);

			reponse = new ResponseEntity<>(authService.saveUser(user),HttpStatus.CREATED);
		       
			
			String uploadDir = "user-photos/" + user.getUserId();
			
			FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
		}catch(UserAlreadyExistException e) {
			return new ResponseEntity<String>(e.getErrorMessage(),HttpStatus.CONFLICT);
		}
		return reponse;
	} */ 

	@ApiOperation(value="login", notes="Login user for My Favourite Books App")
	@PostMapping(path = AuthenticationConstants.LOGIN_PATH) 
	public ResponseEntity<?>  loginUser(@ApiParam(name="User", value="User", required=true) @RequestBody User user){
		ResponseEntity<String> reponse;
		try {

			if(null == user.getUserId() || user.getUserId().isEmpty() ||
					null == user.getPassword() || user.getPassword().isEmpty()	) {
				return new ResponseEntity<String>(AuthenticationConstants.USERID_MSG,HttpStatus.UNAUTHORIZED);
			}

			User loggedInUser = authService.getUserDetails(user.getUserId(), user.getPassword());

			Map<String, String> map = tokenGenerator.generateToken(loggedInUser);

			reponse = new ResponseEntity<>(map.get("token"),HttpStatus.OK);		

		}catch(UserNotAvailableException e) {
			return new ResponseEntity<String>(e.getErrorMessage(),HttpStatus.UNAUTHORIZED);
		}
		return reponse;
	}
	
	
	/*@PutMapping("/update/{id}")
	public ResponseEntity<?> updateUser(@PathVariable("id") final String userId,@RequestBody User user){
		try {
		User updateUser = authService.updateUser(user, userId);

		
		return new ResponseEntity<>(updateUser,HttpStatus.CREATED);
		}
		catch (Exception e) {
			return new ResponseEntity<String>("{\"message\":\"" + e.getMessage() + "\"}", HttpStatus.UNAUTHORIZED);
		}
	}*/
	@PutMapping("/update")
	public ResponseEntity<?> updateUserDetails(@RequestBody User user,HttpServletRequest request,
			HttpServletResponse response) {
		String authHeader = request.getHeader("authorization"); 
		String token = authHeader.substring(7);
		String userId = Jwts.parser().setSigningKey("bookApp").parseClaimsJws(token).getBody().getSubject();
		try {
				
			User updatedUser=new User();
		 updatedUser = authService.updateUser(user,userId);
		return new ResponseEntity<>(updatedUser,HttpStatus.CREATED);
		}
		catch (Exception e) {
			return new ResponseEntity<String>("{\"message\":\"" + e.getMessage() + "\"}", HttpStatus.UNAUTHORIZED);
		}
	}
	@GetMapping("/view") 
	public ResponseEntity<?> viewUserDetails(HttpServletRequest request,HttpServletResponse response) {
		String authHeader = request.getHeader("authorization");
		String token = authHeader.substring(7);
		String userId = Jwts.parser().setSigningKey("bookApp").parseClaimsJws(token).getBody().getSubject();
		try {	
			User viewUser=new User();
		 viewUser = authService.viewUser(userId);
		return new ResponseEntity<>(viewUser,HttpStatus.OK);
		}
		catch (Exception e) {
			return new ResponseEntity<String>("{\"message\":\"" + e.getMessage() + "\"}", HttpStatus.UNAUTHORIZED);
		}
	}





}
