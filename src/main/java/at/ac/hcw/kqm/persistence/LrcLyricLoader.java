package at.ac.hcw.kqm.persistence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Loads synchronized lyrics from LRC files (classpath resources).
 * LRC Format: [mm:ss.xx]Lyric text
 *
 * Example:
 * [00:12.50]First line
 * [00:17.30]Second line
 */
public class LrcLyricLoader {

    /**
     * Represents a single timed lyric line.
     */
    public static class TimedLine {
        public final long timeMs;
        public final String text;

        public TimedLine(long timeMs, String text) {
            this.timeMs = timeMs;
            this.text = text;
        }
    }

    // Pattern for LRC timestamp: [mm:ss.xx] or [m:ss.xxx]
    private static final Pattern TIME_TAG =
            Pattern.compile("\\[(\\d{1,2}):(\\d{2})(?:\\.(\\d{1,3}))?\\]");

    /**
     * Loads lyrics from a classpath resource.
     *
     * @param classpathResource Path like "/at/ac/hcw/kqm/ui/fxml/assets/lyrics/Song.lrc"
     * @return List of timed lines, sorted by timestamp
     * @throws RuntimeException if file not found or cannot be read
     */
    public List<TimedLine> loadFromClasspath(String classpathResource) {
        if (classpathResource == null || classpathResource.isBlank()) {
            return List.of();
        }

        try (InputStream is = getClass().getResourceAsStream(classpathResource)) {
            if (is == null) {
                System.err.println("[LrcLoader] Resource not found: " + classpathResource);
                return List.of();
            }
            return parse(is);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read lyrics: " + classpathResource, e);
        }
    }

    /**
     * Parses an LRC file from InputStream.
     */
    private List<TimedLine> parse(InputStream is) throws IOException {
        List<TimedLine> out = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.strip();
                if (line.isEmpty()) continue;

                // Skip metadata tags like [ar:Artist], [ti:Title], [al:Album]
                if (line.matches("\\[[a-zA-Z]+:.*\\]")) continue;

                // Extract all timestamps from the line
                Matcher m = TIME_TAG.matcher(line);
                List<Long> times = new ArrayList<>();
                int lastEnd = 0;

                while (m.find()) {
                    times.add(parseToMs(m.group(1), m.group(2), m.group(3)));
                    lastEnd = m.end();
                }

                if (times.isEmpty()) continue;

                // Text after all timestamps
                String text = line.substring(lastEnd).strip();
                if (text.isEmpty()) text = " ";

                // Add entry for each timestamp (some LRC lines have multiple times)
                for (Long t : times) {
                    out.add(new TimedLine(t, text));
                }
            }
        }

        // Sort by timestamp
        out.sort((a, b) -> Long.compare(a.timeMs, b.timeMs));
        return out;
    }

    /**
     * Converts LRC timestamp to milliseconds.
     *
     * @param mm Minutes (1-2 digits)
     * @param ss Seconds (2 digits)
     * @param frac Fractional seconds (1-3 digits, can be null)
     * @return Total milliseconds
     */
    private long parseToMs(String mm, String ss, String frac) {
        int minutes = Integer.parseInt(mm);
        int seconds = Integer.parseInt(ss);

        int millis = 0;
        if (frac != null) {
            if (frac.length() == 1) {
                millis = Integer.parseInt(frac) * 100;   // .5 = 500ms
            } else if (frac.length() == 2) {
                millis = Integer.parseInt(frac) * 10;     // .50 = 500ms
            } else {
                millis = Integer.parseInt(frac);          // .500 = 500ms
            }
        }

        return minutes * 60_000L + seconds * 1_000L + millis;
    }
}