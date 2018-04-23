var albumController = function(Album){

    var post = function(req, res){
        if(req.body.title && req.body.artist && req.body.genre && req.body.listened){
            var album = new Album(req.body);

            album.save();
            res.status(201).send(album);
        } else {
            res.status(400).send('Not all fields are Filled in!');
        }    
    }

    var getall = function(req, res){

        var query = {};

        if(req.query.genre){
            query.genre = req.query.genre;
        }
        Album.find(query, function(err,albums){
            if(err){
                res.status(500).send(err);
            } else {
                var returnAlbums = [];
                albums.forEach(function(element, index, array){
                    var newAlbum = element.toJSON();
                    newAlbum._links = {};
                    newAlbum._links.self = {};
                    newAlbum._links.self.href = 'http://' + req.headers.host + '/api/albums/' + newAlbum._id;
                    returnAlbums.push(newAlbum);
                })
                res.json({
                    items : returnAlbums,
                    _links : {
                        self : {
                            href: 'http://'+req.headers.host+'/api/albums'
                        }
                    },
                    pagination: {
                        "error": "Unsupported"
                    }
                });
            }
        });
    }

    var get = function(req, res){

        var returnAlbum = req.album.toJSON();
            returnAlbum._links = {};

            var selfLink = 'http://' + req.headers.host + '/api/albums/' + returnAlbum._id;
            returnAlbum._links.self = {};
            returnAlbum._links.self.href = selfLink;

            var collectionLink = 'http://' + req.headers.host + '/api/albums/';
            returnAlbum._links.collection = {};
            returnAlbum._links.collection.href = collectionLink;

            res.json(returnAlbum);
    }

    var put = function(req, res){
        if(req.body.title && req.body.artist && req.body.genre && req.body.listened){
            req.album.title = req.body.title;
            req.album.artist = req.body.artist;
            req.album.genre = req.body.genre;
            req.album.listened = req.body.listened;
            req.album.save(function(err){
                if(err){
                    res.status(500).send(err);
                } else {
                    res.json(req.album);
                }
            });
        } else {
            res.status(400).send('Not all fields are Filled in!');
        }
    }

    var patch = function(req, res){
        if(req.body._id){
            delete req.body._id;
        }

        for(var p in req.body){
            req.album[p] = req.body[p];
        }

        req.album.save(function(err){
            if(err){
                res.status(500).send(err);
            } else {
                res.json(req.album);
            }
        });
    }

    var remove = function(req, res){
        req.album.remove(function(err){
            if(err){
                res.status(500).send(err);
            } else {
                res.status(204).send('Removed');
            }
        });
    }

    //get options
    var collectionOptions = function(req,res){
        res.header('Allow', 'GET,POST,OPTIONS');
        res.header('Access-Control-Allow-Methods', 'GET,POST,OPTIONS');
        res.send(200);
    }
    var detailOptions = function(req,res){
        res.header('Allow', 'GET,PUT,PATCH,DELETE,OPTIONS');
        res.header('Access-Control-Allow-Methods', 'GET,PUT,PATCH,DELETE,OPTIONS');
        res.send(200);
    }

    return {
        post: post,
        getall: getall,
        get: get,
        put: put,
        patch: patch,
        delete: remove,
        collectionOptions: collectionOptions,
        detailOptions: detailOptions
    }
}


module.exports = albumController;