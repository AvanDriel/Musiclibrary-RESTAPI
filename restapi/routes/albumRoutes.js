var express = require('express');

var routes = function(Album){
var albumRouter = express.Router();
var albumController = require('../controllers/albumController')(Album);
albumRouter.route('/')
    .post(albumController.post)
    .get(albumController.getall)
    .options(albumController.collectionOptions);


albumRouter.use('/:albumId', function(req, res, next){
     Album.findById(req.params.albumId, function(err,album){
            if(err){
                res.status(500).send(err);
            } else if(album){
                req.album = album;
                next();
            } else {
                res.status(404).send('no album found');
            }
    });
})
albumRouter.route('/:albumId')    
    .get(albumController.get)

    .put(albumController.put)
    
    .patch(albumController.patch)

    .delete(albumController.delete)

    .options(albumController.detailOptions);

    return albumRouter;
};

module.exports = routes;