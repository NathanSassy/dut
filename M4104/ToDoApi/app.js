var express = require('express'),
  app = express(),
  port = process.env.PORT || 3000,
  mongoose = require('mongoose'),
  Task = require('./src/models/TaskModel'), //created model loading here
  bodyParser = require('body-parser');

// mongoose instance connection url connection
mongoose.Promise = global.Promise;
mongoose.connect('mongodb://groot_api:gr004pif0rT3st@mongodb-groot.alwaysdata.net:27017/groot_todoapi');

app.use(bodyParser.urlencoded({ extended: true }));
app.use(bodyParser.json());

var routes = require('./src/routes/TaskRoutes'); //importing route
routes(app); //register the route

app.listen(port);
console.log('TodoApi server running on port: ' + port);
