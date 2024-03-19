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
<head>
<style type="text/css" >
	
	h1{
		background-color: yellow;
	}
	
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1 align="center">글목록</h1>
<table border="1"  align="center">
	<tr>
		<th>글번호</th>
		<th>게시판</th>
		<th>작성자</th>
		<th>글제목</th>
		<th>작성일자</th>
		<th>수정일자</th>
	</tr>
	
<c:forEach items="${postdtoList }" var="postDTO" varStatus="idx">
	<tr>
	<td>${postDTO.postId }</td>
	<td>${postDTO.postBoard }</td>
	<td>${postDTO.nickname }( ${postDTO.userId } )</td>
	<td><a href="post?postId=${postDTO.postId }">${postDTO.postTitle }</a></td>
	<td>${postDTO.postDate }</td>
	<td>${postDTO.postEditDate }</td>
	</tr>
</c:forEach>
	<tr>
		<td colspan="6" align="right" ><a href="addPostUI">글쓰기</a></td>
	</tr>
</table>


</body>
</html>