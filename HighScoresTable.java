import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * HighScoresTable.
 * @author ahmed.
 * @since 15.6.2017.
 */
class HighScoresTable {
    private int tableSize;
    private List<ScoreInfo> highScore;

    // Constructor, Create an empty high-scores table with the specified size.
    // The size means that the table holds up to size top scores.
    /**
     * @param size of highScoreList.
     */
    public HighScoresTable(int size) {
        this.highScore = new ArrayList<ScoreInfo>();
        this.tableSize = size;
    }

    // another constructor for HighScoresTable, i made this constructor with an
    // already existed scores list,
    // so there is no empty scoresList.
    /**
     * @param scoreList not empty.
     * @param size of the full list.
     */
    public HighScoresTable(List<ScoreInfo> scoreList, int size) {
        this.highScore = scoreList;
        this.tableSize = size;
    }

    // Add a high-score to the list of scores.
    /**
     * @param score to be added.
     */
    public void add(ScoreInfo score) {
        List<ScoreInfo> newTable = new ArrayList<ScoreInfo>(
                this.highScore);
        // in case scoreList was at full-size, we must delete a score in order
        // to add the new one (if meets the conditions to be added).
        if (newTable.size() >= this.tableSize) {
            for (int i = 0; i < newTable.size() - 1; i++) {
                if (score.getScore() >= newTable
                        .get(newTable.size() - 1).getScore()) {
                    newTable.set(newTable.size() - 1, score);
                }
            }
        } else {
            // adding the score the highScores list becuase the list wasn't full
            // yet.
            newTable.add(score);
        }
        // sorting the list so that the highScores come first.
        newTable = this.sortList(newTable);
        this.highScore = newTable;
    }

    // sorts The list such that the highest
    // scores come first.
    /**
     * @param newTable the list.
     * @return newTable sorted list.
     */
    private List<ScoreInfo> sortList(List<ScoreInfo> newTable) {
        for (int i = 0; i < newTable.size(); i++) {
            for (int j = 0; j < newTable.size() - i - 1; j++) {
                if (newTable.get(j).getScore() < newTable.get(j + 1)
                        .getScore()) {
                    ScoreInfo temp = newTable.get(j);
                    newTable.set(j, newTable.get(j + 1));
                    newTable.set(j + 1, temp);
                }
            }
        }
        return newTable;
    }

    // Return table size.
    /**
     * @return size of score-table.
     */
    public int size() {
        return this.tableSize;
    }

    // Return the current high scores.
    // The list is sorted such that the highest
    // scores come first.
    /**
     * @return listOfHighscores.
     */
    public List<ScoreInfo> getHighScores() {
        return this.highScore;
    }

    // return the rank of the current score: where will it
    // be on the list if added?
    // Rank 1 means the score will be highest on the list.
    // Rank `size` means the score will be lowest.
    // Rank > `size` means the score is too low and will not
    // be added to the list.
    /**
     * @param score of player.
     * @return theRank of the player's score.
     */
    public int getRank(int score) {
        int checkRank = 0;
        for (int i = 0; i < this.highScore.size(); i++) {
            if (score > this.highScore.get(i).getScore()) {
                checkRank++;
                break;
            }
            checkRank++;
        }
        return checkRank;
    }

    // Clears the scores table.
    /**
     * clear function.
     */
    public void clear() {
        List<ScoreInfo> clearance = new ArrayList<ScoreInfo>();
        this.highScore = clearance;
    }

    // Load table data from file.
    // Current table data is cleared.
    /**
     * @param filename the name of the file we want to load from
     * @throws IOException in case we couldn't open or close a file.
     */
    public void load(File filename) throws IOException {
        BufferedReader inputStr = null;
        // initializing an empty scores list.
        this.highScore.clear();
        try {
            inputStr = new BufferedReader(new InputStreamReader(
                    new FileInputStream(filename)));
            String line = inputStr.readLine();
            while (line != null) {
                String[] nameAndScore = line.split(",");
                int score = Integer.parseInt(nameAndScore[1]);
                this.add(new ScoreInfo(nameAndScore[0], score));
                line = inputStr.readLine();
            }
        } catch (IOException e) {
            System.out.println(
                    " Something went wrong while reading the file !");
        } finally {
            if (inputStr != null) { // Exception might have happened at
                                    // constructor
                try {
                    inputStr.close(); // closes FileInputStream too
                } catch (IOException e) {
                    System.out
                            .println(" Failed at closing the file !");
                }
            }
        }
    }

    /**
     * Saving the table's data to the specified file.
     * @param filename which is the file we want save data into.
     * @throws IOException in case we couldn't open or close a file.
     */
    public void save(File filename) throws IOException {
        PrintWriter os = null;
        try {
            os = new PrintWriter(new OutputStreamWriter(
                    new FileOutputStream(filename)));
            for (ScoreInfo s : this.highScore) {
                os.printf("%s", s.getName());
                os.printf("%s", ",");
                os.println(s.getScore());
            } // writing to the file
        } catch (IOException e) {
            System.out.println(
                    " Something went wrong while writing the file !");
        } finally {
            if (os != null) { // Exception might have occurred at the
                              // constructor.
                os.close(); // the fileOutputStream is closed too.
            }
        }
    }

    // Read a table from file and return it.
    // If the file does not exist, or there is a problem with
    // reading it, an empty table is returned.
    /**
     * @param filename the file name we want to load from.
     * @param size the size of the table if the file does not exist.
     * @return If the file does not exist, or there is a problem with
     *         reading it, an empty table is returned.
     */
    public static HighScoresTable loadFromFile(File filename,
            int size) {
        BufferedReader is = null;
        int i = 0;
        try {
            ArrayList<ScoreInfo> scoreTable = new ArrayList<>();
            is = new BufferedReader(new InputStreamReader(
                    new FileInputStream(filename)));
            String line;
            line = is.readLine();
            while ((line != null) && i < size) {
                String[] nameAndScore = line.split(",");
                int score = Integer.parseInt(nameAndScore[1]);
                scoreTable.add(new ScoreInfo(nameAndScore[0], score));
                i++;
                line = is.readLine();
            }
            return new HighScoresTable(scoreTable, size);
        } catch (IOException e) {
            return new HighScoresTable(size);
        } finally {
            if (is != null) { // Exception might have occurred at the
                              // constructor
                try {
                    is.close(); // the FileInputStream will be closed too.
                } catch (IOException e) {
                    System.out.println(
                            " Failed  at closing the file !");
                }
            }
        }
    }
}