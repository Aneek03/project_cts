package com.aneek.book.services;

import com.aneek.book.payloads.CommentDto;
import com.aneek.book.payloads.UserDto;

public interface CommentService {

	CommentDto createComment(CommentDto commentDto,Integer postId,Integer userId);
	
	void deleteComment(Integer commentId);
}
