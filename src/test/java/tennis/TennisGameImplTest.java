package tennis;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TennisGameImplTest {
    private final TennisGame game = new TennisGameImpl();
    @Test
    @DisplayName("Test that the score is 'Love-All' when no points have been scored.")
    void testLoveAll() {
        String score = game.score();
        assertThat(score).isEqualTo("Love-All");
    }

    @Test
    @DisplayName("Test that score is 'Love-Fifteen' when one of the player scores.")
    void testLoveFifteen() {
        game.point("Alfred");
        assertThat(game.score()).isEqualTo("Love-Fifteen");
    }

    @Test
    @DisplayName("Test that score is 'Love-Fourty' when a player scores 3 consecutive.")
    void testLoveFourty() {
        game.point("Alfred");
        game.point("Alfred");
        game.point("Alfred");
        assertThat(game.score()).isEqualTo("Love-Fourty");
    }

    @Test
    @DisplayName("Test that the score is 'Fifteen-Fourty' when the second player scores after Love-Fourty.")
    void testFifteenFourty() {
        game.point("Alfred");
        game.point("Alfred");
        game.point("Alfred");
        game.point("Bruce");
        assertThat(game.score()).isEqualTo("Fifteen-Fourty");
    }
    @Test
    @DisplayName("Test that the score is 'Deuce' when both players have 40 points.")
    void testDeuce() {
        game.point("Alfred");
        game.point("Alfred");
        game.point("Alfred");
        game.point("Bruce");
        game.point("Bruce");
        game.point("Bruce");
        assertThat(game.score()).isEqualTo("Deuce");
    }
    @Test
    @DisplayName("Test that the score is 'Advantage [Name]' when a player scores after a deuce.")
    void testAdvantage() {
        game.point("Alfred");
        game.point("Alfred");
        game.point("Alfred");
        game.point("Bruce");
        game.point("Bruce");
        game.point("Bruce");
        game.point("Bruce");
        assertThat(game.score()).isEqualTo("Advantage Bruce");
    }

    @Test
    @DisplayName("Test that the score is 'Deuce' again when a player comes back from a disadvantage.")
    void testDeuce2() {
        game.point("Alfred");
        game.point("Alfred");
        game.point("Alfred");
        game.point("Bruce");
        game.point("Bruce");
        game.point("Bruce");
        game.point("Bruce");
        game.point("Alfred");
        assertThat(game.score()).isEqualTo("Deuce");
    }

    @Test
    @DisplayName("Test that the score is 'Advantage [Name]' when a player scores after a second deuce.")
    void testAdvantage2() {
        game.point("Alfred");
        game.point("Alfred");
        game.point("Alfred");
        game.point("Bruce");
        game.point("Bruce");
        game.point("Bruce");
        game.point("Bruce");
        game.point("Alfred");
        game.point("Alfred");
        assertThat(game.score()).isEqualTo("Advantage Alfred");
    }

    @Test
    @DisplayName("Test that the score is 'Game [Name]' when a player scores after an advantage.")
    void testGame() {
        game.point("Alfred");
        game.point("Alfred");
        game.point("Alfred");
        game.point("Bruce");
        game.point("Bruce");
        game.point("Bruce");
        game.point("Bruce");
        game.point("Alfred");
        game.point("Alfred");
        game.point("Alfred");
        assertThat(game.score()).isEqualTo("Game Alfred");
    }

    @Test
    @DisplayName("Test that the score is 'Game [Name]' when a player scores after 30-40.")
    void testGame2() {
        game.point("Bruce");
        game.point("Bruce");
        game.point("Bruce");
        game.point("Alfred");
        game.point("Alfred");
        game.point("Bruce");
        assertThat(game.score()).isEqualTo("Game Bruce");

    }

    @Test
    @DisplayName("Test that score doesn't change when giving in a third player.")
    void testThirdPlayerIgnored() {
        game.point("Alfred");
        game.point("Alfred");
        game.point("Alfred");
        game.point("Bruce");
        game.point("Isaac");
        assertThat(game.score()).isEqualTo("Fifteen-Fourty");
    }

    @Test
    @DisplayName("Test that providing null or an empty String throws an IllegalArgumentException.")
    void testNullOrEmptyString() {
        assertThrows(IllegalArgumentException.class, () -> game.point(""));
        assertThrows(IllegalArgumentException.class, () -> game.point(null));
    }
}
