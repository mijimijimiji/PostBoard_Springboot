package com.example.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.example.dto.MemberDTO;
import com.example.dto.PostDTO;
import com.example.editer.PhotoUtil;
import com.example.service.PostService;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class MainController {
	
	@Autowired
	PostService service;

	public MainController() {
		// TODO Auto-generated constructor stub
	}
	
	
	//글 목록 보기
	@RequestMapping(value="/")
	public String main(Model m, HttpSession session) {
		
		
		//혹시 필요할까 싶어서 세팅
		MemberDTO login = new MemberDTO();
		login.setNickname("이쁜이");
		login.setUserId("kmj3718");
		session.setAttribute("login", login); // 로그인정보 세팅
		session.setAttribute("postBoard", "tv"); //게시판 세팅
		
		List<PostDTO> list = service.selectAll();
		System.out.println(list);
		m.addAttribute("postdtoList", list);
		
		return "main"; //뷰리졸버 동작
	}
	
	
	
	//글 자세히 보기
	@RequestMapping(value="/post")
	public String post(Long postId, Model m) {
		//System.out.println(postId); //파싱 잘 됐는지 확인 완료
		
		PostDTO postDTO = service.selectOneByPostId(postId);
		// System.out.println(">>>"+postDTO); //쿼리 정상 작동 확인 완료
		m.addAttribute("postDtoByPostId", postDTO);
		m.addAttribute("PostId", postId);
		
		return "post"; //뷰리졸버 동작
	}

	
	//글작성 페이지로 이동하기. postId필요해서 select 날려서 안고 감
	@RequestMapping(value="/addPostUI")
	public String addPostUI(Model m) {
		Long postId = service.selectOnePostIdFromDual();
		//System.out.println(":::::"+postId); 확인 완료
		m.addAttribute("postId", postId);
		return "addPost"; //뷰리졸버 동작
	}
	
	
	//글 DB에 insert 진행되는 부분
	@RequestMapping(value="/addPost")
	public String addPostUI(PostDTO postDTO) {
		System.out.println("addPost 하기 위해 " + postDTO);
		service.insertPost(postDTO);
		return "redirect:/post?postId="+postDTO.getPostId(); 
	}
	
	
	//post ID로 글 삭제되는 부분		
	@RequestMapping(value="/login/deletePost")
	public String deletePost (Long postId, HttpSession session) {
		//System.out.println("deletePost 실행==================="); 확인완료
		
		service.deletePostByPostId(postId);
		
		session.setAttribute("mesg", "글이 삭제되었습니다.");
		return "redirect:/"; //다시 메인으로 돌아가기
	}		
	
	
	//이미지 업로드 시 글쓰는 장면에 이미지 뿌려지게
	//uploaded와 url을 json으로 받아야 함
	//ModelAndView를 사용하여 json 형식으로 보냄
	@PostMapping("/upload")
    public ModelAndView upload(MultipartHttpServletRequest request) {
        ModelAndView mav = new ModelAndView("jsonView");

        String uploadPath = PhotoUtil.ckUpload(request);
        
        mav.addObject("uploaded", true);
        mav.addObject("url", uploadPath);
        return mav;
    }
	
	
	//글수정되는 화면으로 가는 UI 
	@RequestMapping(value="/login/updatePostUI")
	public String updatePostUI(Long postId, Model m) {
		//URL GET방식으로 넘어온 postId를 자동파싱, 그것으로 DTO 뽑기
		PostDTO postDTO = service.selectOneByPostId(postId);
		m.addAttribute("postDTO", postDTO);
		
		return "updatePost"; //뷰리졸버 동작
	}
	
	@RequestMapping(value="updatePost")
	public String updatePost(PostDTO postDTO,HttpSession session) {
		service.updatePost(postDTO);
		Long postId = postDTO.getPostId();
		session.setAttribute("mesg", "글 수정이 완료되었습니다.");
		return "redirect:/post?postId="+postId;
	}
	
	
	//혹시나 싶을 때 쓰는 test용
//	@RequestMapping("/test")
//	public String text () {
//		return "test";
//	}
	

	
	
	
	
	
	
	
}
