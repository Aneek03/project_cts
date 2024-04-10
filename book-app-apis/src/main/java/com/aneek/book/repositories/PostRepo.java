package com.aneek.book.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.aneek.book.entities.Category;
import com.aneek.book.entities.Post;
import com.aneek.book.entities.User;

public interface PostRepo extends JpaRepository<Post, Integer>{
	
	
	//Spring query methods
	List<Post> findByUser(User user);
	List<Post> findByCategory(Category category);
	
	List<Post> findByTitleContaining(String title);
	
	
	

	@Query("select p from Post p where p.title like :key")
	
	List<Post> searchByTitle(@Param("key") String title);
	
	//@Query(value = "SELECT * FROM post WHERE title LIKE :key", nativeQuery = true)
	//List<Post> findPostsByTitleLike(@Param("key") String title);

}
