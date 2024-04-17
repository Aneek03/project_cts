// 1st
package com.aneek.book.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.*;

@Entity
@Table(name="users")
@NoArgsConstructor
@Getter
@Setter
public class User{

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
	
	
	@ManyToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinTable( name = "user_role",
	joinColumns = @JoinColumn(name="user", referencedColumnName = "id"),
	inverseJoinColumns = @JoinColumn(name="role", referencedColumnName = "id")
			)
	private Set<Role> roles = new HashSet<>();

	
	
//	
//	@Override
//	public Collection<? extends GrantedAuthority> getAuthorities() {
//		// TODO Auto-generated method stub
//		
//		List<SimpleGrantedAuthority> authories = this.roles.stream().map((role)-> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
//		
//		return authories;
//	}
//
//	@Override
//	public String getUsername() {
//		// TODO Auto-generated method stub
//		return this.email;
//	}
//
//	@Override
//	public boolean isAccountNonExpired() {
//		// TODO Auto-generated method stub
//		return true;
//	}
//
//	@Override
//	public boolean isAccountNonLocked() {
//		// TODO Auto-generated method stub
//		return true;
//	}
//
//	@Override
//	public boolean isCredentialsNonExpired() {
//		// TODO Auto-generated method stub
//		return true;
//	}
//
//	@Override
//	public boolean isEnabled() {
//		// TODO Auto-generated method stub
//		return true;
//	}
	
	
	
	
	
	
	//In JPA (Java Persistence API), FetchType.LAZY means that the comments associated 
	//with a user will not be loaded immediately. They will be fetched from the database
	//only when they are specifically accessed in the code
}
