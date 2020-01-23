import java.util.ArrayList;
import java.util.List;

/**
 * FinalFour.
 * @author ahmed.
 * @since 20.05.2017.
 */

public class FinalFour implements LevelInformation {
    private int ballsNum;
    private int paddleWidth, paddleStepLength;
    private int numOfBlocksToRemoveToWin;
    private List<Velocity> theballsVelocities;
    private List<Block> theLevelBlocks;
    private Sprite backgroundcol;

    @Override
    public int numberOfBalls() {
        this.ballsNum = 3;
        return this.ballsNum;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        this.theballsVelocities = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            this.theballsVelocities
                    .add(Velocity.fromAngleAndSpeed(-30 + i * 30, 3));
        }

        return this.theballsVelocities;
    }

    @Override
    public int paddleSpeed() {
        this.paddleStepLength = 5;
        return this.paddleStepLength;
    }

    @Override
    public int paddleWidth() {
        this.paddleWidth = 80;
        return this.paddleWidth;
    }

    @Override
    public String levelName() {
        return "Final Four";
    }

    @Override
    public Sprite getBackground() {
        this.backgroundcol = new Block(
                new Rectangle(new Point(25, 25), 775, 600),
                java.awt.Color.blue, 1);
        return this.backgroundcol;
    }

    @Override
    public List<Block> blocks() {
        this.theLevelBlocks = new ArrayList<Block>();
        int blockWidth = 50;
        int blockHeight = 20;
        int j = 5;
        // creating the blocks and adding them to the game.
        while (j != 12) {
            for (int i = 14; i >= 0; i--) {
                if (j == 5) {
                    // making the blocks of the upper line.
                    this.theLevelBlocks.add(
                            new Block(
                                    new Rectangle(
                                            new Point(
                                                    i * blockWidth
                                                            + 25,
                                                    j * blockHeight),
                                            blockWidth, blockHeight),
                                    java.awt.Color.GRAY, 2));
                } else if (j == 6) {
                    this.theLevelBlocks.add(
                            new Block(
                                    new Rectangle(
                                            new Point(
                                                    i * blockWidth
                                                            + 25,
                                                    j * blockHeight),
                                            blockWidth, blockHeight),
                                    java.awt.Color.RED, 1));
                } else if (j == 7) {
                    this.theLevelBlocks.add(
                            new Block(
                                    new Rectangle(
                                            new Point(
                                                    i * blockWidth
                                                            + 25,
                                                    j * blockHeight),
                                            blockWidth, blockHeight),
                                    java.awt.Color.YELLOW, 1));
                } else if (j == 8) {
                    this.theLevelBlocks.add(
                            new Block(
                                    new Rectangle(
                                            new Point(
                                                    i * blockWidth
                                                            + 25,
                                                    j * blockHeight),
                                            blockWidth, blockHeight),
                                    java.awt.Color.GREEN, 1));
                } else if (j == 9) {
                    this.theLevelBlocks.add(
                            new Block(
                                    new Rectangle(
                                            new Point(
                                                    i * blockWidth
                                                            + 25,
                                                    j * blockHeight),
                                            blockWidth, blockHeight),
                                    java.awt.Color.WHITE, 1));
                } else if (j == 10) {
                    this.theLevelBlocks.add(
                            new Block(
                                    new Rectangle(
                                            new Point(
                                                    i * blockWidth
                                                            + 25,
                                                    j * blockHeight),
                                            blockWidth, blockHeight),
                                    java.awt.Color.pink, 1));
                } else if (j == 11) {
                    this.theLevelBlocks.add(
                            new Block(
                                    new Rectangle(
                                            new Point(
                                                    i * blockWidth
                                                            + 25,
                                                    j * blockHeight),
                                            blockWidth, blockHeight),
                                    java.awt.Color.CYAN, 1));
                }
            }
            j++;
        }
        return this.theLevelBlocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        this.numOfBlocksToRemoveToWin = this.blocks().size(); // == 105.
        // in case the must-destroy-blocks amount is bigger than of that of the
        // blocks of the level,
        // setting the must-destroy-blocks to be the number of all the blocks of
        // the level.
        if (this.numOfBlocksToRemoveToWin > this.blocks().size()) {
            this.numOfBlocksToRemoveToWin = this.blocks().size();
        }
        return this.numOfBlocksToRemoveToWin;
    }
}