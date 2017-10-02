var sqlite3 = require("sqlite3");
class SQLConnection extends sqlite3.Database {
    constructor() {
        super("gps_web_app.db", sqlite3.OPEN_READWRITE);
    }
}

var connection = new SQLConnection();
module.exports = connection;