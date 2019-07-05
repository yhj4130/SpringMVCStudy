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
<title>RegionList.jsp</title>
<link rel="stylesheet" href="<%=cp%>/css/main.css">
</head>
<body>
<!-- 
	#34. RegionList.jsp
	- 지역 데이터 출력 페이지(리스트 페이지)
	- 지역 추가 버튼 구성
	- 리스트 상에 수정 및 삭제 버튼 구성
 -->
 
<div>

	<!-- 메뉴 영역 -->
	<div>
		<c:import url="EmployeeMenu.jsp"></c:import>
	</div>

	<!-- 콘텐츠 영역 -->
	<div id="content">
		
		<h1>[ 지역 관리 ]</h1>
		<hr>
		
		<br><br>
		
		<!--
			REGIONID REGIONNAME  
		-->
		
		<table id="customers" class="table">
			<tr>
				<th>번호</th>
				<th>지역명</th>
			</tr>	
			
			<c:forEach var="region" items="${regionList }">
				<tr>
					<td>${region.regionId }</td>
					<td>${region.regionName }</td>
				</tr>
			</c:forEach>
			
		</table>
		
	</div>	
	
	<!-- 회사 소개 및 어플리케이션 소개 영역 -->
	<div id="footer">
	</div>
	
</div>

</body>
</html>