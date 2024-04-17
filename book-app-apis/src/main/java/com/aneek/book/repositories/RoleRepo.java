package com.aneek.book.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aneek.book.entities.Role;

public interface RoleRepo extends JpaRepository<Role, Integer>{

}
