<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>

<title>Upload file</title>

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
 
<c:if test="${ !empty erreur }"><p style="color:red;"><c:out value="${ erreur }" /></p></c:if>

<form id="form1" method="post" action="" enctype="multipart/form-data">
<p>
		<label for="description">Nom de la vidéo * : </label>
		<input type="text" id="description" name="description" required /> 
</p>

<p>		
		<label for="fichier">fichier: </label>
		<input type="file" name="fichier" id="fichier" style="font-size: 15px; font-style: bold; width:300px; height:30px;" />
		<input type="submit" value="Charger" style="font-size: 15px; font-style: bold; width: 120px; height: 30px;" /> 
</p>		
		
</form>

<c:if test="${! empty listToTraduce}">
<form id="form2" method="post" action="traduced">

 <h1><c:out value="${description}" /></h1>
 <input type="hidden" name="description" value="${description}"/>
 <input type="hidden" name="videoID" value="${videoID}"/>

	<table style="width:100%">
	 <tr>
	    <th>N° Ligne</th>
	    <th>Début</th>
	    <th>fin</th>
	    <th>Texte</th>
	    <th>Traduction</th>
	 </tr>
	 
	 
	 <c:forEach var="varList" items="${listToTraduce}">
	   <tr>
		    <td>
		     <c:out value="${varList.ligne}" />
		     <input type="hidden" name="nl_${varList.ligne}" value="${varList.ligne}"/>
		    </td>
		    <td>
		     <c:out value="${varList.du}" />
		     <input type="hidden" name="du_${varList.ligne}" value="${varList.du}"/>
		    </td>
		    <td>
		     <c:out value="${varList.au}" />
		     <input type="hidden" name="au_${varList.ligne}" value="${varList.au}"/>
		    </td>
		    <td>
		     <c:out value="${varList.texteOrg}" />
		     <input type="hidden" name="texteOrg_${varList.ligne}" value="${varList.texteOrg}"/>
		    </td>
		    <td>
		    <input type="text" value="${varList.texteTraduit}" id="trad_${varList.ligne}" name="trad_${varList.ligne}">
		    </td>
	   </tr>  
	</c:forEach>
	</table>
	<c:set var="listToSend" value="listToTraduce" scope="page" />
<input type="submit" value="Valider" style="font-size: 18px; font-style: bold; width: 120px; height: 35px; position: fixed; top: 20px; right: 20px; " /> 
</form>
</c:if>


 
</body>
</html>