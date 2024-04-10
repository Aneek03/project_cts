// 1st
package com.aneek.book.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.*;

@Entity
@Table(name="users")
@NoArgsConstructor
@Getter
@Setter
public class User {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;

@Column(name="user_name", nullable = false, length = 100)
private String name;
private String email;
private String password;
private String about;

@OneToMany(mappedBy ="user", cascade = CascadeType.ALL, fetch= FetchType.LAZY)
private List<Post> posts = new ArrayList<>();

// cascade= CascadeType.ALL means that any changes made to the User entity will be reflected in the posts entities

//to make foreign key of comments table


	@OneToMany(mappedBy="user",cascade= CascadeType.ALL,fetch= FetchType.LAZY)
	private Set<Comment> comments = new HashSet<>();

	//cascade= CascadeType.ALL means that any changes made to the User entity will be reflected in the Comment entities
	
	//fetch= FetchType.LAZY means the child entities will be loaded on demand, not immediately
	
	//fetch= FetchType.LAZY means that the Comment entities will be loaded only when they are explicitly accessed.
	
	
	
//For example, if you have a User entity with many Comment entities associated with it, 
//using FetchType.LAZY for the comments means that JPA won’t load all the comments from the database when you retrieve a User. 
//Instead, the comments will only be loaded when you actually access them, like calling user.getComments().
	
	
	
	
	
	
	
	
	
	//In JPA (Java Persistence API), FetchType.LAZY means that the comments associated 
	//with a user will not be loaded immediately. They will be fetched from the database
	//only when they are specifically accessed in the code
}
