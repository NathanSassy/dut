var express = require('express');
var router = express.Router();
var journey = require('gpsapp-db').journey_dao;
router.post("/", function(req, res, next) {
    journey.insert(req.body.description, function(lastID) {
        res.render("journey_add", {id:lastID});
    });
});
module.exports = router;