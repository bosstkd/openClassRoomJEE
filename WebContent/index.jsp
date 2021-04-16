<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"https://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>jQuery, Ajax and Servlet/JSP integration example</title>

<script src="https://code.jquery.com/jquery-1.10.2.js"
	type="text/javascript"></script>
<script src="js/app-ajax.js" type="text/javascript"></script>

<script type="text/javascript">
function myJsFunction(value){
	            var name = value;
	            console.log(name);
	            $.get('GetUserServlet', {
	                    userName : name
	            }, function(responseText) {
	                    $('#ajaxGetUserServletResponse1').text(responseText);
	            });
}
</script>

</head>
<body>

	<form>
		Enter Your Name: <input type="text" id="userName" />
	</form>
	<br>
	<button onclick="myJsFunction('AMINE')"> test </button>
	<br>

	<strong>Ajax Response</strong>:
	<div id="ajaxGetUserServletResponse"></div>
	<br>
	<div id="ajaxGetUserServletResponse1"></div>
</body>
</html>