'use strict';
var mongoose = require('mongoose');
var Schema = mongoose.Schema;

var TaskSchema = new Schema(
{
    "nom": String,
    "description": String,
    "contexte": String,
    "duree": Number
},
{
    collection: 'tododbs'
});

module.exports = mongoose.model('Tasks', TaskSchema);
