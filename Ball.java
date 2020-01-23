import biuoop.DrawSurface;

/**
 * Ball.
 * @author ahmed.
 * @since 20.05.2017
 */
public class Ball implements Sprite {
    private Point center;
    private int radius;
    private java.awt.Color colors;
    private DrawSurface d;
    private Velocity v1;
    private int frameXp1;
    private int frameYp1;
    private int frameXp2;
    private int frameYp2;
    private CollisionInfo collObjnP;

    // constructors.
    /**
     * Ball constructor.
     * @param center the point.
     * @param r the radius.
     * @param color of the Ball.
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.radius = r;
        this.colors = color;
    }

    // accessors
    /**
     * getX.
     * @return getX the x-axis value of point.
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * getY.
     * @return getY the x-axis value of point.
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * getSize.
     * @return size the size of the Ball.
     */
    public int getSize() {
        return (int) this.radius;
    }

    /**
     * getColor function.
     * @return color of the ball.
     */
    public java.awt.Color getColor() {
        return this.colors;
    }

    // draw the ball on the given DrawSurface
    /**
     * drawOn.
     * @param surface the frame's surface.
     */
    public void drawOn(DrawSurface surface) {
        this.d = surface;
        this.d.setColor(this.getColor());
        this.d.fillCircle(this.getX(), this.getY(), this.radius);
        this.d.setColor(java.awt.Color.BLACK);
        this.d.drawCircle(this.getX(), this.getY(), this.radius);
    }

    // setting the velocity.
    /**
     * setVelocity.
     * @param v the velocity.
     */
    public void setVelocity(Velocity v) {
        this.v1 = v;
    }

    // adding velocity to the ball.
    /**
     * setVelocity function.
     * @param dx a x-axis value.
     * @param dy a y-axis value.
     */
    public void setVelocity(double dx, double dy) {
        this.v1 = new Velocity(dx, dy);
    }

    // getting the velocity.
    /**
     * getVelocity function.
     * @return v1 the velocity.
     */
    public Velocity getVelocity() {
        return this.v1;
    }

    // gets the line in which the ball would have moved on without colliding
    // with anything.
    /**
     * @return Line.
     */
    public Line trajectory() {
        return new Line(this.center,
                new Point(this.center.getX() + this.v1.getDx(),
                        this.center.getY() + this.v1.getDy()));
    }

    // get the information about the collision object and intersection point.
    /**
     * collisionObjnP function.
     * @param collObjAndP collision information.
     * @return collobjnp collision information.
     */
    public CollisionInfo collisionObjnP(CollisionInfo collObjAndP) {
        this.collObjnP = collObjAndP;
        return this.collObjnP;
    }

    /**
     * this function receives the border points of frames or rectangles.
     */
    /**
     * getFrameSize function.
     * @param frameX1 the first point x-axis.
     * @param frameX2 the second point x-axis.
     * @param frameY1 the first point y-axis.
     * @param frameY2 the second point y-axis.
     */
    public void getFrameSize(int frameX1, int frameY1, int frameX2,
            int frameY2) {
        this.frameXp1 = frameX1;
        this.frameYp1 = frameY1;
        this.frameXp2 = frameX2;
        this.frameYp2 = frameY2;
    }

    /*
     * adding the velocity to the ball in order
     * to change the x and y values of the center of ball
     * in order to make it move a step.
     */
    /**
     * moveOneStep.
     */
    public void moveOneStep() {
        /*
         * if the ball reaches the border point of x-axis then in order to
         * make the ball go back we make the velocity get a minus value.
         */
        if (this.center.getX() + this.radius > this.frameXp2
                || this.center.getX() - this.radius < frameXp1) {
            this.setVelocity(this.v1.getDx() * (-1), this.v1.getDy());
        }
        /*
         * if the ball reaches the border point of y-axis then in order to
         * make the ball go back we make the velocity get a minus value.
         */
        if (// this.center.getY() + this.radius > this.frameYp2
        this.center.getY() - this.radius < frameYp1) {
            this.setVelocity(this.v1.getDx(), this.v1.getDy() * (-1));
        }
        if (this.collObjnP != null) {
            // there is information about a collision.
            this.collObjnP.collisionObject().hit(this,
                    this.collObjnP.collisionPoint(), this.v1);
        }
        // applying the Velocity.
        this.center = this.getVelocity().applyToPoint(this.center);
    }

    @Override
    public void timePassed() {
    }

    // adds the ball to the game by adding it to the spritecollection..
    /**
     * addToGame function.
     * @param g the game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    // removes the ball from the game by removing it from the spritecollection..
    /**
     * addToGame function.
     * @param g the game.
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
    }
}
