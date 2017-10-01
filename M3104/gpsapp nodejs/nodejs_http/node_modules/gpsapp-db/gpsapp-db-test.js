var journey = require("./gpsapp-db").journey_dao;
var coordinates = require("./gpsapp-db").coordinates_dao;
var db = require("./gpsapp-db").db_connection;

journey.insert("test", function(lastId) {
    console.log("newid = " + lastId);
});

journey.update(67, "test update", 3);
journey.delete(67);
journey.findAll();

journey.findByKey(83, function(row) { console.log("row.description = " + row.description)})