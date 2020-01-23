package gameplaytest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import biuoop.DrawSurface;
import biuoop.GUI;

/**
 * Ass6Game.
 * @author ahmed
 *         since 20.6.2017.
 */
public class Ass6Game {
    /**
     * main function.
     * @param args user input.
     */
    public static void main(String[] args) {
        // creating an empty list of LevelInformation type.
        List<LevelInformation> levels = new ArrayList<LevelInformation>();
        // if there are arguments
        if (args.length != 0) {
            // creating a list of the level to play.
            int[] listNums = new int[args.length];
            for (int i = 0; i < args.length; i++) {
                // using try - catch method in order to throw away the string
                // arguments.
                try {
                    listNums[i] = Integer.parseInt(args[i]);
                } catch (Exception e) {
                    // throwing the string argument.
                    System.out.print(
                            "throwing the string from the args");
                }
            }
            // creating the levels and adding them to list.
            for (int i = 0; i < listNums.length; i++) {
                if (listNums[i] == 1) {
                    levels.add(new DirectHit());
                } else if (listNums[i] == 2) {
                    levels.add(new WideEasy());
                } else if (listNums[i] == 3) {
                    levels.add(new Green3());
                } else if (listNums[i] == 4) {
                    levels.add(new FinalFour());
                }
            }
            // in case there are no arguments, we play the four levels start
            // with DirectHit and ending with FinalFour.
        } else {
            levels.add(new DirectHit());
            levels.add(new WideEasy());
            levels.add(new Green3());
            levels.add(new FinalFour());
        }
        GUI gui = new GUI("Arkanoid", 800, 600);
        DrawSurface d = gui.getDrawSurface();
        AnimationRunner run = new AnimationRunner(gui);
        MenuAnimation<String> menu = new MenuAnimation<String>(
                "Menu Title", gui);
        menu.addSelection("s", "Play", "game");
        menu.addSelection("h", "High Scores", "scores");
        menu.addSelection("q", "quit", "Quit");
        // ...
        File theScoresFile = new File("scores.txt");
        HighScoresTable highScoresList = new HighScoresTable(3);
        while (true) {
            GameFlow playGame = new GameFlow(gui, run,
                    highScoresList);
            menu.setSetAndGo();
            run.run(menu);
            highScoresList = playGame.getScoresList();
            String status = menu.getStatus();
            if (status.equals("game")) {
                playGame.runLevels(levels);
                status = menu.getStatus();
            } else if (status.equals("scores")) {
                if (playGame.getScoresList().getHighScores()
                        .isEmpty()) {
                    // File theScoresFile = new File("scores.txt");
                    try {
                        playGame.getScoresList().load(theScoresFile);
                        playGame.getScoresList().save(theScoresFile);
                        HighScoresAnimation showScores = new HighScoresAnimation(
                                highScoresList, "space", gui);
                        if (!showScores.shouldStop()) {
                            showScores.doOneFrame(d, 1 / 60);
                        }
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                } else {
                    playGame.getScoresList();
                    HighScoresTable.loadFromFile(theScoresFile, 3);
                    try {
                        playGame.getScoresList().save(theScoresFile);
                        HighScoresAnimation showScores = new HighScoresAnimation(
                                highScoresList, "space", gui);
                        if (!showScores.shouldStop()) {
                            showScores.doOneFrame(d, 1 / 60);
                        }
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                // show scores
            } else if (status.equals("Quit")) {
                gui.close();
                break;
            }
            try {
                playGame.getScoresList().save(theScoresFile);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}