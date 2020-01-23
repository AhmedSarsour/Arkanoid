import java.util.ArrayList;
import java.util.List;

/**
 * WideEasy.
 * @author ahmed.
 * @since 20.05.2017.
 */
public class WideEasy implements LevelInformation {
    private int ballsNum;
    private int paddleWidth, paddleStepLength;
    private int numOfBlocksToRemoveToWin;
    private List<Velocity> theballsVelocities;
    private List<Block> theLevelBlocks;
    private Sprite backgroundcol;

    @Override
    public int numberOfBalls() {
        this.ballsNum = 10;
        return this.ballsNum;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        this.theballsVelocities = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            if (i <= 5) {
                this.theballsVelocities
                        .add(Velocity.fromAngleAndSpeed(-i * 10, 3));
            } else {
                this.theballsVelocities.add(
                        Velocity.fromAngleAndSpeed(i * 10 - 50, 3));
            }
        }
        return this.theballsVelocities;
    }

    @Override
    public int paddleSpeed() {
        this.paddleStepLength = 1;
        return this.paddleStepLength;
    }

    @Override
    public int paddleWidth() {
        this.paddleWidth = 600;
        return this.paddleWidth;
    }

    @Override
    public String levelName() {
        return "Wide Easy";
    }

    @Override
    public Sprite getBackground() {
        this.backgroundcol = new Block(
                new Rectangle(new Point(25, 25), 775, 600),
                java.awt.Color.white, 1);
        return this.backgroundcol;
    }

    @Override
    public List<Block> blocks() {
        this.theLevelBlocks = new ArrayList<Block>();
        // creating the blocks of the level.
        for (int i = 0; i < 15; i++) {
            if (i == 0 || i == 1) {
                this.theLevelBlocks.add(new Block(
                        new Rectangle(new Point(i * 50 + 25, 200), 50,
                                20),
                        java.awt.Color.red, 1));
            } else if (i == 2 || i == 3) {
                this.theLevelBlocks.add(new Block(
                        new Rectangle(new Point(i * 50 + 25, 200), 50,
                                20),
                        java.awt.Color.orange, 1));
            } else if (i == 4 || i == 5) {
                this.theLevelBlocks.add(new Block(
                        new Rectangle(new Point(i * 50 + 25, 200), 50,
                                20),
                        java.awt.Color.yellow, 1));
            } else if (i >= 6 && i <= 8) {
                this.theLevelBlocks.add(new Block(
                        new Rectangle(new Point(i * 50 + 25, 200), 50,
                                20),
                        java.awt.Color.green, 1));
            } else if (i == 9 || i == 10) {
                this.theLevelBlocks.add(new Block(
                        new Rectangle(new Point(i * 50 + 25, 200), 50,
                                20),
                        java.awt.Color.blue, 1));
            } else if (i == 11 || i == 12) {
                this.theLevelBlocks.add(new Block(
                        new Rectangle(new Point(i * 50 + 25, 200), 50,
                                20),
                        java.awt.Color.pink, 1));
            } else {
                this.theLevelBlocks.add(new Block(
                        new Rectangle(new Point(i * 50 + 25, 200), 50,
                                20),
                        java.awt.Color.cyan, 1));
            }
        }
        return this.theLevelBlocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        this.numOfBlocksToRemoveToWin = 15;
        // in case the must-remove-blocks-in-order-to-win number is bigger than
        // the size of the blocks, we set it to be equal to the number of the
        // level's blocks.
        if (this.numOfBlocksToRemoveToWin > this.blocks().size()) {
            this.numOfBlocksToRemoveToWin = this.blocks().size();
        }
        return this.numOfBlocksToRemoveToWin;
    }
}