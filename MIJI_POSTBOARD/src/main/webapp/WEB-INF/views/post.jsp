<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>   
<!-- jstl 라이브러리 등록 --> 

<c:if test="${! empty mesg }">
	<script>
		alert('${mesg}');
	</script>
</c:if>
<%
if(session.getAttribute("mesg") != null){
	session.removeAttribute("mesg");
}
	
%>
<!DOCTYPE html>
<html>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>
<table border="1" style="width: 700px" align="center">
	<tr>
		<td colspan="3" align="center" style="background-color: pink; font-size: 30px;" >${postDtoByPostId.postTitle }</td>
	</tr>	
	<tr>
		<td colspan="3" style="font-size: 10px; ">
		<b>작성자 :</b> ${postDtoByPostId.nickname }( ${postDtoByPostId.userId } ) <br>
		<b>작성일자 :</b> ${postDtoByPostId.postDate }
		</td>
	</tr>
	<tr >
		<td colspan="3" align="center" style="font-size: 20px; ">${postDtoByPostId.postText }</td>
	</tr>	
	<tr>
		<td colspan="3" align="right">
		<a href="<%=request.getContextPath()%>/">글목록</a>
		<a href="login/updatePostUI?postId=${PostId }">수정</a>
		<a href="login/deletePost?postId=${PostId }">삭제</a>
		</td>
		
	</tr>
</table>




</body>
</html>