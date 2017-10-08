<%@page import="java.util.ArrayList" %>
<%@page import="db.*" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<% ArrayList<Coordinates> coordinates = (ArrayList<Coordinates>) request.getAttribute("coordinates");
   int id = (int) request.getAttribute("journey_id");
   session.setAttribute("journey_id", id);
%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//FR" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
 <head>
  <title>Application de depot de TP</title>
  <link rel="stylesheet" type="text/css" media="all" href="css/style.css" />
 </head>
 <body>

    <jsp:include page="../html/header.html"/>

    Coordonnees du trajet <% out.print(id); %> :

    <table>
            <tr>
                <th>
                    <FORM method="POST" action="http://localhost:8080/gpsapp/?p=coordinate_form">
                        <INPUT type=submit value="Ajouter une coordonne">
                        <INPUT type="hidden" name="journey_id" value="${id}" />
                    </FORM>
                </th>
            </tr>

            <tr>
                <th>
                    <FORM method="POST" action="http://localhost:8080/gpsapp/?p=journey_list">
                        <INPUT type=submit value="Retour a la liste des trajets">
                    </FORM>
                </th>
            </tr>
    </table>

     <table border="1">
        <tr>
            <th>journey_id</th>
            <th>journey_pos</th>
            <th>latitude</th>
            <th>longitude</th>
        </tr>

     <c:forEach items="${coordinates}" var="coordinate">
         <tr>
             <td>
         <c:out value="${coordinate.journey_id}"/>
            </td>

            <td>
        <c:out value="${coordinate.journey_pos}"/>
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

 </body>
</html>
