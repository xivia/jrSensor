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
	
	<!-- select station/sensor -->
	<form name="myForm" method="get" action="" accept-charset="UTF-8">
		Station: 
		<select name="station" onchange="submit();">
			<option value="0">Bitte Station wählen
			<c:forEach var="itemSta" items="${resultListStation}">
			<option<c:if test="${station == itemSta.id}"> selected</c:if> value="${itemSta.id}">${itemSta.name}
			</c:forEach>
		</select>
		<br />
		Sensor: 
		<select name="sensor" onchange="submit();">
			<option value="0">Bitte Sensor wählen
			<c:forEach var="itemSen" items="${resultListSensor}">
			<option<c:if test="${sensor == itemSen.id}"> selected</c:if> value="${itemSen.id}">${itemSen.name}
			</c:forEach>
		</select>
	</form>
	
	<!-- list data -->
	
	<br />
</body>
</html>