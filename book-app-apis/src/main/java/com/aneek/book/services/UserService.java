// 3rd
package com.aneek.book.services;

import java.util.List;

import com.aneek.book.payloads.UserDto;

public interface UserService {


// we will not provide entity in the service thats why we will create class for data transfer
//in payloads package

UserDto createUser(UserDto user);

UserDto updateUser(UserDto user, Integer userId);

UserDto getUserById(Integer userId);

List<UserDto> getAllUsers();

void deleteUser(Integer userId);

}