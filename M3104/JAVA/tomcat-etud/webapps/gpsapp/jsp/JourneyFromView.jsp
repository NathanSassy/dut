<%@page import="db.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//FR" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
 <head>
  <title>Application de depot de TP</title>
  <link rel="stylesheet" type="text/css" media="all" href="css/style.css" />
 </head>
 <body>

    <jsp:include page="../html/header.html"/>

    <FORM method="POST" action=http://localhost:8080/gpsapp/?p=journey_add>
         Description : <INPUT type=text size=50 name="journey_description">
         <BR>
		 <INPUT type=submit value=Envoyer>
    </FORM>

 </body>
</html>
