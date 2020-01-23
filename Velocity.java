//Velocity specifies the change in position on the `x` and the `y` axes.
/**
 * Velocity.
 * @author ahmed.
 * @since 20.05.2017
 */
public class Velocity {
    private double dx;
    private double dy;
    private static double initialv;

    // constructor.
    /**
     * Velocity constructor.
     * @param dx the added x-axis velocity.
     * @param dy the added y-axis velocity.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /*
     * Take a point with position (x,y) and return a
     * new point with position (x+dx, y+dy)
     */
    /**
     * @param p the point.
     * @return p the point after adding velocity.
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + this.dx, p.getY() + this.dy);
    }

    /**
     * getDx function.
     * @return the x-axis velocity value.
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * getDy.
     * @return the y-axis velocity value.
     */
    public double getDy() {
        return this.dy;
    }

    // sets a new velocity on x-axis.
    /**
     * setDx function.
     * @param speedx the value of velocity.
     */
    public void setDx(double speedx) {
        this.dx = speedx;
    }

    // sets a new velocity on y-axis.
    /**
     * setYx function.
     * @param speedy the value of velocity.
     */
    public void setDy(double speedy) {
        this.dy = speedy;
    }

    /*
     * it receives the angle and the speed of the ball
     * and then changes the direction of the ball according
     * to the given angle.
     */
    /**
     * framAngleAndSpeed function.
     * @param angle of the movement.
     * @param speed represents the velocity.
     * @return velocity with directions.
     */
    public static Velocity fromAngleAndSpeed(double angle,
            double speed) {
        double dx;
        double dy;
        initialv = speed;
        dx = Math.cos(Math.toRadians(angle) - Math.PI / 2) * speed;
        dy = Math.sin(Math.toRadians(angle) - Math.PI / 2) * speed;
        return new Velocity(dx, dy);
    }

    // sets a new velocity for the ball after a collision.
    /**
     * @param angle not in radians.
     * @param speed the velocity's value.
     */
    public void setAngleVelocity(double angle, double speed) {
        this.dx = Math.cos(Math.toRadians(angle) - Math.PI / 2)
                * initialv;
    }
}