
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<title>files to download</title>

<style>
table {
  font-family: arial, sans-serif;
  border-collapse: collapse;
  width: 100%;
}

td, th {
  border: 1px solid #dddddd;
  text-align: left;
  padding: 8px;
  font-size: 14px;
}

tr:nth-child(even) {
  background-color: #dddddd;
}
</style>
</head>
<body>
<%@ include file="menu.jsp" %>


<c:if test="${ empty lvb}"	>
  <h1>Aucune traduction pour l'instant</h1>
</c:if>

<c:if test="${! empty lvb}"	>

<c:if test="${ !empty erreur }"><p style="color:red;"><c:out value="${ erreur }" /></p></c:if>
<form method="post" action="fileDown">
  <input type="checkbox" name="org" id = "org" >
  <label for="org">Texte original</label>
 <table style="width:50%">
 <tr>
  <td><input type="submit" name="download" value="télécharger" style="width: 100%; height: 100%; font-size: 20px" /></td>
  <td><input type="submit" name="delete" value="supprimer" style="width: 100%; height: 100%; font-size: 20px" /></td>
 </tr>
 </table>

 <table style="width:50%">
	 <tr>
	    <th>ID Video</th>
	    <th>Nom de video</th>
	    <th>Select</th>
	 </tr>
	 
	 <c:forEach var="listVideo" items="${lvb}">
	   <tr>
		    <td>
		     <c:out value="${listVideo.id}" />
		     <input type="hidden" name="nl_${listVideo.id}" value="${listVideo.id}"/>
		    </td>
	
	        <td>
		     <c:out value="${listVideo.videoName}" />
		     <input type="hidden" name="nl_${listVideo.videoName}" value="${listVideo.videoName}"/>
		    </td>
		    
		    <td>
		     <input type="radio"  name="selection" value="${listVideo.id}">
		    </td>
	
	   </tr>  
	</c:forEach>
	</table>
</form>
</c:if>
	
</body>
</html>