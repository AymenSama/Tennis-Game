package tennis;

public interface TennisGame {
    /**
     * Increases the score of the player with the name playerName by 1.
     * @param playerName Name of one of the players.
     */
    void point(String playerName);


    /**
     * @return Current score according to
     * @see <a href="https://en.wikipedia.org/wiki/Tennis_scoring_system">scoring system in a game</a>
     *
     */
    String score();
}
