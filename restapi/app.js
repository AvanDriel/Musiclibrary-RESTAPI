//use express, mongoose
var express = require('express');
var mongoose = require('mongoose');
var bodyParser = require('body-parser');
var app = express();    

// Connect to MongoDB with mongoose
var connectOptions = { useMongoClient: true };
var db = mongoose.connect('mongodb://avandriel:a12348765A@musicapi-shard-00-00-craev.mongodb.net:27017,musicapi-shard-00-01-craev.mongodb.net:27017,musicapi-shard-00-02-craev.mongodb.net:27017/test?ssl=true&replicaSet=MusicAPI-shard-0&authSource=admin', connectOptions);  

//use model
var Album = require('./models/albumModel');

//declare port
var port = process.env.PORT || 8000;

//use bodyparser
app.use(bodyParser.urlencoded({extended:true}));
app.use(bodyParser.json());

//req router
albumRouter = require('./routes/albumRoutes')(Album);


//cors middleware
app.use(function(req, res, next){
    res.header("Access-Control-Allow-Origin", "*");
    res.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
    next();
})

// middleware supported format
app.use('*', function(req,res,next){
    if(!req.accepts('application/json')){
        res.status(400).send('Unsupported format');
    } 
    else {
        next();
    }
})

app.use('/api/albums', albumRouter);
// app.use('/api/artists', artistRouter);


//functions
app.get('/', function(req, res){
    res.send('welcome to my API');
});

app.listen(port, function(){
    console.log( 'Gulp is running on PORT: ' + port);
})

