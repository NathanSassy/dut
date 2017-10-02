var express = require('express');
var router = express.Router();
var journey = require('gpsapp-db').journey_dao;
const ret = function(req, res, next) {
  journey.findAll(function(rows) {
      res.render("journeys", {data:rows});
    });
  };
router.get("/", ret);
router.post("/", ret);
module.exports = router;