package com.aneek.book.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aneek.book.entities.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer>{

}
