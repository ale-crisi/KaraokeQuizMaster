package at.ac.hcw.kqm.persistence;

import at.ac.hcw.kqm.model.Question;

import java.util.List;

/**
 * Interface für den Zugriff auf Quizfragen.
 * Definiert Methoden zum Laden von Fragen
 * aus einer Datenquelle.
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