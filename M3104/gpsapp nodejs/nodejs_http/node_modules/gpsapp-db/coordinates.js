var db = require("./sqlite_connection");
var journey = require("./journey");

var CoordinatesDAO = function(){
    this.insert = function(journey_id, journey_pos, latitude, longitude, callback){
        let stmt = db.prepare("INSERT INTO Journey (description) VALUES (?)");
        stmt.run(journey_id, journey_pos, latitude, longitude, function() {
            if(callback)
                callback(this.lastID);
            updateDistance(key);
        });
    };

    this.update = function(key,journey_pos, latitude, longitude, callback){
        let stmt = db.prepare("UPDATE Coordinates SET journey_pos = ?, latitude = ?, longitude = ? WHERE id = ?");
        stmt.run(journey_pos, latitude, longitude, key, function() {
            if(callback)
                callback(true);
            updateDistance(key);
        });
    };

    this.delete = function(key, callback){
        let stmt = db.prepare("DELETE FROM Coordinates WHERE journey_pos = ?");
        stmt.run(key, function() {
            if(callback)
                callback(true);
            updateDistance(key);
        });
    };

    this.findAll = function(callback){
        db.all("SELECT * FROM Coordinates", function(err, rows) {
            if(callback)
                callback(rows);
        });
    };

    this.findByKey = function(key, callback){
        let stmt = db.prepare("SELECT * FROM Coordinates WHERE journey_pos = ?");
        stmt.get(key, function(err, row) {
            if(callback)
                callback(row);
        });
    };

    this.updateDistance = function(key) {
        journey.findByKey(key, function(rows) {
            var distance = 0.0;
            rows.forEach(function (row) {  
                distance += rows.distance; 
            });
            journey.findByKey(key, function(row) {
                journey.update(key, row.description, distance);
            });
        });
    };

};
var coordinates_dao = new CoordinatesDAO();
  module.exports = coordinates_dao;