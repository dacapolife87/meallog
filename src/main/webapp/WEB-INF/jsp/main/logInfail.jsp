<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  

<%@ include file="/WEB-INF/include/include-header.jspf" %>
  <!-- Theme Made By www.w3schools.com - No Copyright -->
  <title>Meal Login Fail</title>

</head>
<body>
<h1>Login Fail.</h1>
</body>
<script type="text/javascript">
  $(document).ready(function(){
    	alert("ID 또는 PASSWROD 가 잘되되었습니다. \n 다시 시도해 주세요");
    	location.href='/meallog/main.do'
  });
</script>
</html>
