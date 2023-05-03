package tennis;

public class TennisGameImpl implements TennisGame {
    private final TennisPlayer player1 = new TennisPlayer();
    private final TennisPlayer player2 = new TennisPlayer();

    @Override
    public void point(String playerName) throws IllegalArgumentException {

        if(playerName == null || playerName.equals("")) {
            throw new IllegalArgumentException("Provided: null or an empty String\nExpected: a non empty String for a player name");
        }

        if (player1.points.equals("Love")) {
            initPlayer(player1 ,playerName);
            return;
        }

        if (player1.name.equals(playerName)) {
            updatePoints(player1, player2);
            return;
        }

        if (player2.points.equals("Love")) {
            initPlayer(player2 ,playerName);
            return;
        }

        if (player2.name.equals(playerName)) {
            updatePoints(player2, player1);
        }


    }

    private void updatePoints(TennisPlayer player1, TennisPlayer player2) {
        switch (player1.points) {
            case "Fifteen" -> player1.points = "Thirty";
            case "Thirty" -> player1.points = "Fourty";
            case "Fourty" -> scoreWithFourtyPoints(player1, player2);
        }
    }

    private void initPlayer(TennisPlayer player , String playerName) {
        player.name = playerName;
        player.points = "Fifteen";
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
