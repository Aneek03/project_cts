//2nd

package com.aneek.book.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aneek.book.entities.User;


public interface UserRepo extends JpaRepository<User, Integer>{
// Jpa repository will provide us all the functionalities and database operation on User


}