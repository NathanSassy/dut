<%@page import="java.util.ArrayList" %>
<%@page import="db.*" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<% ArrayList<Journey> data = (ArrayList<Journey>) request.getAttribute("data"); %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//FR" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
 <head>
  <title>Application de depot de TP</title>
  <link rel="stylesheet" type="text/css" media="all" href="css/style.css" />
 </head>
 <body>

    <FORM method="POST" action="http://localhost:8080/gpsapp/?p=journey_form">
    		 <INPUT type=submit value="Ajouter un trajet">
    </FORM>

     <table border="1">
        <tr>
            <th>id</th>
            <th>description</th>
            <th>date_creation</th>
            <th>distance</th>
        </tr>

     <c:forEach items="${data}" var="journey">
         <tr>
             <td>
                <a href="http://localhost:8080/gpsapp/?p=journey&journey_id=${journey.id}">
                    <c:out value="${journey.id}"/>
                </a>
            </td>

            <td>
                <c:out value="${journey.description}"/>
           </td>

           <td>
                <c:out value="${journey.date_creation}"/>
          </td>

          <td>
                <c:out value="${journey.distance}"/>
         </td>

        </tr>
     </c:forEach>

     </table>

 </body>
</html>
