package at.ac.hcw.kqm.persistence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Liest LRC-Dateien aus dem Classpath und wandelt sie in Zeit+Text-Zeilen um.
 * Format: [mm:ss]Text oder [mm:ss.xx]Text oder [mm:ss.xxx]Text
 */
public class LrcLyricLoader {

    /** Eine Zeile mit Startzeit (ms) und Text. */
    public static class TimedLine {
        public final long timeMs;
        public final String text;

        public TimedLine(long timeMs, String text) {
            this.timeMs = timeMs;
            this.text = text;
        }
    }

    // Zeitstempel: [mm:ss] oder [mm:ss.xx] oder [mm:ss.xxx]
    private static final Pattern TIME_TAG =
            Pattern.compile("\\[(\\d{1,2}):(\\d{2})(?:\\.(\\d{1,3}))?\\]");

    /** Lädt eine LRC-Datei als Resource (z.B. "/.../lyrics/song.lrc"). */
    public List<TimedLine> loadFromClasspath(String classpathResource) {
        if (classpathResource == null || classpathResource.isBlank()) {
            return List.of();
        }

        try (InputStream is = LrcLyricLoader.class.getResourceAsStream(classpathResource)) {
            if (is == null) {
                System.err.println("[LrcLoader] Resource nicht gefunden: " + classpathResource);
                return List.of();
            }
            return parse(is);
        } catch (IOException e) {
            throw new RuntimeException("Lyrics konnten nicht gelesen werden: " + classpathResource, e);
        }
    }

    /** Parst den InputStream und erzeugt eine sortierte Liste aus TimedLines. */
    private List<TimedLine> parse(InputStream is) throws IOException {
        List<TimedLine> result = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
            String rawLine;

            while ((rawLine = br.readLine()) != null) {
                String line = rawLine.strip();
                if (line.isEmpty()) continue;

                // Metadaten ignorieren (z.B. [ar:...], [ti:...], [al:...])
                if (line.matches("\\[[a-zA-Z]+:.*\\]")) continue;

                Matcher matcher = TIME_TAG.matcher(line);

                // Alle Zeitstempel in der Zeile sammeln (manche LRC-Zeilen haben mehrere)
                List<Long> times = new ArrayList<>();
                int lastEnd = -1;

                while (matcher.find()) {
                    long ms = toMillis(matcher.group(1), matcher.group(2), matcher.group(3));
                    times.add(ms);
                    lastEnd = matcher.end();
                }

                if (times.isEmpty()) continue;

                // Text steht nach dem letzten Zeitstempel
                String text = (lastEnd >= 0) ? line.substring(lastEnd).strip() : "";
                if (text.isEmpty()) text = " ";

                for (long t : times) {
                    result.add(new TimedLine(t, text));
                }
            }
        }

        // Nach Startzeit sortieren
        result.sort(Comparator.comparingLong(a -> a.timeMs));
        return result;
    }

    /** Wandelt mm:ss(.fff) in Millisekunden um. */
    private long toMillis(String mm, String ss, String frac) {
        int minutes = Integer.parseInt(mm);
        int seconds = Integer.parseInt(ss);

        int millis = 0;
        if (frac != null) {
            // 1 Stelle: .5  -> 500ms
            // 2 Stellen: .50 -> 500ms
            // 3 Stellen: .500 -> 500ms
            if (frac.length() == 1) millis = Integer.parseInt(frac) * 100;
            else if (frac.length() == 2) millis = Integer.parseInt(frac) * 10;
            else millis = Integer.parseInt(frac);
        }

        return minutes * 60_000L + seconds * 1_000L + millis;
    }
}
