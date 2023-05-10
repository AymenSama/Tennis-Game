package tennis;

public class TennisGameImpl implements TennisGame {
    private static class TennisPlayer {
        String name;
        String points = "Love";
        boolean hasAdvantage;
        boolean winsGame;
    }

    private final TennisPlayer player1 = new TennisPlayer();
    private final TennisPlayer player2 = new TennisPlayer();

    public TennisGameImpl(String player1, String player2) {
        this.player1.name = player1;
        this.player2.name = player2;
    }

    @Override
    public void point(String playerName) {

        if(playerName == null || playerName.equals("")) {
            throw new IllegalArgumentException("Expected: a non empty string\nActual: empty string or null");
        }

        if (player1.name.equals(playerName)) {
            updatePoints(player1, player2);
        } else if (player2.name.equals(playerName)) {
            updatePoints(player2, player1);
        }

    }

    private void updatePoints(TennisPlayer player1, TennisPlayer player2) {
        switch (player1.points) {
            case "Love" -> player1.points = "Fifteen";
            case "Fifteen" -> player1.points = "Thirty";
            case "Thirty" -> player1.points = "Fourty";
            case "Fourty" -> scoreWithFourtyPoints(player1, player2);
        }
    }
    private void scoreWithFourtyPoints(TennisPlayer playerA, TennisPlayer playerB) {
        if(!playerB.points.equals("Fourty") || playerA.hasAdvantage) {
            playerA.winsGame = true;
            return;
        }

        if(playerB.hasAdvantage) {
            playerB.hasAdvantage = false;
            return;
        }

        playerA.hasAdvantage = true;
    }

    @Override
    public String score() {
        if(player1.winsGame) {
            return "Game " + player1.name;
        }

        if(player2.winsGame) {
            return "Game " + player2.name;
        }

        if(player1.hasAdvantage) {
            return "Advantage " + player1.name;
        }

        if(player2.hasAdvantage) {
            return "Advantage " + player2.name;
        }

        if (player1.points.equals("Fourty") && player2.points.equals(player1.points)) {
            return "Deuce";
        }

        if (player1.points.equals(player2.points)) {
            return player1.points + "-All";
        }

        return player2.points + "-" + player1.points;
    }




}
