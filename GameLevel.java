import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;

/**
 * GameLevel.
 * @author ahmed.
 * @since 20.05.2017.
 */
public class GameLevel implements Animation {
    private AnimationRunner runner;
    private boolean running;
    private SpriteCollection sprites;
    private List<Collidable> allCollidables;
    private Counter blocksCount;
    private BlockRemover blockRemove;
    private BallRemover ballRemove;
    private Counter availableBalls;
    private int paddleSpot;
    private int lives;
    private int ballsSize;
    private int remainedBlocksNumberToRemoveToWin;
    private List<Counter> ballSpot;
    private BallGenerator ballGenerate;
    private ScoreTrackingListener score;
    private ScoreIndicator scoreIndication;
    private Collidable pad;
    private GUI gui;
    private KeyboardSensor keyboard;
    private LevelInformation whichLv;
    private String levelname;

    // GameLevel constructor, getting Information about a level, the animation
    // runner, the lives number, the score number, and the gui.
    /**
     * @param theLevel information.
     * @param run the animationRunner.
     * @param theLives number.
     * @param scorePoints the value.
     * @param theGui which we work on.
     */
    public GameLevel(LevelInformation theLevel, AnimationRunner run,
            int theLives, ScoreTrackingListener scorePoints,
            GUI theGui) {
        this.lives = theLives;
        this.whichLv = theLevel;
        this.runner = run;
        this.score = scorePoints;
        this.gui = theGui;
        // getting the name of the level in order to display it at top-right
        // corner of the screen.
        this.levelname = theLevel.levelName();
    }

    // this function adds a collidable object to the list of all collidables.
    /**
     * addCollidable function.
     * @param c the Collidable object.
     */
    public void addCollidable(Collidable c) {
        // collidables are paddle, frameBorders and Blocks.
        this.allCollidables.add(c);
    }

    /*
     * this function adds a Sprite to the Sprites list.
     */
    /**
     * addSprite function.
     * @param s the Sprite
     */
    public void addSprite(Sprite s) {
        // ball is a sprite, and block is also a sprite.
        this.sprites.addSprite(s);
    }

    // Initialize a new game: create the Blocks and add them to the game.
    /**
     * initialize function.
     */
    public void initialize() {
        // getting the must-destroy-blocks-number in order to finish the level.
        this.remainedBlocksNumberToRemoveToWin = this.whichLv
                .numberOfBlocksToRemove();
        // counting the blocks of the level,setting it from 0.
        this.blocksCount = new Counter(0);
        // initial number of balls is 0.
        this.availableBalls = new Counter(0);
        // creating a blockremover.
        this.blockRemove = new BlockRemover(this, new Counter(0));
        // creating a ballremover.
        this.ballRemove = new BallRemover(this, new Counter(0));
        // creating a ballgenerator.
        this.ballGenerate = new BallGenerator(this, new Counter(0));
        this.keyboard = this.gui.getKeyboardSensor();
        // creating the sprites list.
        this.sprites = new SpriteCollection();
        // creating the collidables list.
        this.allCollidables = new ArrayList<>();
        int frameX2 = 800;
        int frameY2 = 600;
        // creating the deathBlock, when the ball falls down the borders of the
        // surface it dies.
        Collidable deathBlock = new DeathRegionBlock(
                new Rectangle(new Point(-200, 600), 1200, 200),
                java.awt.Color.black, 1, this.ballRemove);
        // adding it to the game and collidable list.
        this.allCollidables.add((Collidable) deathBlock);
        ((Block) deathBlock).addToGame(this);
        Collidable death2Block = new DeathRegionBlock(
                new Rectangle(new Point(-1000, 800), 2200, 200),
                java.awt.Color.black, 1, this.ballRemove);
        // adding it to the game and collidable list.
        this.allCollidables.add((Collidable) death2Block);
        ((Block) death2Block).addToGame(this);
        // creating a killerBlock in the level.
        Collidable killerBlock = new DeathRegionBlock(new Rectangle(
                new Point(frameX2 * 3 / 4, frameY2 * 2 / 3), 80, 20),
                java.awt.Color.red, 1, this.ballRemove);
        // adding it to the game and collidable list.
        this.allCollidables.add((Collidable) killerBlock);
        ((Block) killerBlock).addToGame(this);

        // creating a generator block that produces balls.
        Collidable generatorBlock = new BlockGenerator(
                new Rectangle(new Point(frameX2 / 4, frameY2 * 2 / 3),
                        80, 20),
                java.awt.Color.ORANGE, 1, this.ballGenerate);
        // adding the generator block to game and collidable list.
        this.allCollidables.add((Collidable) generatorBlock);
        ((Block) generatorBlock).addToGame(this);

        // creating a scoreindicator sprite.
        Sprite scoreIndicate = new ScoreIndicator(
                new Rectangle(new Point(0, 0), 800, 20),
                java.awt.Color.LIGHT_GRAY, -1, this.score);
        // adding it to the game.
        ((Block) scoreIndicate).addToGame(this);
        this.scoreIndication = (ScoreIndicator) scoreIndicate;
        // setting the score.
        this.scoreIndication.setTheGame(this);

        // creating a livesindicator sprite.
        Sprite livesIndicate = new LivesIndicator(
                new Rectangle(new Point(200, 0), 60, 20),
                java.awt.Color.LIGHT_GRAY, -1);
        // adding it to the game.
        ((Block) livesIndicate).addToGame(this);
        // setting the lives count.
        ((LivesIndicator) livesIndicate).setTheGame(this);

        // creating levelName sprite.
        Sprite thelevelName = new LevelNameBlock(
                new Rectangle(new Point(600, 0), 60, 20),
                java.awt.Color.LIGHT_GRAY, -1);
        // adding it to the game.
        ((Block) thelevelName).addToGame(this);
        // setting the name of the level.
        ((LevelNameBlock) thelevelName).setTheGame(this);
        // creating the blocks which are collidable.
        if (this.whichLv.levelName().equals("Direct Hit")) {
            for (int i = 0; i < this.whichLv.blocks().size(); i++) {
                Block b = this.whichLv.blocks().get(i);
                Block theBlock = new Block(b.getCollisionRectangle(),
                        b.getColor(), b.getHitPoints(),
                        this.blockRemove);
                this.allCollidables.add((Collidable) theBlock);
                // adding the blocks to the game.
                theBlock.addToGame(this);
                // increasing the counter of the blocks.
                this.blocksCount.increase(1);
            }
            // getting the number of the balls of the level.
            this.ballsSize = this.whichLv.numberOfBalls();
        } else if (this.whichLv.levelName().equals("Wide Easy")) {
            for (int i = 0; i < this.whichLv.blocks().size(); i++) {
                Block b = this.whichLv.blocks().get(i);
                Block theBlock = new Block(b.getCollisionRectangle(),
                        b.getColor(), b.getHitPoints(),
                        this.blockRemove);
                this.allCollidables.add((Collidable) theBlock);
                // adding the blocks to the game.
                theBlock.addToGame(this);
                this.blocksCount.increase(1);
            }
            this.ballsSize = this.whichLv.numberOfBalls();
        } else if (this.whichLv.levelName().equals("Green 3")) {
            // creating the blocks and adding them to the game.
            for (int i = 0; i < this.whichLv.blocks().size(); i++) {
                Block b = this.whichLv.blocks().get(i);
                Block theBlock = new Block(b.getCollisionRectangle(),
                        b.getColor(), b.getHitPoints(),
                        this.blockRemove);
                this.allCollidables.add((Collidable) theBlock);
                // adding the blocks to the game.
                theBlock.addToGame(this);
                this.blocksCount.increase(1);
            }

            this.ballsSize = this.whichLv.numberOfBalls();

        } else if (this.whichLv.levelName().equals("Final Four")) {
            for (int i = 0; i < this.whichLv.blocks().size(); i++) {
                Block b = this.whichLv.blocks().get(i);
                Block theBlock = new Block(b.getCollisionRectangle(),
                        b.getColor(), b.getHitPoints(),
                        this.blockRemove);
                this.allCollidables.add((Collidable) theBlock);
                // adding the blocks to the game.
                theBlock.addToGame(this);
                this.blocksCount.increase(1);
            }
            this.ballsSize = this.whichLv.numberOfBalls();
        }
    }

    // Run the game -- start the animation loop.
    /**
     * run function.
     */
    public void run() {
        while (this.lives >= 0
                && this.getRemainedBlocksNumberToRemoveToWin() > 0) {
            this.playOneTurn();
        }
    }

    // playing a one turn, which is one live.
    /**
     * playOneTurn function.
     */
    public void playOneTurn() {
        this.createBallsOnTopOfPaddle();
        this.running = true;
        this.runner = new AnimationRunner(this.gui);
        CountdownAnimation countingdown = new CountdownAnimation(6.0,
                3, this.sprites);
        countingdown.getTheGameScreen(this, this.gui);
        DrawSurface d = this.gui.getDrawSurface();
        if (this.lives > 0) {
            // displaying the countdown before the beginning of the level.
            if (!countingdown.shouldStop()) {
                countingdown.doOneFrame(d, 1 / 60);
            }
        }
        this.runner.run(this);
    }

    // draws the background and borders of the game.
    /**
     * drawBorders.
     * @param dr the drawsurface.
     */
    public void drawBorders(DrawSurface dr) {
        // drawing the borders and the background.
        java.awt.Color color = ((Block) this.whichLv.getBackground())
                .getColor();
        dr.setColor(color);
        dr.fillRectangle(0, 0, 780, 780);
        dr.drawRectangle(780, 20, 20, 600);

        // the right border.
        dr.setColor(java.awt.Color.GRAY);
        dr.fillRectangle(775, 25, 25, 600);
        dr.setColor(java.awt.Color.BLACK);
        // the left side border.
        dr.setColor(java.awt.Color.GRAY);
        dr.fillRectangle(0, 25, 25, 600);
        dr.setColor(java.awt.Color.BLACK);
        // the upper side border.
        dr.setColor(java.awt.Color.GRAY);
        dr.fillRectangle(0, 20, 800, 25);
        dr.setColor(java.awt.Color.BLACK);
    }

    // creates the paddle and the balls of the currently played level.
    /**
     * createBallsOnTopOfPaddle function.
     */
    public void createBallsOnTopOfPaddle() {
        int frameX1 = 0;
        int frameY1 = 0;
        int frameX2 = 800;
        int frameY2 = 600;
        // creating the paddle.
        Collidable padd = this.createPaddle();
        this.pad = padd;
        // adding the paddle to the collidable list.
        this.allCollidables.add(padd);
        this.paddleSpot = this.sprites.getSize() - 1;
        this.ballSpot = new ArrayList<Counter>();
        // creating the balls.
        int radius = 4;
        Sprite[] balls = new Ball[this.whichLv.numberOfBalls()];
        for (int l = 0; l < this.whichLv.numberOfBalls(); l++) {
            balls[l] = new Ball(new Point(frameX2 / 2, frameY2 - 80),
                    radius, java.awt.Color.WHITE);
            ((Ball) balls[l]).getFrameSize(frameX1 + 25, frameY1 + 45,
                    frameX2 - 25, frameY2 - 25);
            // getting a velocity.
            Velocity v2 = this.whichLv.initialBallVelocities().get(l);
            // adding the velocity to the ball.
            ((Ball) balls[l]).setVelocity(v2);
            // adding the created ball to the game.
            ((Ball) balls[l]).addToGame(this);
            // setting the ballspot in the sprite collection in the ballspot
            // list.
            this.ballSpot
                    .add(new Counter(this.sprites.getSize() - 1));
            // increasing the number of available balls.
            this.availableBalls.increase(1);
        }
    }

    // creating the gameEnvironMent and moving the balls.
    /**
     * makeGameEnvironmentAndMoveTheBalls function.
     */
    public void makeGameEnvironmentAndMoveTheBalls() {
        // creating the GameEnvironment.
        GameEnvironment collidables = new GameEnvironment(
                this.allCollidables);
        for (int i = 0; i < this.ballSpot.size(); i++) {
            // getting the first ball's velocity for the GameEnvinronment.
            collidables.getVelocity(((Ball) this.sprites
                    .getSprite(this.ballSpot.get(i).getValue()))
                            .getVelocity());
            // getting the collidable object of the first ball.
            ((Ball) this.sprites.getSprite(this.ballSpot.get(i)
                    .getValue())).collisionObjnP(collidables
                            .getClosestCollision(((Ball) this.sprites
                                    .getSprite(this.ballSpot.get(i)
                                            .getValue()))
                                                    .trajectory()));
            // moving the ball.
            ((Ball) this.sprites
                    .getSprite(this.ballSpot.get(i).getValue()))
                            .moveOneStep();
        }
    }

    // moving the paddle left or right.
    /**
     * movePaddle function.
     */
    public void movePaddle() {
        // checking which key is being pressed for the paddle.
        if (this.keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            if (((Paddle) this.sprites.getSprite(this.paddleSpot))
                    .getxLeft() <= 740
                    && ((Paddle) this.sprites
                            .getSprite(this.paddleSpot))
                                    .getxLeft() >= 0) {
                // moving the paddle to the left side.
                ((Paddle) this.sprites.getSprite(this.paddleSpot))
                        .moveLeft();
            }
        }
        if (this.keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            if (((Paddle) this.sprites.getSprite(this.paddleSpot))
                    .getxLeft() <= 740
                    && ((Paddle) this.sprites
                            .getSprite(this.paddleSpot))
                                    .getxLeft() >= 20) {
                // moving the paddle to the right side.
                ((Paddle) this.sprites.getSprite(this.paddleSpot))
                        .moveRight();
            }
        }
    }

    // removes the given collidable from the collidables'list.
    /**
     * removeCollidable function.
     * @param c the collidable.
     */
    public void removeCollidable(Collidable c) {
        List<Collidable> removedCollidableList = new ArrayList<Collidable>(
                this.allCollidables);
        removedCollidableList.remove(c);
        this.allCollidables = removedCollidableList;
    }

    // removes a given sprite from the sprites collection.
    /**
     * removeSprite function.
     * @param s the sprite.
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    // gets the number of the remaining blocks in the game.
    /**
     * @return blocksCount the yet-Alive-blocks.
     */
    public Counter getRemainedBlocksNum() {
        return this.blocksCount;
    }

    // sets an adjusted number of blocks after removing one block out of the
    // game.
    /**
     * @param mustRemove number of blocks.
     */
    public void setRemainedBlocksNumberToRemoveToWin(int mustRemove) {
        this.remainedBlocksNumberToRemoveToWin = mustRemove;
    }

    // gets the number of the remaining blocks that must be destroyed in order
    // to win the level.
    /**
     * @return remainedBlocksNumberToRemoveToWin.
     */
    public int getRemainedBlocksNumberToRemoveToWin() {
        return this.remainedBlocksNumberToRemoveToWin;
    }

    // getting the number of remaining balls in the level.
    /**
     * @return availableBalls.
     */
    public Counter getRemainedBallsNum() {
        return this.availableBalls;
    }

    // getting the list of the spritescollection.
    /**
     * @return sprites the collection.
     */
    public SpriteCollection getSprites() {
        return this.sprites;
    }

    // getting the size of the list of the spritescollection.
    /**
     * @return spritesSize the number.
     */
    public int getSpritesSize() {
        return this.sprites.getSize();
    }

    // getting the ball which exists in the given index of the ballsSpot list.
    /**
     * @param i the index.
     * @return ballSpot which is in that index.
     */
    public int getBallSpot(int i) {
        return this.ballSpot.get(i).getValue();
    }

    // this function adjusts the balls spots(after removing a block or a ball or
    // so), it also adjusts the spot of the paddle in the sprite collection.
    /**
     * setBallsSpot.
     */
    public void setBallsSpot() {
        for (int j = 0; j < this.ballSpot.size(); j++) {
            this.ballSpot.get(j).decrease(1);
        }
        this.paddleSpot -= 1;
    }

    // sets a new list of the balls after removing a ball.
    /**
     * @param item of the ballsList.
     */
    public void setNewBallsList(int item) {
        List<Counter> newBallsList = new ArrayList<Counter>(
                this.ballSpot);
        newBallsList.remove(item);
        this.ballSpot = newBallsList;
    }

    // using it in ballGenerator.
    // sets a new ball list after adding a new ball.
    /**
     * setNewAddBallList function.
     */
    public void setNewAddBallList() {
        List<Counter> newBallsList = new ArrayList<Counter>(
                this.ballSpot);
        newBallsList.add(new Counter(
                this.getBallSpot(this.ballSpot.size() - 1) + 1));
        this.ballSpot = newBallsList;
    }

    // used in ball generator.
    // gets the ballSpot list.
    /**
     * @return ballSpot counter List.
     */
    public List<Counter> getBallSpot() {
        return this.ballSpot;
    }

    // getting the scoreTrackingListener of the game.
    /**
     * @return score of the game.
     */
    public ScoreTrackingListener getScore() {
        return this.score;
    }

    // getting the scoreIndicator of the game.
    /**
     * @return scoreIndication the scoreindicator.
     */
    public ScoreIndicator getScoreIndicator() {
        return this.scoreIndication;
    }

    // returns the number of the lives in the game.
    /**
     * @return lives of the game.
     */
    public int getLives() {
        return this.lives;
    }

    // modifies the number of the lives after losing a life or so.
    /**
     * @param modifyLives the new lives number.
     */
    public void setLives(int modifyLives) {
        this.lives = modifyLives;
    }

    // creates a new collidable paddle that is initialized at the middle of the
    // GUI.
    /**
     * @return pad the collidable paddle.
     */
    public Collidable createPaddle() {
        int frameX2 = 800;
        int frameY2 = 600;
        double padWidth = this.whichLv.paddleWidth();
        double padHeight = 15;
        double paddleStepLength = this.whichLv.paddleSpeed();
        // creating the paddle which is Paddle and collidable at the same time.
        Collidable paddl = new Paddle(
                new Rectangle(new Point(frameX2 / 2 - padWidth / 2,
                        frameY2 - 40), padWidth, padHeight),
                java.awt.Color.ORANGE, paddleStepLength);
        // adding the paddle to the game.
        ((Paddle) paddl).addToGame(this);
        // adding the paddle to the collidables list.
        return paddl;
    }

    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        // drawing the borders and decorations of the level.
        this.drawBorders(d);
        this.drawDecoration(d);
        // creating the game environment and moving the balls.
        this.makeGameEnvironmentAndMoveTheBalls();
        // moving the paddle.
        this.movePaddle();
        // drawing all the sprites of the game.
        this.sprites.drawAllOn(d);
        // pausing the game in case p-key was pressed.
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new PauseScreen(this.keyboard));
        }
        // ending the playoneTurn in case all the must-destroy-blocks-to-win are
        // destroyed.
        if (this.blockRemove.getRemainingBlocks() <= 0
                || this.lives == 0) {
            this.running = false;
        }
        // ending the playoneturn in case all the balls fell down.
        if (this.ballRemove.getRemainingBalls() == 0) {
            // adjusting the lives.
            this.setLives(this.getLives() - 1);
            // removing the paddle and then later creating a new one.
            this.removeCollidable(this.pad);
            this.removeSprite((Sprite) this.pad);
            this.running = false;
        }
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    // draws the decorations of the levels.
    /**
     * @param d the DrawSurface.
     */
    public void drawDecoration(DrawSurface d) {
        if (this.whichLv.levelName().equals("Wide Easy")) {
            d.setColor(java.awt.Color.LIGHT_GRAY);
            d.fillCircle(100, 100, 45);
            d.setColor(java.awt.Color.orange);
            // drawing the sun or something like this.
            d.fillCircle(100, 100, 38);
            for (int i = 0; i < 70; i++) {
                d.drawLine(100, 100, i * 10 + 25, 200);
            }
            d.setColor(java.awt.Color.YELLOW);
            d.fillCircle(100, 100, 30);
            for (int i = 0; i < 15; i++) {
                d.setColor(java.awt.Color.red);
                d.fillCircle(35, 300 + i * 20, 5);
                d.setColor(java.awt.Color.black);
                d.drawLine(25, 300 + 10 + i * 20, 35,
                        300 + i * 20 + 5);
            }
            for (int i = 0; i < 15; i++) {
                d.setColor(java.awt.Color.red);
                d.fillCircle(765, 300 + i * 20, 5);
                d.setColor(java.awt.Color.black);
                d.drawLine(765, 300 + i * 20 + 5, 775,
                        300 + i * 20 + 10);
            }
        } else if (this.whichLv.levelName().equals("Green 3")) {
            // a castle was drawn :).
            d.setColor(java.awt.Color.DARK_GRAY);
            d.fillRectangle(100, 450, 500, 600);
            d.fillRectangle(50, 520, 50, 600);
            d.fillRectangle(600, 500, 80, 600);
            d.fillRectangle(150, 350, 40, 450);
            d.fillRectangle(165, 150, 10, 350);
            d.setColor(java.awt.Color.LIGHT_GRAY);
            d.fillRectangle(170, 410, 15, 40);
            d.setColor(java.awt.Color.white);
            d.fillRectangle(155, 370, 30, 20);
            d.setColor(java.awt.Color.black);
            d.drawLine(170, 370, 170, 390);
            d.drawLine(155, 380, 185, 380);
            d.setColor(java.awt.Color.white);

            for (int i = 0; i < 19; i++) {
                d.fillRectangle(120 + i * 25, 460, 10, 20);
            }
            d.setColor(java.awt.Color.black);
            for (int i = 0; i < 10; i++) {
                d.drawLine(125 + i * 50, 460, 125 + i * 50, 480);
                d.drawLine(120 + i * 50, 470, 130 + i * 50, 470);
            }
            d.setColor(java.awt.Color.ORANGE);
            d.fillCircle(170, 150, 15);
            d.setColor(java.awt.Color.YELLOW);
            for (int i = 1; i <= 20; i++) {
                d.drawLine(170, 150, 170 + i * 3, 210 - i * 3);
            }
            for (int i = 0; i < 20; i++) {
                d.drawLine(170, 150, 230 - i * 3, 150 - i * 3);
            }
            int j = 0;
            for (int i = 20; i > 0; i--) {
                d.drawLine(170, 150, 170 - i * 3, 150 - j * 3);
                j++;
            }
            j = 0;
            for (int i = 20; i > 0; i--) {
                d.drawLine(170, 150, 170 - i * 3, 150 + j * 3);
                j++;
            }
            d.setColor(java.awt.Color.red);
            d.fillCircle(170, 150, 10);
            d.setColor(java.awt.Color.white);
            d.fillCircle(170, 150, 5);
            for (int i = 0; i < 6; i++) {
                d.setColor(java.awt.Color.LIGHT_GRAY);
                d.fillRectangle(120 + i * 100, 540, 30, 60);
                d.setColor(java.awt.Color.black);
                d.drawRectangle(120 + i * 100, 540, 30, 60);
            }
            d.setColor(java.awt.Color.black);
            d.drawLine(100, 500, 680, 500);
        } else if (this.whichLv.levelName().equals("Final Four")) {
            d.setColor(java.awt.Color.yellow);
            Random rand = new Random();
            for (int i = 0; i < 70; i++) {
                d.fillCircle(rand.nextInt(730) + 30,
                        rand.nextInt(550) + 50, 3);
            }
            d.setColor(java.awt.Color.LIGHT_GRAY);
            d.fillCircle(100, 300, 20);
            d.fillCircle(140, 320, 30);
            d.fillCircle(70, 300, 20);
            d.fillCircle(90, 310, 20);
            d.fillCircle(90, 340, 30);
            for (int i = 0; i < 12; i++) {
                d.drawLine(70 + i * 8, 320, 30 + i * 8, 600);
            }
            d.fillCircle(300, 300, 20);
            d.fillCircle(340, 320, 30);
            d.fillCircle(370, 300, 20);
            d.fillCircle(390, 310, 20);
            d.fillCircle(390, 340, 30);
            for (int i = 0; i < 15; i++) {
                d.drawLine(300 + i * 8, 320, 250 + i * 8, 600);
            }
            d.fillCircle(600, 300, 30);
            d.fillCircle(640, 320, 20);
            d.fillCircle(670, 290, 40);
            d.fillCircle(690, 310, 20);
            for (int i = 0; i < 12; i++) {
                d.drawLine(600 + i * 8, 320, 540 + i * 8, 600);
            }

        } else if (this.whichLv.levelName().equals("Direct Hit")) {
            // drawing an accuracy - shapelike draw.
            d.setColor(java.awt.Color.blue);
            d.drawCircle(402, 162, 50);
            d.drawCircle(402, 162, 80);
            d.drawCircle(402, 162, 110);
            d.drawLine(250, 162, 550, 162);
            d.drawLine(402, 45, 402, 300);
            d.setColor(java.awt.Color.red);
        }
    }

    // returns the name of the currently played level.
    /**
     * getLevelName Function.
     * @return levelname the string.
     */
    public String getLevelName() {
        return this.levelname;
    }
}