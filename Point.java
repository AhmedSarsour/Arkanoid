/**
 * Point.
 * @author ahmed.
 * @since 20.05.2017
 */
public class Point {
    private double x;
    private double y;

    // constructor.
    /**
     * @param x the point x-axis value.
     * @param y the point y-axis value.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /*
     * constructor, if user doesn't put anything when
     * creating a new point, the new point will be at (0,0)
     */
    /**
     * constructor.
     */
    public Point() {
        this(0.0, 0.0);
    }

    // returns the distance of this point to the other point
    /**
     * distance function.
     * @param other point.
     * @return distance from two points.
     */
    public double distance(Point other) {
        double dx = this.x - other.getX();
        double dy = this.y - other.getY();
        return Math.sqrt((dx * dx) + (dy * dy));
    }

    // returns true is the points are equal, false otherwise
    /**
     * equals function.
     * @param other point.
     * @return boolean true or false.
     */
    public boolean equals(Point other) {
        if (this.x == other.getX() && this.y == other.getY()) {
            return true;
        }
        return false;
    }

    // receive the x-axis point value.
    /**
     * setX function.
     * @param xP the x-axis point value.
     */
    public void setX(double xP) {
        this.x = xP;
    }

    // receive the y-axis point value.
    /**
     * @param yP the y-axis point value.
     */
    public void setY(double yP) {
        this.y = yP;
    }

    // Returns the x value of this point
    /**
     * getX fuction.
     * @return x the value.
     */
    public double getX() {
        return this.x;
    }

    // Returns the y values of this point
    /**
     * getY function.
     * @return y the value.
     */
    public double getY() {
        return this.y;
    }

    // shows the x and y values of the point.
    /**
     * toString function.
     * @return string of the values of the point.
     */
    public String toString() {
        return "X = " + this.x + ", Y = " + this.y;
    }
}