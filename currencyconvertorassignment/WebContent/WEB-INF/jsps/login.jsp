<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="cc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/css/main.css"
	type="text/css" />
<title>Insert title here</title>
</head>

<body>

	<p>logging in to the</p>
	<div style=color:red;>${errorMsg}</div>

	<cc:form method="post" action="${pageContext.request.contextPath}/convertcurrency"
		commandName="login" >

		<table>
			<tr>
				<td>Email:</td>
				<td><cc:input name="email" type="text" path="email"/> 
						 <br />  <cc:errors path="email"
						cssClass="error"></cc:errors></td> 
			</tr>

			<tr>
				<td>Password:</td>
				<td><cc:input name="password" type="password" path="password"/><br />  <cc:errors path="password"
						cssClass="error"></cc:errors></td> 

			</tr>
			<tr>
				<td colspan="3"><input type="submit" value="Sign In"
					name="login" /></td>
			</tr>
		</table>
	</cc:form>

	<button onclick="location.href='http://www.java.com'">Sign Up</button>


</body>
</html>