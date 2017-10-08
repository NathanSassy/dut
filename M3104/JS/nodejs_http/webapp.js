var http = require('http');
var gpsdb = require('gpsapp-db');

var server = http.createServer(function(req, res) {
  res.writeHead(200, {"Content-Type": "text/html"});
  res.write('<!DOCTYPE html>'+
            '<html>'+
            '    <head>'+
            '        <meta charset="utf-8" />'+
            '        <title>Mes trajets : </title>'+
            '    </head>'+ 
            '    <body>'+
            '       <table border="1">'+
            '           <tr>'+
            '               <th>id</th>'+
            '               <th>description</th>'+
            '               <th>date_creation</th>'+
            '               <th>distance</th>'+
            '           </tr>');
    gpsdb.journey_dao.findAll(function(rows) {
        rows.forEach(function (row) {
            res.write("<tr>");
            
            res.write("<td>");
            res.write("" + row.id);
            res.write("</td>");

            res.write("<td>");
            res.write("" + row.description);
            res.write("</td>");

            res.write("<td>");
            res.write("" + row.date_creation);
            res.write("</td>");

            res.write("<td>");
            res.write("" + row.distance);
            res.write("</td>");
            
            res.write("</tr>");
        });

        res.write(
            '    </body>'+
            '</html>');
        res.end();
    });
});

server.listen(8080);