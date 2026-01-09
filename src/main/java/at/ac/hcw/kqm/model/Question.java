package at.ac.hcw.kqm.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a quiz question in the Karaoke Quiz Master game.
 * Each question is specifically related to ONE song.
 * After a player chooses a song, they answer questions about that song.
 */
public class Question {
    private int id;
    private int songId; // REQUIRED: Every question must be linked to a song
    private String questionText;
    private List<AnswerOption> answerOptions;
    private int correctOptionId;
    private String hint; // Optional hint for the HINT joker

    /**
     * Constructor to create a new question.
     */
    public Question(int id, int songId, String questionText, int correctOptionId) {
        this.id = id;
        this.songId = songId;
        this.questionText = questionText;
        this.answerOptions = new ArrayList<>();
        this.correctOptionId = correctOptionId;
        this.hint = "";
    }

    /**
     * Constructor with hint.
     */
    public Question(int id, int songId, String questionText, int correctOptionId, String hint) {
        this.id = id;
        this.songId = songId;
        this.questionText = questionText;
        this.answerOptions = new ArrayList<>();
        this.correctOptionId = correctOptionId;
        this.hint = hint;
    }

    public Question() {
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSongId() {
        return songId;
    }

    public void setSongId(int songId) {
        this.songId = songId;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public List<AnswerOption> getAnswerOptions() {
        return answerOptions;
    }

    public void setAnswerOptions(List<AnswerOption> answerOptions) {
        this.answerOptions = answerOptions;
    }

    /**
     * Adds a single answer option to this question.
     *
     * @param option The answer option to add
     */
    public void addAnswerOption(AnswerOption option) {
        this.answerOptions.add(option);
    }

    public int getCorrectOptionId() {
        return correctOptionId;
    }

    public void setCorrectOptionId(int correctOptionId) {
        this.correctOptionId = correctOptionId;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    /**
     * Checks if a given answer option ID is the correct answer.
     *
     * @param optionId The ID of the answer option to check
     * @return true if the option is correct, false otherwise
     */
    public boolean isCorrectAnswer(int optionId) {
        return optionId == correctOptionId;
    }

    /**
     * Gets the correct answer option object.
     *
     * @return The AnswerOption that is correct, or null if not found
     */
    public AnswerOption getCorrectAnswer() {
        for (AnswerOption option : answerOptions) {
            if (option.getId() == correctOptionId) {
                return option;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", songId=" + songId +
                ", questionText='" + questionText + '\'' +
                ", answerOptions=" + answerOptions.size() +
                ", correctOptionId=" + correctOptionId +
                '}';
    }

    public boolean isCorrect(int selectedOptionId) {
        return selectedOptionId == correctOptionId;
    }
}