var db = require("./sqlite_connection");
var journey = require("./journey");

var CoordinatesDAO = function(){

    this.findFromJourneyId = function(key, callback) {
        db.all("SELECT * FROM Coordinates WHERE journey_id = " + key, function(err, rows) {
            if(callback)
                callback(rows);
        });
    };

    const updateDistance = (coordinateID) => {
        let convertToRad = function (degrees) {
            return degrees * (Math.PI / 180);
        };        

        let distance = function (lat1, long1, lat2, long2) {
            let REarth =  6378.137;
            return parseFloat(REarth * Math.acos(Math.sin(convertToRad(lat2))
                * Math.sin(convertToRad(lat1))
                + Math.cos(convertToRad(lat2))
                * Math.cos(convertToRad(lat1))
                * Math.cos(convertToRad(long2)
                - convertToRad(long1))));
        };

        this.findByKey(coordinateID, (row) => {
            let journeyId = row.journey_id;
            this.findFromJourneyId(journeyId, (rows) => {
                let distanceTotal = 0.0;
                let coord = [];
                rows.forEach(function(row) {
                    coord.push([row.latitude, row.longitude]);
                });

                for (let i = 0; i < coord.length - 1; i++) {
                    distanceTotal += distance(coord[i][0], coord[i][1], coord[i+1][0], coord[i+1][1]);
                }
    
                journey.findByKey(journeyId, function(row) {
                    journey.update(journeyId, row.description, distanceTotal);
                });
            });
        });
    };

    this.getLastCoordinatesID = function(callback) {
        let stmt = db.prepare("SELECT COUNT(*) AS id FROM Coordinates");
        stmt.get(function(err, row) {
            if(callback)
                callback(row.id);
        });
    };
    
    this.insert = (journey_id, journey_pos, latitude, longitude, callback) => {
        this.getLastCoordinatesID((id) => {
            var newID = id + 1;
            let stmt = db.prepare("INSERT INTO Coordinates VALUES (?,?,?,?)");
            stmt.run(journey_id, newID, latitude, longitude, function() {
                updateDistance(newID);
                if(callback)
                    callback(this.lastID);
            });
        });
    };

    this.update = function(key, journey_pos, latitude, longitude, callback){
        let stmt = db.prepare("UPDATE Coordinates SET journey_pos = ?, latitude = ?, longitude = ? WHERE id = ?");
        stmt.run(journey_pos, latitude, longitude, key, function(key) {
            updateDistance(key);
            if(callback)
                callback(true);
        });
    };

    this.delete = function(key, callback){
        let stmt = db.prepare("DELETE FROM Coordinates WHERE journey_pos = ?");
        stmt.run(key, function(key) {
            updateDistance(key);
            if(callback)
                callback(true);
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
};

var coordinates_dao = new CoordinatesDAO();
module.exports = coordinates_dao;