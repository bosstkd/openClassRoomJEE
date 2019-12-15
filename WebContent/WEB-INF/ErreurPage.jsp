
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Page d'erreur</title>
</head>
<body>
<h1>Page d'erreur</h1>
<%@ include file="menu.jsp" %>
<c:if test="${ !empty erreur }"><p style="color:red;"><c:out value="${ erreur }" /></p></c:if>
</body>
</html>