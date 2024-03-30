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
@GeneratedValue(strategy = GenerationType.SEQUENCE)
private int id;

@Column(name="user_name", nullable = false, length = 100)
private String name;
private String email;
private String password;
private String about;

@OneToMany(mappedBy ="user", cascade = CascadeType.ALL, fetch= FetchType.LAZY)
private List<Post> posts = new ArrayList<>();

//to make foreign key of comments table
	@OneToMany(mappedBy="user",cascade= CascadeType.ALL,fetch= FetchType.LAZY)
	private Set<Comment> comments = new HashSet<>();

}
