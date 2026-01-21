package at.ac.hcw.kqm.model;

/**
 * Repräsentiert eine einzelne Zeile eines Songtexts.
 * Speichert den Text der Zeile und den Zeitpunkt,
 * ab dem sie angezeigt werden soll.
 */

public class LyricLine {
    private long startTime;
    private String text;

    public LyricLine(long startTime, String text) {
        this.startTime = startTime;
        this.text = text;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return String.format("[%dms] %s", startTime, text);
    }
}
