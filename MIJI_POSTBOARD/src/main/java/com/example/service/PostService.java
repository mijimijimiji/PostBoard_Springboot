package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.PostDAO;
import com.example.dto.PostDTO;

@Service
public class PostService {
	
	@Autowired
	PostDAO dao;

	public PostService() {
		// TODO Auto-generated constructor stub
	}

	public List<PostDTO> selectAll() {
		
		return dao.selectAll();
	}

	public PostDTO selectOneByPostId(Long postId) {
		// TODO Auto-generated method stub
		return dao.selectOneByPostId(postId);
	}

	public Long selectOnePostIdFromDual() {
		// TODO Auto-generated method stub
		return dao.selectOnePostIdFromDual();
	}

	public void insertPost(PostDTO postDTO) {
		dao.insertPost(postDTO);
		
	}

	public void deletePostByPostId(Long postId) {
		// TODO Auto-generated method stub
		dao.deletePostByPostId(postId);
		
	}

	public void updatePost(PostDTO postDTO) {
		dao.updatePost(postDTO);
		
	}

	
	

}
