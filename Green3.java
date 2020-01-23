import java.util.ArrayList;
import java.util.List;

// a level of the game.
/**
 * Green3.
 * @author ahmed.
 * @since 20.05.2017.
 */
public class Green3 implements LevelInformation {
    private int ballsNum;
    private int paddleWidth, paddleStepLength;
    private int numOfBlocksToRemoveToWin;
    private List<Velocity> theballsVelocities;
    private List<Block> theLevelBlocks;
    private Sprite backgroundcol;

    @Override
    public int numberOfBalls() {
        this.ballsNum = 2;
        return this.ballsNum;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        this.theballsVelocities = new ArrayList<>();
        this.theballsVelocities
                .add(Velocity.fromAngleAndSpeed(30, 3));
        this.theballsVelocities
                .add(Velocity.fromAngleAndSpeed(-30, 3));
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
        return "Green 3";
    }

    @Override
    public Sprite getBackground() {
        this.backgroundcol = new Block(
                new Rectangle(new Point(25, 25), 775, 600),
                java.awt.Color.green, 1);
        return this.backgroundcol;
    }

    @Override
    public List<Block> blocks() {
        this.theLevelBlocks = new ArrayList<Block>();
        int blockWidth = 50;
        int blockHeight = 20;
        int j = 5;
        int limit = 0;
        // creating the blocks and adding them to the game.
        while (j != 10) {
            // BLOCKREMOVER I CAN Do something like ... setBlockRemover in
            // GameLevel , and then
            // getBlockRemover and then we can use it here.
            for (int i = 14; i >= 5 + limit; i--) {
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
                                    java.awt.Color.BLUE, 1));
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
                }
            }
            j++;
            limit++;
        }
        return this.theLevelBlocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        this.numOfBlocksToRemoveToWin = 40;
        if (this.numOfBlocksToRemoveToWin > this.blocks().size()) {
            this.numOfBlocksToRemoveToWin = this.blocks().size();
        }
        return this.numOfBlocksToRemoveToWin;
    }
}
