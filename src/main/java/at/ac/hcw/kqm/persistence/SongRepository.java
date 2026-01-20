package at.ac.hcw.kqm.persistence;

import at.ac.hcw.kqm.model.Song;

import java.util.List;

/**
 * Interface für den Zugriff auf Songs.
 * Definiert Methoden zum Laden von Songs
 * aus einer Datenquelle.
 */

public interface SongRepository {

    /**
     * Retrieves all available songs.
     * @return A list of all songs
     */
    List<Song> getAllSongs();

    /**
     * Retrieves a specific song by its ID.
     * @param id The unique identifier of the song
     * @return The song with the given ID, or null if not found
     */
    Song getSongById(int id);

    /**
     * Searches for songs by artist name.
     * @param artist The artist name to search for
     * @return A list of songs by that artist
     */
    List<Song> getSongsByArtist(String artist);

    /**
     * Gets the total number of songs available.
     * @return The count of all songs
     */
    int getSongCount();
}
