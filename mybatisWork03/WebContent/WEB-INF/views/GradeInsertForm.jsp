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
			if($("#sub1").val()=="" || $("#sub2").val()=="" || $("#sub3").val()=="")
			{
				$("#err").html("필수 입력 항목이 누락되었습니다.");
				$("#err").css("display", "inline");
				return;
			}
			
			$("#gradeForm").submit();
		});
	});
s
</script>
</head>
<body>

<div>
	<h1>성적 입력</h1>
	<hr>
	<!-- <h2><a href="memberinsertform.action">회원 추가</a></h2> -->
</div>

<div>	
	<form action="gradeinsert.action" method="post" id="gradeForm">
		<table>
			<tr>
				<th>아이디</th>
				<td><input type="text" id="sid" name="sid" value="${sid }" readonly="readonly"></td>
			</tr>
			<tr>
				<th>과목1</th>
				<td><input type="text" id="sub1" name="sub1" ></td>
			</tr>
			<tr>
				<th>과목2</th>
				<td><input type="text" id="sub2" name="sub2" ></td>
			</tr>
			<tr>
				<th>과목3</th>
				<td><input type="text" id="sub3" name="sub3" ></td>
			</tr>
			<tr>
			
				<td colspan="2" align="center">
					<br><br>
					<button type="button" class="btn" id="submitBtn" style="width: 40%;">성적 입력</button>
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