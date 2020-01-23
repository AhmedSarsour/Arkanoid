import java.util.ArrayList;
import java.util.List;

//this is a level of the game.
/**
 * DirectHit.
 * @author ahmed.
 * @since 20.05.2017.
 */
public class DirectHit implements LevelInformation {
    private int ballsNum;
    private int paddleWidth, paddleStepLength;
    private int numOfBlocksToRemoveToWin;
    private List<Velocity> theballsVelocities;
    private List<Block> theLevelBlocks;
    private Sprite backgroundcol;

    @Override
    public int numberOfBalls() {
        this.ballsNum = 1;
        return this.ballsNum;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        this.theballsVelocities = new ArrayList<>();
        this.theballsVelocities.add(Velocity.fromAngleAndSpeed(0, 3));
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
        return "Direct Hit";
    }

    @Override
    public Sprite getBackground() {
        this.backgroundcol = new Block(
                new Rectangle(new Point(25, 25), 775, 600),
                java.awt.Color.black, 1);
        return this.backgroundcol;
    }

    @Override
    public List<Block> blocks() {
        this.theLevelBlocks = new ArrayList<Block>();
        this.theLevelBlocks.add(
                new Block(new Rectangle(new Point(390, 150), 25, 25),
                        java.awt.Color.red, 1));
        return this.theLevelBlocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        this.numOfBlocksToRemoveToWin = 1;
        if (this.numOfBlocksToRemoveToWin > this.blocks().size()) {
            this.numOfBlocksToRemoveToWin = this.blocks().size();
        }
        return this.numOfBlocksToRemoveToWin;
    }
}
