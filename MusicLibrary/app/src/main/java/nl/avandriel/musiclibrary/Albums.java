package nl.avandriel.musiclibrary;

//class with all getters and setters for the listview
public class Albums {

    private String title, artist, genre;

    public Albums(String title, String artist, String genre){
        this.setTitle(title);
        this.setArtist(artist);
        this.setGenre(genre);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
