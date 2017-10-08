var express = require('express');
var router = express.Router();
var coordinates = require('gpsapp-db').coordinates_dao;
router.get("/", function(req, res, next) {
    res.render("journey_form", {});
});
module.exports = router;