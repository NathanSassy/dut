var express = require('express');
var router = express.Router();
var coordinates = require('gpsapp-db').coordinates_dao;
router.post("/", function(req, res, next) {
    res.render("coordinate_form", {id:req.body.id});
});
module.exports = router;