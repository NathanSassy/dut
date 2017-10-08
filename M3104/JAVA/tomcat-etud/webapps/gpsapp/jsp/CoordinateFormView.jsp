<%@page import="java.util.ArrayList" %>
<%@page import="db.*" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<% ArrayList<Coordinates> coordinates = (ArrayList<Coordinates>) request.getAttribute("coordinates");
   session.setAttribute("new_coordinates", coordinates);
   session.setAttribute("journey_id", (Integer) request.getAttribute("journey_id"));
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//FR" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
 <head>
  <title>Application de depot de TP</title>
  <link rel="stylesheet" type="text/css" media="all" href="css/style.css" />
 </head>
 <body>

    <jsp:include page="../html/header.html"/>

    Ajouter des coordonnes :



    Coordonnees :

     <table border="1">
        <tr>
            <th>journey_id</th>
            <th>latitude</th>
            <th>longitude</th>
        </tr>

         <c:forEach items="${coordinates}" var="coordinate">
             <tr>
                 <td>
                    <c:out value="${coordinate.journey_id}"/>
                 </td>

                 <td>
                    <c:out value="${coordinate.latitude}"/>
                 </td>

                 <td>
                    <c:out value="${coordinate.longitude}"/>
                 </td>
            </tr>
         </c:forEach>
     </table>

    <FORM method="POST" action=http://localhost:8080/gpsapp/?p=coordinate_form>
         latitude : <INPUT type=number step=0.000001 name="latitude">
         <BR>
         longitude : <INPUT type=number step=0.000001 step=any name="longitude">
         <BR>
         <input type="hidden" name="journey_id" value="${journey_id}" />
         <INPUT type=submit value=Ajouter>
    </FORM>

    <FORM method="POST" action=http://localhost:8080/gpsapp/?p=coordinate_add>
         <INPUT type=submit value=Appliquer>
    </FORM>


 </body>
</html>
