<%@page import="com.tg.mymember.MyMemberDto"%>
<%@page import="java.util.ArrayList"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<jsp:include page="/Header.jsp"/>
	
	<h1>회원목록</h1>
	<div>
		<a href="./add">신규 회원</a>
	</div>
	<br/>
	
	<%
		ArrayList<MyMemberDto> myMemberList = 
			(ArrayList<MyMemberDto>)request.getAttribute("myMemberList");
	
		for(MyMemberDto myMemberDto : myMemberList){
	%>
	
	<%=myMemberDto.getNo()%>,
	<a href='./update?no=<%=myMemberDto.getNo()%>'><%=myMemberDto.getName()%></a>,
	<%=myMemberDto.getEmail()%>,
	<%=myMemberDto.getCreateDate()%>
	<a href='./delete?no=<%=myMemberDto.getNo()%>'>[삭제]</a>
	<br>
	
	<%
		}
	%>
	
	<jsp:include page="/Tail.jsp"/>
	

</body>
</html>