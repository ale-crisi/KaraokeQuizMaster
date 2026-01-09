package at.ac.hcw.kqm.model;
/**
 * Represents a player in the Karaoke Quiz Master game.
 * Stores player information including name, points, game state, and joker usage.
 */
public class Player {
    private int id;
    private String name;
    private String avatar;  // Avatar identifier (e.g., "avatar1", "avatar2")
    private int points;
    private int streak;  // Number of consecutive correct answers
    private boolean fiftyFiftyUsed;
    private boolean hintUsed;
    private boolean replaceQuestionUsed;
    private int turnOrder;  // Position in the player sequence (1, 2, 3, etc.)
    private boolean hasCompletedQuiz;  // Whether this player finished their quiz round

    /**
     * Constructor to create a new player.
     */
    public Player(int id, String name) {
        this.id = id;
        this.name = name;
        this.avatar = "default";
        this.points = 0;
        this.streak = 0;
        this.fiftyFiftyUsed = false;
        this.hintUsed = false;
        this.replaceQuestionUsed = false;
        this.turnOrder = 0;
        this.hasCompletedQuiz = false;
    }

    /**
     * Constructor with avatar.
     */
    public Player(int id, String name, String avatar) {
        this.id = id;
        this.name = name;
        this.avatar = avatar;
        this.points = 0;
        this.streak = 0;
        this.fiftyFiftyUsed = false;
        this.hintUsed = false;
        this.replaceQuestionUsed = false;
        this.turnOrder = 0;
        this.hasCompletedQuiz = false;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    /**
     * Adds points to the player's score.
     */
    public void addPoints(int points) {
        this.points += points;
    }

    public int getStreak() {
        return streak;
    }

    public void setStreak(int streak) {
        this.streak = streak;
    }

    /**
     * Increases the streak by 1 (called when player answers correctly).
     */
    public void incrementStreak() {
        this.streak++;
    }

    /**
     * Resets the streak to 0 (called when player answers incorrectly).
     */
    public void resetStreak() {
        this.streak = 0;
    }

    public boolean isFiftyFiftyUsed() {
        return fiftyFiftyUsed;
    }

    public void setFiftyFiftyUsed(boolean fiftyFiftyUsed) {
        this.fiftyFiftyUsed = fiftyFiftyUsed;
    }

    public boolean isHintUsed() {
        return hintUsed;
    }

    public void setHintUsed(boolean hintUsed) {
        this.hintUsed = hintUsed;
    }

    public boolean isReplaceQuestionUsed() {
        return replaceQuestionUsed;
    }

    public void setReplaceQuestionUsed(boolean replaceQuestionUsed) {
        this.replaceQuestionUsed = replaceQuestionUsed;
    }

    public int getTurnOrder() {
        return turnOrder;
    }

    public void setTurnOrder(int turnOrder) {
        this.turnOrder = turnOrder;
    }

    public boolean hasCompletedQuiz() {
        return hasCompletedQuiz;
    }

    public void setHasCompletedQuiz(boolean hasCompletedQuiz) {
        this.hasCompletedQuiz = hasCompletedQuiz;
    }

    /**
     * Checks if a specific joker type is still available.
     */
    public boolean isJokerAvailable(JokerType jokerType) {
        switch (jokerType) {
            case FIFTY_FIFTY:
                return !fiftyFiftyUsed;
            case HINT:
                return !hintUsed;
            case REPLACE_QUESTION:
                return !replaceQuestionUsed;
            default:
                return false;
        }
    }

    /**
     * Marks a joker as used.
     */
    public void useJoker(JokerType jokerType) {
        switch (jokerType) {
            case FIFTY_FIFTY:
                fiftyFiftyUsed = true;
                break;
            case HINT:
                hintUsed = true;
                break;
            case REPLACE_QUESTION:
                replaceQuestionUsed = true;
                break;
        }
    }

    /**
     * Resets all jokers to unused state (for a new game).
     */
    public void resetJokers() {
        this.fiftyFiftyUsed = false;
        this.hintUsed = false;
        this.replaceQuestionUsed = false;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", avatar='" + avatar + '\'' +
                ", points=" + points +
                ", streak=" + streak +
                ", turnOrder=" + turnOrder +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return id == player.id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }
}