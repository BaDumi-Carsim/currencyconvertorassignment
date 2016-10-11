<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="cc"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/css/main.css"
	type="text/css" />
<script type="text/javascript" src="/currencyconvertorassignment/static/script/jquery.js"></script>
<title>Insert title here</title>
</head>
<body>

	

	<div style="color: red;">${errorAddCurrencyMsg}</div>
	<div style="color: red;">${errorMsg}</div>
	<cc:form method="post"
		action="${pageContext.request.contextPath}/currencymanagement"
		commandName="currencies">

		<table>
			<tr>
				<td>Currency:</td>
				<td><cc:input name="currency" id="currency" type="text" path="currency" /> <br />
					<cc:errors path="currency" cssClass="error"></cc:errors></td>
			</tr>

			<tr>
				<td>Currency Code:</td>
				<td><cc:input name="currencycode" id="currencycode" type="text" maxlength="3"
						path="currencyCode" /><br /> <cc:errors path="currencyCode"
						cssClass="error"></cc:errors></td>

			</tr>
			<tr>
				<td>Conversion Rate To USD:</td>
				<td><cc:input name="valueToUSD" id="valueToUSD" type="text" path="valueToUSD" /><br />
					<cc:errors path="valueToUSD" cssClass="error"></cc:errors></td>

			</tr>
			<tr>
				<td><input type="submit" value="Add Currrency"
					name="addCurrrency" /></td>
				

			</tr>
		</table>
	</cc:form>
	<p />
	<h3>Available Currencies</h3>
	<p />
	<table>
		<tr>
			<th>Currencies</th>
			<th>Value To USD</th>
			<th>Currency Code</th>
		</tr>
		<c:forEach var="cur" items="${currency}">
			<tr>
				<td>${cur.currency}</td>
				<td>${cur.valueToUSD}</td>
				<td>${cur.currencyCode}</td>
			</tr>
		</c:forEach>
	</table>
</body>

<script type="text/javascript">
 $(document).ready(function(){
	//alert("so")
	$('#reset').click(function(){
		$('#currency').val("");
		$('#valueToUSD').val("");
		$('#valueToUSD').val("");
		
		$("#reset").click(function() {
	        $("#currency").val("");
	});
    });
}); 
</script>

</html>