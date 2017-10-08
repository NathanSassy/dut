var express = require('express');
var router = express.Router();
var coordinates = require('gpsapp-db').coordinates_dao;
router.get("/", function(req, res, next) {
    coordinates.findFromJourneyId(req.query.id, function(rows) {    
        res.render("journey", {data:rows, id:req.query.id});
    });
});
module.exports = router;