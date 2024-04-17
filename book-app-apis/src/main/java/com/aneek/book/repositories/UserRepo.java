//2nd

package com.aneek.book.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aneek.book.entities.User;

										// 3rd party orm tool
public interface UserRepo extends JpaRepository<User, Integer>{
// Jpa repository will provide us all the functionalities and database operation on User

	Optional<User> findByEmail(String email);
	
	
}