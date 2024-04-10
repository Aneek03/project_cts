package com.aneek.book.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="posts")
@Getter
@Setter
@NoArgsConstructor
public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer postId;
	
	@Column(name="post_title", length =300, nullable = false)
	private String title;
	
	@Column(length = 10000)
	private String content;
	
	private String imageName;			
	
	private Date addedDate;
						        //The cascade= CascadeType.ALL part means that any operations performed on the Post entity,
	                          //such as persisting, removing, or updating, will be cascaded to the associated Comment entities.
	@ManyToOne
	@JoinColumn(name="category_id")
	private Category category;
	
	@ManyToOne
	private User user;
	                   // to make foreign key of comments table
	@OneToMany(mappedBy="post",cascade= CascadeType.ALL) 
	private Set<Comment> comments = new HashSet<>();
	
	//The @OneToMany annotation in Java specifies a one-to-many relationship between two entities. 
	//In this context, it indicates that a single Post entity can have multiple associated Comment entities. 
	//The mappedBy="post" attribute tells the persistence framework that the Post entity
	//is the inverse side of the relationship and the Comment entity owns the relationship.

}
