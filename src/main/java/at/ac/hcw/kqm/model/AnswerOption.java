package at.ac.hcw.kqm.model;
/**
 * Repräsentiert eine einzelne Antwortmöglichkeit für eine Quizfrage.
 * Jede Antwortmöglichkeit hat eine eindeutige ID und einen Text.

 * Attribute:
 * private int id;
 * private String text;

 * Konstruktor:
 * Erstellt eine neue Antwortmöglichkeit.
 */

public class AnswerOption {

    private int id;
    private String text;

    public AnswerOption(int id, String text) {
        this.id = id;
        this.text = text;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "AnswerOption{" +
                "id=" + id +
                ", text='" + text + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnswerOption that = (AnswerOption) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }
}

