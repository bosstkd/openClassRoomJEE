
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<title>Etat de traduction</title>
</head>
<body>

   <%@ include file="menu.jsp" %>
   
   <form method="post" action="dbgetsend">
 
	   <h1>Etat traduction <c:out value="${description}"  /> video ID: (<c:out value="${videoID}"  />)</h1>
	   <input type="hidden" name="description" value="${description}"/>
	   <input type="hidden" name="videoID" value="${videoID}"/>
	   <input type="submit" value="Enregitrer dans la base de données" style="font-size: 24px; font-style: bold; width: 50%; height: 45px;" /> 
  
   <table style="width:100%">
	 <tr>
	    <th>N° Ligne</th>
	    <th>Début</th>
	    <th>fin</th>
	    <th>Texte</th>
	    <th>Traduction</th>
	 </tr>
	 
	 <c:forEach var="varList" items="${lfb}">
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
		   
		    <c:set var = "TR1" value="texte a traduire"/>
		   
		    <c:choose>
		     <c:when test='${varList.texteTraduit eq TR1 }'>
		       <label style="color:red; font-size: 12px">${varList.texteTraduit}</label>
		      </c:when>
		     <c:otherwise>
		      <label style="color:green; font-size: 16px">${varList.texteTraduit}</label>
		     </c:otherwise>
		    </c:choose>
		    
		     <input type="hidden" name="trad_${varList.ligne}" value="${varList.texteTraduit}"/>
		    </td>
	   </tr>  
	</c:forEach>
	</table>
   </form>
   
   
</body>
</html>