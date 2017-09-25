var sqlite3 = require("sqlite3");
class SQLConnection extends sqlite3.Database {
    constructor(file) {
        super(file);
    }
}