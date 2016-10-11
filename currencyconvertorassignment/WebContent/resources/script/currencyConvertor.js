/**
 * 
 */
$(document).ready (function(){
	$('#topBoxAmount').on('input',function(e) {
		var fromOption = $('select[name=selectedTopCurrency]').val();
		var fromTextBox = $('#topBoxAmount').val();
		var toTextBox = $('select[name=selectedBottomCurrency]').val();
		var submitDetails = fromOption.concat("_").concat(fromTextBox).concat("_").concat(toTextBox);
												// alert(submitDetails);

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
			  alert("successfull post");
				
			}
			function error(data) {
				alert("error post");
				
			}
	   });