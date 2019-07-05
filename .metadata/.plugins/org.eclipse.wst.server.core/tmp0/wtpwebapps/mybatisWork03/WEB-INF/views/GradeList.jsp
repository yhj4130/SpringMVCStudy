<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setCharacterEncoding("UTF-8");
	String cp = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>StudentList.jsp</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script type="text/javascript">

	//$(document).ready();
	$(function()
	{
		$(".updateBtn").click(function()
		{
			// 테스트
			//alert("수정 버튼 클릭");
			
			$(location).attr("href", "gradeupdateform.action?sid=" + $(this).val());
		});
		
		$(".deleteBtn").click(function()
		{
			// 테스트
			//alert("삭제 버튼 클릭");
			
			if(confirm("현재 선택한 데이터를 정말 삭제하시겠습니까?"))
			{
				$(location).attr("href", "gradedelete.action?sid=" + $(this).val());	
			}

		});
	});

</script>

</head>
<body>


<div>
	<h1>성적 목록</h1>
	<hr>
	<h2><a href="studentlist.action">학생 명단</a></h2>
</div>

<div>
	<h2>총 ${count } 명</h2>
</div>

<div>
	<table>
		<tr>
			<th>아이디</th>
			<th>이름</th>
			<th>과목1</th>
			<th>과목2</th>
			<th>과목3</th>
			<th>총점</th>
			<th>평균</th>
			<th>결과</th>
			<th>수정</th>
			<th>삭제</th>
			
		</tr>
		<c:forEach var="grade" items="${list }">
			<tr>
				<td>${grade.sid }</td>
				<td>${grade.name }</td>
				<td>${grade.sub1 }</td>
				<td>${grade.sub2 }</td>
				<td>${grade.sub3 }</td>
				<td>${grade.tot }</td>
				<td>${grade.avg }</td>
				<td>${grade.ch }</td>
				<td><button type="button" class="btn updateBtn" value="${grade.sid }">수정</button></td>
				<td><button type="button" class="btn deleteBtn" value="${grade.sid }">삭제</button></td>
		</c:forEach>
		
	</table>
</div>
</body>
</html>