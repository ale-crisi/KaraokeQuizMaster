package at.ac.hcw.kqm.persistence;

import at.ac.hcw.kqm.model.Question;

import java.util.List;

/**
 * Interface for accessing quiz questions.
 * Defines methods for retrieving questions from a data source.
 */
public interface QuestionRepository {

    /**
     * Retrieves all available questions.
     * @return A list of all questions
     */
    List<Question> getAllQuestions();

    /**
     * Retrieves a specific question by its ID.
     * @param id The unique identifier of the question
     * @return The question with the given ID, or null if not found
     */
    Question getQuestionById(int id);

    /**
     * Retrieves questions related to a specific song.
     * @param songId The ID of the song
     * @return A list of questions about that song
     */
    List<Question> getQuestionsBySongId(int songId);

    /**
     * Gets the total number of questions available.
     * @return The count of all questions
     */
    int getQuestionCount();
}