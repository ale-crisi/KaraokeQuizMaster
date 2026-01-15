package at.ac.hcw.kqm.model;

/**
 * Represents a single line of lyrics with its start time in milliseconds.
 */
public class LyricLine {
    private long startTime;  // Time in milliseconds when this line should appear
    private String text;     // The lyrics text for this line

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
