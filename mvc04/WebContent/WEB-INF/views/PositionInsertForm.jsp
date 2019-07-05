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
			if($("#departmentName").val()=="")
			{
				$("#err").html("필수 입력 항목이 누락되었습니다.");
				$("#err").css("display", "inline");
				return;
			}
			
			$("#positionForm").submit();
		});
	});
s
</script>
</head>
<body>

<div>
	
	<!-- 메뉴 영역 -->
	<div>
		<c:import url="EmployeeMenu.jsp"></c:import>
	</div>
	
	<div id="content">
		
		<h1>[ 직위 추가 ]</h1>
		<hr>
		
		<form action="positioninsert.action" method="post" id="positionForm">
			<table>
				<tr>
					<th>직위명</th>
					<td><input type="text" id="positionName" name="positionName" placeholder="직위명"></td>
				</tr>
				<tr>
					<th>최소기본급</th>
					<td><input type="text" id="minBasicPay" name="minBasicPay" placeholder="최소기본급"></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<br><br>
						<button type="button" class="btn" id="submitBtn" style="width: 40%;">직위 추가</button>
						<button type="button" class="btn" id="listBtn" style="width: 40%;" onclick="location.href='positionlist.action'">직위 리스트</button> 
						<br><br> 
						<span id="err" style="color: red; font-weight: bold; display: none;"></span>
					</td>
				</tr>
			</table>
		</form>
	
	</div>	
	
	<!-- 회사 소개 및 어플리케이션 소개 영역 -->
	<div id="footer"></div>
	
</div>

</body>
</html>