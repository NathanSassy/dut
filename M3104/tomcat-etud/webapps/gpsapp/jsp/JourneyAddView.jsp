<% int id = request.getAttribute("journey_id") != null ? (int) request.getAttribute("journey_id") : -1; %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//FR" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
 <head>
  <title>Application de depot de TP</title>
  <link rel="stylesheet" type="text/css" media="all" href="css/style.css" />
 </head>
 <body>
    Nouveau trajet : id 
    <% out.print(id); %>

 </body>
</html>
