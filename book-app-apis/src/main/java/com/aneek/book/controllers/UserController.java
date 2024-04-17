// 7th

package com.aneek.book.controllers;   
import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aneek.book.payloads.ApiResponse;
import com.aneek.book.payloads.UserDto;
import com.aneek.book.services.UserService;


import jakarta.validation.Valid;

@RestController // @Controller(makes web controller) + @ResponseBody(return method object in json)
@RequestMapping("/api/users")// used to http map web resquest
public class UserController {

@Autowired
private UserService userService;

//POST - create user
//@PostMapping("/")  // Response entity used to represent HTTP response
//
//public ResponseEntity<UserDto>  createUser(@Valid @RequestBody UserDto userDto){
//
//UserDto createUserDto = this.userService.createUser(userDto);
//
//return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
//}

//PUT - update user

@PutMapping("/{userId}") // is called path URI variable  
public ResponseEntity<UserDto> updateUser(@Valid @RequestBody  UserDto userDto, @PathVariable Integer userId) 
// @Valid used to validate method parameter
// user details will received with the help of @Requestbody
//@RequestBody indicates the parameter should be bound to the body of the web request
{                                                                                                                                                                                                             // and stored in userDto.    
         
                    // @PathVariable denotes that the userId is to be taken from the URL path.

   UserDto updatedUser = this.userService.updateUser(userDto, userId);
   
return ResponseEntity.ok(updatedUser);        // ok status will be sent          
}


//DELETE -  delete user
 @DeleteMapping("/{userId}") 
 //@PreAuthorize("hasRole('ROLE_ADMIN')")
public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer userId)
{                              // ? used to denote any type
 
 this.userService.deleteUser(userId);
 
 return new ResponseEntity<ApiResponse> (new ApiResponse("User deleted successfully ", true), HttpStatus.OK);
  // ApiResponse class object (8th class) to sent api response message to users
}



//GET - All users get
 @GetMapping("/")
 public ResponseEntity<List <UserDto>> getAllUsers()
 {
 return ResponseEntity.ok(this.userService.getAllUsers());
 }
 
 //GET - Single user get
 @GetMapping("/{userId}")
 public ResponseEntity<UserDto> getSingleUsers(@PathVariable Integer userId)
 {
 return ResponseEntity.ok(this.userService.getUserById(userId));
 }
 
// register new user api
 @PostMapping("/")
 public ResponseEntity<UserDto> registerUser(@RequestBody UserDto userDto){
	 
	 UserDto registerUser = this.userService.registerNewUser(userDto);
	 
	 return new ResponseEntity<UserDto>(registerUser,HttpStatus.CREATED);
 }
 
// @GetMapping("/{email}")
//	public ResponseEntity<UserDto> getUserByEmail(@PathVariable String email) {
//	 return ResponseEntity.ok(this.userService.getUserByEmail(email));
//	}
// 
 
 @GetMapping("/email/{email}")
 public ResponseEntity<UserDto> getUserByEmail(@PathVariable String email) {
     UserDto userDto = userService.getUserByEmail(email);
     return new ResponseEntity<>(userDto, HttpStatus.OK);
 }
 
 @PostMapping("/login")
 public ResponseEntity<UserDto> loginUser(@RequestParam String email, @RequestParam String password){
     try {
         UserDto loggedInUser = this.userService.loginUser(email, password);
         return new ResponseEntity<>(loggedInUser, HttpStatus.OK);
     } catch (RuntimeException e) {
         return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
     }
 }
 
 
}