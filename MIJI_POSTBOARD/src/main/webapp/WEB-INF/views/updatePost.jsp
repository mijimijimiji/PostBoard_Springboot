<%@page import="com.example.dto.MemberDTO"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style>
  .ck-editor__editable { height: 400px; }
  .ck-content { font-size: 12px; }
</style>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdn.ckeditor.com/ckeditor5/41.0.0/classic/ckeditor.js"></script> <!-- //ckeditor 사용을 위한 js -->
<script src="https://cdn.ckeditor.com/ckeditor5/41.0.0/classic/translations/ko.js"></script> <!-- 한글 설정 -->
<script src="https://ckeditor.com/apps/ckfinder/3.5.0/ckfinder.js"></script> <!-- 이미지 업로드 어댑터   -->

<script type="text/javascript">
	<%
	// 테스트용 저장 session 정보 활용. /주소 처리 컨트롤러에 있음
	MemberDTO login = (MemberDTO)session.getAttribute("login");
	Long postId = (Long)request.getAttribute("postId");
	String postBoard = (String)session.getAttribute("postBoard");
	
	%>
	
	$(document).ready(function () {
		
		
		 let myEditor;
		
		 ClassicEditor
		 .create(document.querySelector( '#postText' ), {
			 ckfinder: {
					uploadUrl: '<%=request.getContextPath()%>/upload'
				}
		 } ).then(editor => {
	            myEditor = editor;
	            console.log('Editor was initialized');
	        })
	        .catch(error => {
	            console.error(error);
	        });
		 //CKEditor를 textarea와 연결했음. 그래서 id가 textarea의 ID임
		 

			function MyUploadAdapter(loader) {
		   	 // 업로더 초기화 코드...
		 	   this.loader = loader;
		  	  // 이미지를 업로드하고, 서버로부터 응답 받은 후 응답형 이미지 처리를 위한 로직 구현...
			}
		 
		 
		
			 $("#back").on("click", function(){
				 
				 event.preventDefault();
				 
				 if(confirm("정말 취소하시겠습니까 ?") == true){        
					 alert("글 수정이 취소되었습니다");
				     }else{        
					 return ;
				     }
				 //진짜 취소할건지 한 번 더 확인
				 
				 $("#updatePostForm").attr("action","/app").submit();
				//취소버튼 눌렸을 때 글작성 취소하고 main으로 이동
				
			 })
		 

	});
	
	

</script>
</head>
<body>
<h2>글 수정하기</h2>
<hr>
<form id="updatePostForm" action="<%=request.getContextPath() %>/updatePost" enctype="multipart/form-data">

<input type="hidden" name="postId" value="${postDTO.postId }">
<input type="hidden" name="postBoard" value="${postDTO.postBoard }">
<input type="hidden" name="userId" value="${postDTO.userId }">
<input type="hidden" name="nickname" value="${postDTO.nickname }">
<!--<input type="hidden" name="postDate" value="">   --> 
<!-- <input type="hidden" name="contId" value=" ">  null
<input type="hidden" name="postEditDate" value=" "> null //mapper에서 sysdate처리
<input type="hidden" name="categoryId" value=" "> null -->


<table border="1" style="width: 700px" align="center">
	<tr>
		<td colspan="3" align="center">
			<input type="text" name="postTitle" placeholder="제목을 입력하세요." style="width: 700px" value="${postDTO.postTitle }">
		</td>
	</tr>	
	<tr>
		<td><textarea style="width: 700px; height: 400px;" placeholder="내용을 입력하세요." name="postText" id="postText">${postDTO.postText }</textarea></td>
	</tr>
	<tr>
	<td colspan="3" align="center">
	<input type="submit" id="add" value="수정"></input>
	<input type="submit" id="back" value="취소"></input>
	<!-- 기능 추가)))) 취소 시 괜찮겠냐고 물어보기  -->
	</td>
	</tr>
</table>


</form>

</body>
</html>