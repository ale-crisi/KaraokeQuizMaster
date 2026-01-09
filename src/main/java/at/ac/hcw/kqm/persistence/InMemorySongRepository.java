package at.ac.hcw.kqm.persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import at.ac.hcw.kqm.model.Song;

/**
 * In-memory implementation of SongRepository.
 * Stores karaoke songs that players can choose from.
 * Each song will have multiple quiz questions associated with it.
 *
 * DATA: Contains 15 karaoke songs (10 English, 5 German)
 */
public class InMemorySongRepository implements SongRepository {

    private List<Song> songs;

    /**
     * Constructor that initializes the repository with karaoke songs.
     */
    public InMemorySongRepository() {
        this.songs = new ArrayList<>();
        initializeSongs();
    }

    /**
     * Initializes the repository with popular karaoke songs.
     * Some songs are marked as locked/unavailable for game progression.
     */
    private void initializeSongs() {
        // Popular English karaoke songs (all available)
        songs.add(new Song(1, "Simarik", "Tarkan", true));
        songs.add(new Song(2, "It's My Life", "Bon Jovi", true));
        songs.add(new Song(3, "Hit Me Baby One More Time", "Britney Spears", true));
        songs.add(new Song(4, "Rolling in the Deep", "Adele", true));
        songs.add(new Song(5, "Blinding Lights", "The Weeknd", true));
        songs.add(new Song(6, "Imagine", "John Lennon", true));
        songs.add(new Song(7, "Someone Like You", "Adele", true));
        songs.add(new Song(8, "Hotel California", "Eagles", true));
        songs.add(new Song(9, "Don't Stop Believin'", "Journey", true));
        songs.add(new Song(10, "Sweet Child O' Mine", "Guns N' Roses", true));

        // German karaoke favorites (some locked for progression)
        songs.add(new Song(11, "99 Luftballons", "Nena", true));
        songs.add(new Song(12, "Atemlos durch die Nacht", "Helene Fischer", false)); // Locked
        songs.add(new Song(13, "Major Tom", "Peter Schilling", false)); // Locked
        songs.add(new Song(14, "Du hast", "Rammstein", false)); // Locked
        songs.add(new Song(15, "Astronaut", "Sido feat. Andreas Bourani", false)); // Locked
    }

    @Override
    public List<Song> getAllSongs() {
        return new ArrayList<>(songs);
    }

    @Override
    public Song getSongById(int id) {
        for (Song song : songs) {
            if (song.getId() == id) {
                return song;
            }
        }
        return null;
    }

    @Override
    public List<Song> getSongsByArtist(String artist) {
        return songs.stream()
                .filter(s -> s.getArtist().equalsIgnoreCase(artist))
                .collect(Collectors.toList());
    }

    @Override
    public int getSongCount() {
        return songs.size();
    }

    /**
     * Gets only available (unlocked) songs.
     * 
     * @return List of available songs
     */
    public List<Song> getAvailableSongs() {
        return songs.stream()
                .filter(Song::isAvailable)
                .collect(Collectors.toList());
    }

    /**
     * Gets only locked (unavailable) songs.
     * 
     * @return List of locked songs
     */
    public List<Song> getLockedSongs() {
        return songs.stream()
                .filter(s -> !s.isAvailable())
                .collect(Collectors.toList());
    }

    /**
     * Unlocks a song by ID.
     * 
     * @param songId The ID of the song to unlock
     * @return true if song was found and unlocked
     */
    public boolean unlockSong(int songId) {
        Song song = getSongById(songId);
        if (song != null) {
            song.setAvailable(true);
            return true;
        }
        return false;
    }
}