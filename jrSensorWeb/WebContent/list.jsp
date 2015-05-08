<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta charset="utf-8" />
<title>jrSensor</title>
</head>
<body>
	<h2>jrSensor</h2>
	<br />
	
	<table>
	<tr>
		<td>key</td>
		<td>&nbsp;</td>
		<td>name</td>
		<td>&nbsp;</td>
		<td>description</td>
	</tr>
	<c:forEach var="item" items="${resultList}">
  		<tr>
 	 		<td>${item.id}</td>
 	 		<td>&nbsp;</td>
  			<td>${item.name}</td>
  			<td>&nbsp;</td>
  			<td>${item.description}</td>
  		</tr>
	</c:forEach>
	</table>
	
	<br />
</body>
</html>