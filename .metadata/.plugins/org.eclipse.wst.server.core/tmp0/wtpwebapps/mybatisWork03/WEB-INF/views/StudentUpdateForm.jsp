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
<title>RegionInsertForm.jsp</title>
<link rel="stylesheet" href="<%=cp%>/css/main.css">
<link rel="stylesheet" href="<%=cp%>/css/jquery-ui.css">

<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script type="text/javascript" src="<%=cp%>/js/jquery-ui.js"></script>
<script type="text/javascript">
	
	$(document).ready(function()
	{
		$("#submitBtn").click(function()
		{
			if($("#name").val()=="" || $("#tel").val()=="")
			{
				$("#err").html("필수 입력 항목이 누락되었습니다.");
				$("#err").css("display", "inline");
				return;
			}
			
			$("#studentForm").submit();
		});
	});
s
</script>
</head>
<body>

<div>
	<h1>학생 수정</h1>
	<hr>
	<!-- <h2><a href="memberinsertform.action">회원 추가</a></h2> -->
</div>

<div>	
	<form action="studentupdate.action" method="post" id="studentForm">
		<table>
			<tr>
				<th>아이디</th>
				<td><input type="text" id="sid" name="sid" value="${search.sid }" readonly="readonly"></td>
			</tr>
			<tr>
				<th>이름</th>
				<td><input type="text" id="name" name="name" value="${search.name }"></td>
			</tr>
			<tr>
				<th>전화번호</th>
				<td><input type="text" id="tel" name="tel" value="${search.tel }"></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<br><br>
					<button type="button" class="btn" id="submitBtn" style="width: 40%;">학생 수정</button>
					<button type="button" class="btn" id="listBtn" style="width: 40%;" onclick="location.href='studentlist.action'">학생 리스트</button> 
					<br><br> 
					<span id="err" style="color: red; font-weight: bold; display: none;"></span>
				</td>
			</tr>
		</table>
	</form>
</div>

</body>
</html>