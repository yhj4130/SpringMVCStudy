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
			
			$(location).attr("href", "studentupdateform.action?sid=" + $(this).val());
		});
		
		$(".deleteBtn").click(function()
		{
			// 테스트
			//alert("삭제 버튼 클릭");
			
			if(confirm("현재 선택한 데이터를 정말 삭제하시겠습니까?"))
			{
				$(location).attr("href", "studentdelete.action?sid=" + $(this).val());	
			}

		});
		
		$(".gradeBtn").click(function()
		{
			$(location).attr("href", "gradeinsertform.action?sid=" + $(this).val());	
		});

	});

</script>

</head>
<body>


<div>
	<h1>학생 명단</h1>
	<hr>
	<h2><a href="studentinsertform.action">학생 등록</a></h2>
	<h2><a href="gradelist.action">성적 목록</a></h2>
</div>

<div>
	<h2>총 ${count } 명</h2>
</div>

<div>
	<table>
		<tr>
			<th>아이디</th>
			<th>이름</th>
			<th>전화번호</th>
			<th>수정</th>
			<th>삭제</th>
			<th>성적입력</th>
			
		</tr>
		<c:forEach var="student" items="${list }">
			<tr>
				<td>${student.sid }</td>
				<td>${student.name }</td>
				<td>${student.tel }</td>
				<td><button type="button" class="btn updateBtn" value="${student.sid }">수정</button></td>
				<td><button type="button" ${student.sub==0 ? "class='btn deleteBtn'" : "class=='disable'" }
					${student.sub==0? "" : "disabled='disabled'" } value="${student.sid }"> 삭제</button></td>
				<td><button type="button" class="btn gradeBtn" value="${student.name }" >성적입력</button></td>
			</tr>
		</c:forEach>
		
	</table>
</div>
</body>
</html>