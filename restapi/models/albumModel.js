var mongoose = require('mongoose');
var Schema = mongoose.Schema;

//Define what an album is supposed to look like
var albumModel = new Schema({
    title: {type: String},
    artist: {type: String},
    genre: {type: String},
    listened: {type: String, default:false}
});

module.exports = mongoose.model('Album', albumModel);