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
	<table>
	<tr>
		<td>Station: </td> 
		<td>
		<select name="station" onchange="myForm.sensor.selectedIndex = 0;submit();">
			<option value="0">Bitte Station wählen
			<c:forEach var="itemSta" items="${resultListStation}">
			<option<c:if test="${station == itemSta.id}"> selected</c:if> value="${itemSta.id}">${itemSta.name}
			</c:forEach>
		</select>
		</td>
	</tr>
	<tr>
		<td>Sensor: </td>
		<td> 
		<select name="sensor" onchange="submit();">
			<option value="0">Bitte Sensor wählen
			<c:forEach var="itemSen" items="${resultListSensor}">
			<option<c:if test="${sensor == itemSen.id}"> selected</c:if> value="${itemSen.id}">${itemSen.name}
			</c:forEach>
		</select>
		</td>
	</tr>
	</table>
	</form>
	
	<br />
	
	<!-- list data -->
	<table>
	<tr>
		<td>Wert</td>
		<td>&nbsp;</td>
		<td>Einheit</td>
	</tr>
	<c:forEach var="itemSenDat" items="${resultListSensorData}">
	<tr>	
		<td>${itemSenDat.value}</td><td>&nbsp;</td><td>${itemSenDat.datatype.unit}</td>
	</tr>
	</c:forEach>
	</table>
	
	<br />
</body>
</html>