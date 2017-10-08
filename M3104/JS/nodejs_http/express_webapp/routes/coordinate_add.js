var express = require('express');
var router = express.Router();
var coordinates = require('gpsapp-db').coordinates_dao;
router.post("/", function(req, res, next) {
    coordinates.insert(req.body.journey_id, req.body.journey_pos, req.body.latitude, req.body.longitude, function(r) {
        res.render("coordinate_add", {ok:r});
    });
});
module.exports = router;