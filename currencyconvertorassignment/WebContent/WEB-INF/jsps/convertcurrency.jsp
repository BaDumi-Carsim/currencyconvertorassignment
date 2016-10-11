<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="/currencyconvertorassignment/static/script/jquery.js"></script>



<!-- <script type="text/javascript"> function returnedData(){
	$.getJSON("<c:url value = '/convertcurrency'/>", convertedCurrency);
	}
	
	function convertedCurrency(data){
		alert(data.convertedValue);
		
	}
  $(document).ready(returnedData); 
</script> -->

<title>Insert title here</title>
</head>
<body>
<c:out value="${ConvertedValue}"/>
<c:out value="${convertedValue}"></c:out>
<c:out value = "${test}"/>

	<form action="" method="post">

		<h3>Please select your currency values:</h3>
		<label>From: <select id="selectedTopCurrency"
			name="selectedTopCurrency">

				<c:forEach var="currencyBoxOne" items="${currencies}">

					<option value="${currencyBoxOne.currency}"
						${currencyBoxTwo.id == 1 ? 'selected="selected"' : ''}>${currencyBoxOne.currency}</option>

				</c:forEach>

		</select> <input type="text" id="topBoxAmount" name="topBoxAmount"
			class="target" />
		</label>
		<p />
		<label>&#160&#160&#160&#160To: <select
			name="selectedBottomCurrency" name="selectedBottomCurrency">
				<c:forEach var="currencyBoxTwo" items="${currencies}">
					<option value="${currencyBoxTwo.currency}"
						${currencyBoxTwo.id == 2 ? 'selected="selected"' : ''}>${currencyBoxTwo.currency}</option>
				</c:forEach>
		</select> <input type="text" id="bottomBoxAmount" name="bottomBoxAmount"
			value="<c:out value="${ConvertedValue}"/>" />
		</label> <br />
		<p />
		Rate used: 1 $USD to:  <input type="text" id="rate" name="rate" value=""
			 /><div id="currencyTo"></div>
	</form>
	<p>
		<button
			onclick="location.href='${pageContext.request.contextPath}/currencylist'">Currency
			Management</button>
	</p>

<div id = "dvComment"></div>

<script type="text/javascript">

   $(document).ready(function() {
	$('#topBoxAmount').on('input',function(e) {
		var fromOption = $('select[name=selectedTopCurrency]').val();
		var fromTextBox = $('#topBoxAmount').val();
		var toTextBox = $('select[name=selectedBottomCurrency]').val();
		var submitDetails = fromOption.concat("_").concat(fromTextBox).concat("_").concat(toTextBox);
												//alert(submitDetails);
				 											
		  $.ajax({
			type : 'POST',
			url : "<c:url value = '/convertcurrency'/>",
			data : JSON.stringify(submitDetails),
			success : success,
			error : error,
			contentType : "application/json",
			dataType : 'json'
		});
	});
		
	function success(data) {
				
				$('#bottomBoxAmount').val(data.postconvertedValue);
				$("#rate").val(data.valueToUSD);
				$('#currencyTo').val(data.currencyTo);
			}
			function error(data) {
				
			   alert("error");
			}
		});   
	</script> 
</body>



</html>