import biuoop.DrawSurface;

/**
 * Paddle.
 * @author ahmed.
 *         since 20.05.2017.
 */
public class Paddle implements Sprite, Collidable {
    private double xLeft, xRight, yUp, yDown;
    private double padWidth, padHeight;
    private double stepLength;
    private java.awt.Color paddleColor;

    /**
     * Paddle Constructor.
     * @param rect the Rectangle shape.
     * @param color of the paddle.
     * @param step of the paddle.
     */
    public Paddle(Rectangle rect, java.awt.Color color, double step) {
        this.stepLength = step;
        this.paddleColor = color;
        this.xLeft = rect.getUpperLeft().getX();
        this.xRight = rect.getUpperLeft().getX() + rect.getWidth();
        this.yUp = rect.getUpperLeft().getY();
        this.yDown = rect.getUpperLeft().getY() + rect.getHeight();
        this.padWidth = rect.getWidth();
        this.padHeight = rect.getHeight();
    }

    // getting the upper side of the paddle.
    /**
     * getuSide function.
     * @return uSide of paddle rectangle.
     */
    public double getuSide() {
        double uSide = this.getCollisionRectangle().getUpperLeft()
                .getY();
        return uSide;
    }

    // getting the down side of the paddle.
    /**
     * getdSide function.
     * @return dSide of paddle rectangle.
     */
    public double getdSide() {
        double dSide = this.getCollisionRectangle().getUpperLeft()
                .getY() + this.getCollisionRectangle().getHeight();
        return dSide;
    }

    // getting the left side of the paddle.
    /**
     * getlSide function.
     * @return lSide of paddle rectangle.
     */
    public double getlSide() {
        double lSide = this.getCollisionRectangle().getUpperLeft()
                .getX();
        return lSide;
    }

    // getting the right side of the paddle.
    /**
     * getrSide function.
     * @return rSide of paddle rectangle.
     */
    public double getrSide() {
        double rSide = this.getCollisionRectangle().getUpperLeft()
                .getX() + this.getCollisionRectangle().getWidth();
        return rSide;
    }

    // getting the x-axis point of the upper left point.
    /**
     * getxLeft function.
     * @return xLeft of the paddle.
     */
    public double getxLeft() {
        return this.xLeft;
    }

    // getting the upper y-axis point of the paddle.
    /**
     * getyUp function.
     * @return yUp of the paddle.
     */
    public double getyUp() {
        return this.yUp;
    }

    // getting the paddle's width.
    /**
     * getpadWidth function.
     * @return padWidth the width of paddle.
     */
    public double getpadWidth() {
        return this.padWidth;
    }

    // getting the step length of the paddle.
    /**
     * getstepLength function.
     * @return stepLength of paddle.
     */
    public double getstepLength() {
        return this.stepLength;
    }

    // moving the paddle to the left side.
    /**
     * moveLeft function.
     */
    public void moveLeft() {
        this.xLeft = this.xLeft - this.getstepLength();
        this.xRight = this.xRight - this.getstepLength();
        if (this.xLeft <= 25) {
            this.xLeft = 25;
            this.xRight = this.xLeft + this.padWidth;
        }
    }

    // moving the paddle to the right side.
    /**
     * moveRight function.
     */
    public void moveRight() {
        this.xLeft = this.xLeft + this.getstepLength();
        this.xRight = this.xRight + this.getstepLength();
        if (this.xLeft >= 800 - 25 - this.padWidth) {
            this.xLeft = 800 - 25 - this.padWidth;
            this.xRight = this.xLeft + this.padWidth;
        }
    }

    @Override
    public void timePassed() {
        // this.timePassed();
    }

    // drawing the paddle.
    /**
     * drawOn function.
     * @param d the drawSurface.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(this.paddleColor);
        d.fillRectangle((int) this.getxLeft(), (int) this.getyUp(),
                (int) this.padWidth, (int) this.padHeight);
        d.setColor(java.awt.Color.BLACK);
        d.drawRectangle((int) this.getxLeft(), (int) this.getyUp(),
                (int) this.padWidth, (int) this.padHeight);
    }

    // returns the rectangular shape of the paddle.
    /**
     * GetCollisionRectangle function.
     * @return Rectangle shape of the paddle.
     */
    public Rectangle getCollisionRectangle() {
        return new Rectangle(
                new Point(this.getxLeft(), this.getyUp()),
                this.padWidth, this.padHeight);
    }

    // returns the new velocity to the ball after colliding with the paddle.
    /**
     * hit function.
     * @param collisionPoint the point.
     * @param currentVelocity the velocity of ball.
     * @param hitter the ball.
     * @return velocity after collision.
     */
    public Velocity hit(Ball hitter, Point collisionPoint,
            Velocity currentVelocity) {
        if (collisionPoint != null) {
            // getting the borders of the collision object.
            double uSide = this.getCollisionRectangle().getUpperLeft()
                    .getY();
            double dSide = this.getCollisionRectangle().getUpperLeft()
                    .getY()
                    + this.getCollisionRectangle().getHeight();
            double lSide = this.getCollisionRectangle().getUpperLeft()
                    .getX();
            double rSide = this.getCollisionRectangle().getUpperLeft()
                    .getX() + this.getCollisionRectangle().getWidth();

            if (collisionPoint.getX() == lSide
                    || collisionPoint.getX() == rSide
                    || collisionPoint.getY() == uSide
                    || collisionPoint.getY() == dSide) {
                // dividing the paddle into 5 parts.
                double parts = this.padWidth / 5;
                if (uSide == collisionPoint.getY()
                        || dSide == collisionPoint.getY()) {
                    currentVelocity
                            .setDy((-1) * currentVelocity.getDy());
                }
                // in case the ball hit the left or right sides of the paddle
                if (lSide == collisionPoint.getX()
                        || rSide == collisionPoint.getX()) {
                    currentVelocity
                            .setDx((-1) * currentVelocity.getDx());
                } else if (hitter.getX() >= this.getxLeft()
                        && hitter.getX() < this.getxLeft() + parts) {
                    // in case it ball hit the deeply-left side of the paddle.
                    currentVelocity.setAngleVelocity(-60,
                            currentVelocity.getDx());
                } else if (hitter.getX() >= this.getxLeft() + parts
                        && hitter.getX() < this.getxLeft()
                                + 2 * parts) {
                    // in case the ball hit the lightly-left side of the paddle.
                    currentVelocity.setAngleVelocity(-30,
                            currentVelocity.getDx());
                } else if (hitter.getX() >= this.getxLeft()
                        + 3 * parts
                        && hitter.getX() < this.getxLeft()
                                + 4 * parts) {
                    // in case the ball hit the paddle in the light-right side
                    // of the paddle.
                    currentVelocity.setAngleVelocity(30,
                            currentVelocity.getDx());
                } else if (hitter.getX() >= this.getxLeft()
                        + 4 * parts
                        && hitter.getX() <= this.getxLeft()
                                + 5 * parts) {
                    // in case the ball hit the paddle in the deeply-right side
                    // of the paddle.
                    currentVelocity.setAngleVelocity(60,
                            currentVelocity.getDx());
                }
                /*
                 * if (lSide == collisionPoint.getX()
                 * || rSide == collisionPoint.getX()) {
                 * if (hitter.getX() >= this.getxLeft() && hitter.getX() <
                 * this.getxLeft() + parts) {
                 * currentVelocity = Velocity.fromAngleAndSpeed(300,
                 * currentVelocity.getDx());
                 * } else if (hitter.getX() >= this.getxLeft() + parts &&
                 * hitter.getX() < this.getxLeft() + 2 * parts) {
                 * currentVelocity = Velocity.fromAngleAndSpeed(330,
                 * currentVelocity.getDx());
                 * } else if (hitter.getX() >= this.getxLeft() + 3 * parts &&
                 * hitter.getX() < this.getxLeft() + 4 * parts) {
                 * currentVelocity = Velocity.fromAngleAndSpeed(30,
                 * currentVelocity.getDx());
                 * } else if (hitter.getX() >= this.getxLeft() + 4 * parts &&
                 * hitter.getX() <= this.getxLeft() + 5 * parts) {
                 * currentVelocity = Velocity.fromAngleAndSpeed(60,
                 * currentVelocity.getDx());
                 * }
                 * }
                 */
                return new Velocity(currentVelocity.getDx(),
                        currentVelocity.getDy());
            }
        }
        return currentVelocity;
    }

    // Add this paddle to the game.
    /**
     * addToGame function.
     * @param g the game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}