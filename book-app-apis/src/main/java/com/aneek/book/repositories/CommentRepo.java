package com.aneek.book.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aneek.book.entities.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer>{
	
	
}





















//extends JpaRepository: This indicates that CommentRepo is extending the JpaRepository interface, 
	//which comes with a set of CRUD (Create, Read, Update, Delete) methods.
	//<Comment, Integer>: These are generic type parameters where:
	//Comment: Represents the entity type the repository will manage.
	//Integer: Represents the type of the primary key of the entity.
	//By passing Comment and Integer as arguments, 
	//you’re specifying that the repository will manage Comment entities and that the primary key of Comment is of type Integer
