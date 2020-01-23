/**
 * ScoreInfo.
 * @author ahmed.
 *         since 04.06.2017.
 */
public class ScoreInfo {
    private String theName;
    private int theScore;

    // Constructor, accepts a name and a score of a player.
    /**
     * @param name of player.
     * @param score of player.
     */
    public ScoreInfo(String name, int score) {
        this.theName = name;
        this.theScore = score;
    }

    // returns the name of a player.
    /**
     * getName function.
     * @return theName of player.
     */
    public String getName() {
        return this.theName;
    }

    // returns the score of a player.
    /**
     * @return theScore of the player.
     */
    public int getScore() {
        return this.theScore;
    }
}