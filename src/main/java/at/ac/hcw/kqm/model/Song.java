package at.ac.hcw.kqm.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Repräsentiert einen Song im Karaoke Quiz Master Spiel.
 * Jeder Song hat eine ID, einen Titel,
 * einen Künstler und einen Verfügbarkeitsstatus.
 */

public class Song {
    private int id;
    private String title;
    private String artist;
    private boolean isAvailable;  // Whether the song is unlocked/available
    private List<LyricLine> lyrics;  // Song lyrics with timing for karaoke mode (optional)
    private String audioFilePath;  // Path to audio file
    private String lyricsFilePath;  // Path to LRC lyrics file

    /**
     * Constructor to create a new song.
     */
    public Song(int id, String title, String artist) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.isAvailable = true;
        this.lyrics = new ArrayList<>();
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
        this.lyrics = new ArrayList<>();
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

    public List<LyricLine> getLyrics() {
        return lyrics;
    }

    public void setLyrics(List<LyricLine> lyrics) {
        this.lyrics = lyrics;
    }

    public void addLyricLine(long startTime, String text) {
        this.lyrics.add(new LyricLine(startTime, text));
    }

    public String getAudioFilePath() {
        return audioFilePath;
    }

    public void setAudioFilePath(String audioFilePath) {
        this.audioFilePath = audioFilePath;
    }

    public String getLyricsFilePath() {
        return lyricsFilePath;
    }

    public void setLyricsFilePath(String lyricsFilePath) {
        this.lyricsFilePath = lyricsFilePath;
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
