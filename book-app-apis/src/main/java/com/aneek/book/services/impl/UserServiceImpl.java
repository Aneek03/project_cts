// 5th

package com.aneek.book.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.aneek.book.exceptions.*;
import com.aneek.book.config.AppConstants;
import com.aneek.book.entities.Role;
import com.aneek.book.entities.User;
import com.aneek.book.payloads.UserDto;
import com.aneek.book.repositories.RoleRepo;
import com.aneek.book.repositories.UserRepo;
import com.aneek.book.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private ModelMapper modelMapper;
	
	//@Autowired
	//private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleRepo roleRepo;

	@Override
	public UserDto createUser(UserDto userDto) {

		User user = this.dtoToUser(userDto);

		User savedUser = this.userRepo.save(user);

		return this.userToDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {

//		User user = this.userRepo.findById(userId) // when the userId is not present in db
//				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
		
		User user = null;
		Optional<User> userOptional = this.userRepo.findById(userId);
		if (userOptional.isPresent()) {
		    user = userOptional.get();
		} else {
		    throw new ResourceNotFoundException("User", "Id", userId);
		}

		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());

		User updatedUser = this.userRepo.save(user);
		UserDto userDto1 = this.userToDto(updatedUser);

		return userDto1;
	}

	@Override
	public UserDto getUserById(Integer userId) {

		User user = this.userRepo.findById(userId) // when the userId is not present in db
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
		
		//.orElseThrow():This is a method provided by Java’s Optional class.
		//If findById finds a user, it returns an Optional containing the user. 
		//If no user is found, an empty Optional is returned. 

		return this.userToDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {

		List<User> users = this.userRepo.findAll();

		//List<UserDto> userDtos = users.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());
		// it will return one by one user and we will put in the list
		
		
		List<UserDto> userDtos = new ArrayList<>();
		for (User user : users) {
		    userDtos.add(this.userToDto(user));
		}
		

		return userDtos;
	}

	@Override
	public void deleteUser(Integer userId) {

		User user = this.userRepo.findById(userId) // when the userId is not present in db
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));

		this.userRepo.delete(user);

	}

	public User dtoToUser(UserDto userDto) {

		User user = this.modelMapper.map(userDto, User.class);
									//userDto is the source and User.class is the destination
		
		//User user = new User();
//		user.setId(userDto.getId());
//		user.setName(userDto.getName());
//		user.setEmail(userDto.getEmail());
//		user.setPassword(userDto.getPassword());
//		user.setAbout(userDto.getAbout());

		return user;
	}

	public UserDto userToDto(User user) {
		
		
		UserDto userDto = this.modelMapper.map(user, UserDto.class);

//		UserDto userDto = new UserDto();
//
//		userDto.setId(user.getId());
//		userDto.setName(user.getName());
//		userDto.setEmail(user.getEmail());
//		userDto.setPassword(user.getPassword());
//		userDto.setAbout(user.getAbout());

		return userDto;
	}

	@Override
	public UserDto registerNewUser(UserDto userDto) {
		// TODO Auto-generated method stub
		
		User user = this.modelMapper.map(userDto, User.class);
		
		//encoded the password
		//user.setPassword(this.passwordEncoder.encode(user.getPassword()));
		
		//user.setPassword(this.passwordEncoder.encode(user.getPassword()));
		
		//roles 
		Role role =this.roleRepo.findById(AppConstants.NORMAL_USER).get();
		
		user.getRoles().add(role);
		
		User newUser = this.userRepo.save(user);
		
		return this.modelMapper.map(newUser, UserDto.class);
	}

	@Override
	public UserDto getUserByEmail(String email) {
		
		User user = this.userRepo.findByEmail(email) // when the userId is not present in db
				.orElseThrow(() -> new ResourceNotFoundException("User", "email "+email, 0));
		
		
		return this.userToDto(user);
	}

	@Override
	public UserDto loginUser(String email, String password) {
		
		User user = this.userRepo.findByEmail(email) // when the userId is not present in db
				.orElseThrow(() -> new ResourceNotFoundException("User", "email "+email, 0));
		
		if(user != null && user.getPassword().equals(password)) {
	        // User is authenticated
	        return this.modelMapper.map(user, UserDto.class);
	    } else {
	        // User authentication failed
	        throw new RuntimeException("Invalid username or password");
	    }
	}

}