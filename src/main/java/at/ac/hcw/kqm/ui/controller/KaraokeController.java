package at.ac.hcw.kqm.ui.controller;

import at.ac.hcw.kqm.app.AppState;
import at.ac.hcw.kqm.model.LyricLine;
import at.ac.hcw.kqm.model.Player;
import at.ac.hcw.kqm.model.Song;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.text.Normalizer;
import java.util.List;

public class KaraokeController {

    @FXML
    private Button playButton, pauseButton, stopButton, nextButton;
    @FXML
    private Label songTitleLabel, lyricsPrevious, lyricsCurrent, lyricsCurrentGold, lyricsNext;
    @FXML
    private StackPane currentLineContainer;

    private List<Player> ranking;
    private List<Song> selectedSongs;
    private int idx = 0;
    private MediaPlayer player;
    private int currentLyricIndex = -1;
    private Rectangle clipRect;

    @FXML
    public void initialize() {
        try {
            System.out.println("[Karaoke] initialize() called");
            ranking = AppState.get().getEngine().getRanking();
            selectedSongs = AppState.get().getSelectedSongs();
            System.out.println("[Karaoke] Players: " + ranking.size() + ", Songs: " + selectedSongs.size());

            // Setup clip rectangle for gold text animation
            clipRect = new Rectangle();
            clipRect.setWidth(0);
            clipRect.setHeight(1000); // Large enough to cover text
            lyricsCurrentGold.setClip(clipRect);

            // Initialize lyrics display
            clearLyrics();

            showCurrent();

            playButton.setOnAction(e -> {
                if (player != null) {
                    player.play();
                    System.out.println("[Karaoke] Playing audio");
                } else {
                    System.err.println("[Karaoke] No MediaPlayer available");
                }
            });
            pauseButton.setOnAction(e -> {
                if (player != null) {
                    player.pause();
                    System.out.println("[Karaoke] Paused audio");
                }
            });
            stopButton.setOnAction(e -> {
                if (player != null) {
                    player.stop();
                    System.out.println("[Karaoke] Stopped audio");
                }
            });
            nextButton.setOnAction(e -> {
                idx = (idx + 1) % ranking.size();
                showCurrent();
            });
            System.out.println("[Karaoke] initialize() completed successfully");
        } catch (Exception e) {
            System.err.println("[Karaoke] ERROR in initialize(): " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    private void showCurrent() {
        try {
            System.out.println("[Karaoke] showCurrent() called");
            Player p = ranking.get(idx);
            Song s = AppState.get().getSongForPlayer(p);

            System.out.println("[Karaoke] Player: " + p.getName() + ", Song: " + s.getDisplayName());
            currentLyricIndex = -1;
            clearLyrics();
            if (songTitleLabel != null && s != null) {
                songTitleLabel.setText(s.getTitle() + " – " + s.getArtist());
            }

            // If audio path set, resolve to playable URI (classpath, file, or URL)
            if (s != null) {
                String configuredPath = s.getAudioFilePath();
                String uri = null;
                if (configuredPath != null && !configuredPath.isEmpty()) {
                    uri = resolveToMediaUri(configuredPath);
                }

                // Try fallback discovery if direct resolution failed
                if (uri == null) {
                    System.out.println("[Karaoke] Attempting fallback search for audio...");
                    uri = findFallbackMediaUri(s);
                    if (uri != null) {
                        // Cache found URI into song to avoid re-search
                        s.setAudioFilePath(uri);
                        System.out.println("[Karaoke] Found audio via fallback: " + uri);
                    }
                }

                if (uri != null) {
                    // Clean up old player
                    if (player != null) {
                        player.stop();
                        player.dispose();
                    }

                    Media media = new Media(uri);
                    player = new MediaPlayer(media);

                    // Add listener for synchronized lyrics
                    player.currentTimeProperty().addListener((obs, oldVal, newVal) -> {
                        updateLyrics(s, newVal.toMillis());
                    });

                    player.setOnReady(() -> {
                        System.out.println("[Karaoke] MediaPlayer ready, duration: " + player.getTotalDuration());
                    });

                    player.setOnError(() -> {
                        System.err.println("[Karaoke] MediaPlayer error: " + player.getError());
                    });

                    System.out.println("[Karaoke] MediaPlayer created successfully for URI: " + uri);
                } else {
                    player = null;
                    System.err.println("[Karaoke] Audio not found for song: " + s.getDisplayName());
                }
            }
            System.out.println("[Karaoke] showCurrent() completed");
        } catch (Exception e) {
            System.err.println("[Karaoke] ERROR in showCurrent(): " + e.getMessage());
            e.printStackTrace();
        }
    }

    private String resolveToMediaUri(String path) {
        try {
            // Classpath resource (starts with '/')
            if (path.startsWith("/")) {
                URL res = KaraokeController.class.getResource(path);
                return res != null ? res.toExternalForm() : null;
            }
            // Already a URI (file:, http:, https:)
            if (path.startsWith("file:") || path.startsWith("http:") || path.startsWith("https:")) {
                return path;
            }
            // Keine Dateisystemzugriffe mehr erlaubt, daher null zurückgeben
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    // Attempts to find an audio file by scanning the audio resource folder
    // using normalized title/artist matches. Returns a Media-friendly URI or null.
    private String findFallbackMediaUri(Song s) {
        try {
            // Suche nach Audiodateien ausschließlich im Classpath-Ordner
            String[] extensions = { ".mp3", ".m4a", ".wav" };
            String basePath = "/at/ac/hcw/kqm/ui/fxml/assets/audio/";
            String artistN = normalize(s.getArtist());
            String titleN = normalize(s.getTitle());
            for (String ext : extensions) {
                String candidate = basePath + artistN + " - " + titleN + ext;
                URL res = KaraokeController.class.getResource(candidate);
                if (res != null) {
                    System.out.println("[Karaoke] Found audio resource: " + candidate);
                    return res.toExternalForm();
                }
            }
        } catch (Exception e) {
            System.err.println("[Karaoke] Error in fallback search: " + e.getMessage());
        }
        return null;
    }



    private int scoreTitleMatch(String titleNorm, String nameNorm) {
        String[] tokens = titleNorm.split(" ");
        int hits = 0;
        for (String t : tokens) {
            if (t.isEmpty()) continue;
            if (nameNorm.contains(t)) hits++;
        }
        // weight longer titles a bit higher
        return hits;
    }

    private String normalize(String s) {
        if (s == null) return "";
        String n = s.toLowerCase();
        n = n.replace('\u2026', ' '); // ellipsis … -> space
        n = Normalizer.normalize(n, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
        n = n.replaceAll("[^a-z0-9 ]", " ")
                .replaceAll("\\s+", " ")
                .trim();
        return n;
    }

    private void updateLyrics(Song s, double currentTimeMs) {
        if (s == null || s.getLyrics() == null || s.getLyrics().isEmpty()) {
            clearLyrics();
            return;
        }

        List<LyricLine> lyricLines = s.getLyrics();

        // Binary search for better performance (O(log n) instead of O(n))
        int nextIndex = findCurrentLyricIndex(lyricLines, (long) currentTimeMs);

        // Update only if we've moved to a new line
        if (nextIndex != currentLyricIndex && nextIndex >= 0) {
            currentLyricIndex = nextIndex;
            updateLyricsDisplay(lyricLines, nextIndex);
        }

        // Update gold effect progress for current line
        if (currentLyricIndex >= 0 && currentLyricIndex < lyricLines.size()) {
            updateGoldProgress(lyricLines, currentLyricIndex, currentTimeMs);
        }
    }

    /**
     * Updates the 3-line karaoke display (previous, current, next).
     */
    private void updateLyricsDisplay(List<LyricLine> lines, int currentIndex) {
        // Previous line (dimmed)
        if (currentIndex > 0) {
            lyricsPrevious.setText(lines.get(currentIndex - 1).getText());
        } else {
            lyricsPrevious.setText("");
        }

        // Current line (white + gold overlay)
        String currentText = lines.get(currentIndex).getText();
        lyricsCurrent.setText(currentText);
        lyricsCurrentGold.setText(currentText);

        // Reset gold clip for new line
        clipRect.setWidth(0);

        // Next line (dimmed)
        if (currentIndex < lines.size() - 1) {
            lyricsNext.setText(lines.get(currentIndex + 1).getText());
        } else {
            lyricsNext.setText("");
        }
    }

    /**
     * Updates the gold color progress from left to right based on time within current line.
     */
    private void updateGoldProgress(List<LyricLine> lines, int currentIndex, double currentTimeMs) {
        long lineStartTime = lines.get(currentIndex).getStartTime();
        long lineEndTime;

        // Determine end time (start of next line or arbitrary duration)
        if (currentIndex < lines.size() - 1) {
            lineEndTime = lines.get(currentIndex + 1).getStartTime();
        } else {
            lineEndTime = lineStartTime + 5000; // 5 seconds default for last line
        }

        long lineDuration = lineEndTime - lineStartTime;
        long elapsed = (long) currentTimeMs - lineStartTime;

        // Calculate progress percentage (0.0 to 1.0)
        double progress = Math.min(1.0, Math.max(0.0, (double) elapsed / lineDuration));

        // Update clip width based on progress
        double labelWidth = lyricsCurrentGold.getWidth();
        if (labelWidth > 0) {
            clipRect.setWidth(labelWidth * progress);
        }
    }

    /**
     * Clears all lyric displays.
     */
    private void clearLyrics() {
        lyricsPrevious.setText("");
        lyricsCurrent.setText("");
        lyricsCurrentGold.setText("");
        lyricsNext.setText("");
        clipRect.setWidth(0);
    }

    private int findCurrentLyricIndex(List<LyricLine> lines, long currentTimeMs) {
        if (lines.isEmpty()) return -1;

        int left = 0;
        int right = lines.size() - 1;
        int result = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            long midTime = lines.get(mid).getStartTime();

            if (midTime <= currentTimeMs) {
                result = mid;  // This line is valid, but there might be a later one
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return result;
    }
}
