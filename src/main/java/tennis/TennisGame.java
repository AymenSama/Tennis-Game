package tennis;

public interface TennisGame {
    /**
     * Erhöht den Punktestand des Players mit dem Namen playerName um 1
     * @param playerName Name eines der Spieler
     * @throws IllegalArgumentException null oder ein leeres String übergeben
     */
    void point(String playerName);


    /**
     * @return Aktueller Spielstand nach
     * @see <a href="https://de.wikipedia.org/wiki/Tennis#Gliederung_und_Z%C3%A4hlweise">Zählweise in einem Spiel</a>
     *
     */
    String score();
}
