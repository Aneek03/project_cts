// 7th

package com.aneek.book.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aneek.book.payloads.ApiResponse;
import com.aneek.book.payloads.UserDto;
import com.aneek.book.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

@Autowired
private UserService userService;

//POST - create user
@PostMapping("/")
public ResponseEntity<UserDto>  createUser(@Valid @RequestBody UserDto userDto){

UserDto createUserDto = this.userService.createUser(userDto);

return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
}

//PUT - update user

@PutMapping("/{userId}") // is called path URI variable  
public ResponseEntity<UserDto> updateUser(@Valid @RequestBody  UserDto userDto, @PathVariable Integer userId) // user details will received with the help of @Requestbody
{                                                                                                                                                                                                             // and stored in userDto.    
         
                    // @PathVariable will help the fetch the data send by URI variable

   UserDto updatedUser = this.userService.updateUser(userDto, userId);
   
return ResponseEntity.ok(updatedUser);        // ok status will be sent          
}


//DELETE -  delete user
 @DeleteMapping("/{userId}")                      
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
}