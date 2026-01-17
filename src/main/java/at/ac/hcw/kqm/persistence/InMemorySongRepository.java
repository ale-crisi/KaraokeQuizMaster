package at.ac.hcw.kqm.persistence;

import at.ac.hcw.kqm.model.Song;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class InMemorySongRepository implements SongRepository {

    private List<Song> songs;


    public InMemorySongRepository() {
        this.songs = new ArrayList<>();
        initializeSongs();
    }


    private void initializeSongs() {
        LrcLyricLoader loader = new LrcLyricLoader();

        Song s1 = new Song(1, "Simarik", "Tarkan", true);
        songs.add(s1);

        Song s2 = new Song(2, "It's My Life", "Bon Jovi", true);
        s2.setAudioFilePath("/at/ac/hcw/kqm/ui/fxml/assets/audio/Bon Jovi - It's My Life.mpeg");
        s2.setLyricsFilePath("/at/ac/hcw/kqm/ui/fxml/assets/lyrics/Bon Jovi - It's My Life.lrc");
        applyLyricsFromLrc(s2, loader);
        songs.add(s2);

        Song s3 = new Song(3, "Hit Me Baby One More Time", "Britney Spears", true);
        s3.setAudioFilePath("/at/ac/hcw/kqm/ui/fxml/assets/audio/Britney Spears - ... Baby One More Time.mpeg");
        s3.setLyricsFilePath("/at/ac/hcw/kqm/ui/fxml/assets/lyrics/Britney Spears - Hit Me Baby One More Time.lrc");
        applyLyricsFromLrc(s3, loader);
        songs.add(s3);

        songs.add(new Song(4, "Rolling in the Deep", "Adele", true));
        songs.add(new Song(5, "Blinding Lights", "The Weeknd", true));
        songs.add(new Song(6, "Imagine", "John Lennon", true));
        songs.add(new Song(7, "Someone Like You", "Adele", true));
        songs.add(new Song(8, "Hotel California", "Eagles", true));
        songs.add(new Song(9, "Don't Stop Believin'", "Journey", true));
        songs.add(new Song(10, "Sweet Child O' Mine", "Guns N' Roses", true));

    }

    /**
     * Loads lyrics from LRC file and applies them to the song.
     *
     * @param song Song to populate with lyrics
     * @param loader LRC loader instance
     */
    private void applyLyricsFromLrc(Song song, LrcLyricLoader loader) {
        try {
            String lrcPath = song.getLyricsFilePath();
            if (lrcPath == null || lrcPath.isBlank()) {
                return;
            }

            List<LrcLyricLoader.TimedLine> lines = loader.loadFromClasspath(lrcPath);
            for (LrcLyricLoader.TimedLine tl : lines) {
                song.addLyricLine(tl.timeMs, tl.text);
            }

            System.out.println("✓ Lyrics loaded successfully");
            System.out.println("  Song: " + song.getDisplayName());
            System.out.println("  Lines: " + lines.size());
        } catch (Exception e) {
            System.err.println("✗ Failed to load lyrics");
            System.err.println("  Song: " + song.getDisplayName());
            System.err.println("  Error: " + e.getMessage());
        }
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