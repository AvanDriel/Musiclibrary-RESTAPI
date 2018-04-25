# Musiclibrary RESTAPI

### Assignment(s)
The origal assignment was to build a RESTful API and a Vuejs frontend page to manage the API. For this project I descided to make a music library where you can add albums you want to listen to, and later mark them as 'listened'. Later I also made an android app to interact with the API on your smartphone.

### Installation
The Vue project can be downloaded and then launched on a node server using npm start. It connects with the api I built that is running on my server. The api code is also in this repository, so can be downloaded if you wish.
The App is not yet built, but can be run on an emulator using Androidstudio. (The App is using android api version 23)

### Vue
![Vue Overview](https://i.imgur.com/T8HFYQJ.png)

The frontend is quite simple, and shows all the albums currently in the api. By clicking on a album, you can see a detail view of the album and two buttons to either delete or edit the album. the edit uses a form which sends the form input to the api.

![Detail View](https://i.imgur.com/hdU1OHP.png)

By clicking on the 'new album' button on the top bar you can input data that gets sent to the api to create a new album in the system.

![New Album](https://i.imgur.com/GwygF4u.png)

### App
![App Mainscreen](https://i.imgur.com/ibNLaca.png)

The app is made using Android Studio and java, and using volley for some requests.
On the main screen are two buttons, one to see a list of all albums, and one to add a new album.

![Album List](https://i.imgur.com/1ECbRls.png)

The 'see your albums'button executes an AsyncTask to send a get request to the api and store all the data. The data gets passed to a custom model which creates a custom list to show all the albums in on the next screen.

![New Album](https://i.imgur.com/pNZmbr1.png)

The 'add album' button sends you to a new screen where you can input an artist, album title and genre of the album you want to add. Upon clicking the add button a request (using volley) is sent to the api to add your new album.

![Settings](https://i.imgur.com/5O7WYpn.png)

I also wanted to see how settings work in Android, so on the main screen there is a settings button that sends you to the settings screen. The only option is to change your name, that gets displayed on the main screen when you enter the app.
