var express = require('express');
var router = express.Router();
var journey = require('gpsapp-db').journey_dao;
router.get("/", function(req, res, next) {
journey.findAll(function(rows) {
    res.render("journeys", {data:rows});
  });
});
module.exports = router;