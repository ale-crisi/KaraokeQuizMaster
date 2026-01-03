package at.ac.hcw.kqm.model;

/**
 * Represents a song in the Karaoke Quiz Master game.
 * Each song has an ID, title, artist, and availability status.
 */
public class Song {
    private int id;
    private String title;
    private String artist;
    private boolean isAvailable;  // Whether the song is unlocked/available
    private String lyrics;  // Song lyrics for karaoke mode (optional)
    private String audioFilePath;  // Path to audio file (optional, for preview/karaoke)

    /**
     * Constructor to create a new song.
     */
    public Song(int id, String title, String artist) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.isAvailable = true;  // Default: all songs are available
        this.lyrics = "";
        this.audioFilePath = "";
    }

    /**
     * Constructor with availability status.
     *
     * @param id          Unique identifier for the song
     * @param title       The song's title
     * @param artist      The artist who performs the song
     * @param isAvailable Whether the song is unlocked
     */
    public Song(int id, String title, String artist, boolean isAvailable) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.isAvailable = isAvailable;
        this.lyrics = "";
        this.audioFilePath = "";
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public String getLyrics() {
        return lyrics;
    }

    public void setLyrics(String lyrics) {
        this.lyrics = lyrics;
    }

    public String getAudioFilePath() {
        return audioFilePath;
    }

    public void setAudioFilePath(String audioFilePath) {
        this.audioFilePath = audioFilePath;
    }

    /**
     * Gets a display string for the song list.
     *
     * @return Formatted string: "Title - Artist"
     */
    public String getDisplayName() {
        return title + " - " + artist;
    }

    @Override
    public String toString() {
        return "Song{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", artist='" + artist + '\'' +
                ", isAvailable=" + isAvailable +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Song song = (Song) o;
        return id == song.id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }
}
