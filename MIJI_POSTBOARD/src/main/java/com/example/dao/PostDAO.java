package com.example.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.dto.PostDTO;

@Component
public class PostDAO {
	
	@Autowired
	SqlSessionTemplate session;

	public PostDAO() {
		// TODO Auto-generated constructor stub
	}

	public List<PostDTO> selectAll() {
		
		return session.selectList("selectAll");
	}

	public PostDTO selectOneByPostId(Long postId) {
		// TODO Auto-generated method stub
		return session.selectOne("selectOneByPostId", postId);
	}

	public Long selectOnePostIdFromDual() {
		// TODO Auto-generated method stub
		return session.selectOne("selectOnePostIdFromDual");
	}

	public void insertPost(PostDTO postDTO) {
		session.insert("insertPost", postDTO);
		
	}

	public void deletePostByPostId(Long postId) {
		// TODO Auto-generated method stub
		
		int num = session.delete("deletePostInfo",postId);
		if(num==1) {
		session.delete("deletePostByPostId",postId);
		}else {
			System.out.println("==POST 삭제 실패==");
		}
	}

	public void updatePost(PostDTO postDTO) {
		session.update("updatePost", postDTO);
		
	}

	
}
