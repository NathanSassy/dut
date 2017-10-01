var db = require("./sqlite_connection");
var JourneyDAO = function(){
    this.insert = function(description, callback){
        let stmt = db.prepare("INSERT INTO Journey (description) VALUES (?)");
        stmt.run(description, function() {
            if(callback)
                callback(this.lastID);
        });
    };

    this.update = function(key, description, distance, callback){
        let stmt = db.prepare("UPDATE Journey SET description = ?, distance = ? WHERE id = ?");
        stmt.run(description, distance, key, function() {
            if(callback)
                callback(true);
        });
    };

    this.delete = function(key, callback){
        let stmt = db.prepare("DELETE FROM Journey WHERE id = ?");
        stmt.run(key, function() {
            if(callback)
                callback(true);
        });
    };

    this.findAll = function(callback){
        db.all("SELECT * FROM Journey", function(err, rows) {
            if(callback)
                callback(rows);
        });
    };

    this.findByKey = function(key, callback){
        let stmt = db.prepare("SELECT * FROM Journey WHERE id = ?");
        stmt.get(key, function(err, row) {
            if(callback)
                callback(row);
        });
    };

};

var journey_dao = new JourneyDAO();
module.exports = journey_dao;