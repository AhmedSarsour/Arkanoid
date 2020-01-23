import java.util.ArrayList;
import java.util.List;

/**
 * Line.
 * @author ahmed.
 * @since 20.05.2017
 */
public class Line {
    private Point p1;
    private Point p2;
    private Velocity v;

    // constructors.
    /**
     * Line constructor.
     * @param start the point.
     * @param end the point.
     */
    public Line(Point start, Point end) {
        this.p1 = start;
        this.p2 = end;
    }

    /**
     * Line constructor.
     * @param x1 the x-axis value of start point.
     * @param y1 the y-axis value of start point.
     * @param x2 the x-axis value of end point.
     * @param y2 the y-axis value of end point.
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.p1.setX(x1);
        this.p1.setY(y1);
        this.p2.setX(x2);
        this.p2.setX(y2);
    }

    // returns the distance of the two given points.
    /**
     * length function.
     * @return distance between points.
     */
    public double length() {
        return this.p1.distance(this.p2);
    }

    // Returns the middle point of the line
    /**
     * middle function.
     * @return midP the middle point.
     */
    public Point middle() {
        double midx = (this.p1.getX() + this.p2.getX()) / 2;
        double midy = (this.p1.getY() + this.p2.getY()) / 2;
        Point midP = new Point(midx, midy);
        return midP;
    }

    // Returns the start point of the line
    /**
     * start function.
     * @return start the point
     */
    public Point start() {
        if (this.p1.getX() < this.p2.getX()) {
            return this.p1;
        } else if (this.p1.getX() == this.p2.getX()) {
            if (this.p1.getY() < this.p2.getY()) {
                return this.p1;
            }
            return this.p2;
        }
        return this.p2;
    }

    // Returns the end point of the line
    /**
     * end function.
     * @return end the point.
     */
    public Point end() {
        if (this.p1.getX() < this.p2.getX()) {
            return this.p2;
        } else if (this.p1.getX() == this.p2.getX()) {
            if (this.p1.getY() < this.p2.getY()) {
                return this.p2;
            }
            return this.p1;
        }
        return this.p1;
    }

    /*
     * the function calls the function intersectionWith which returns
     * null if the points don't intersect or the intersection point,
     * so if this function gets null then return false, true otherwise.
     */
    /**
     * isIntersecting function.
     * @param other line.
     * @return Point or null.
     */
    public boolean isIntersecting(Line other) {
        if (this.intersectionWith(other) == null) {
            return false;
        }
        return true;
    }

    // Returns the intersection point if the lines intersect,
    // and null otherwise.
    /**
     * intersectionWith function.
     * @param other the line.
     * @return p the intersection point.
     */
    public Point intersectionWith(Line other) {
        double x1 = (this.start().getX());
        double y1 = (this.start().getY());
        double x2 = (this.end().getX());
        double y2 = (this.end().getY());
        double x3 = (other.start().getX());
        double y3 = (other.start().getY());
        double x4 = (other.end().getX());
        double y4 = (other.end().getY());
        double d = (x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4);
        if (d == 0) {
            return null;
        }
        // finding the intersection point
        double xIntersect = (((x3 - x4) * (x1 * y2 - y1 * x2)
                - (x1 - x2) * (x3 * y4 - y3 * x4)) / d);
        double yIntersect = (((y3 - y4) * (x1 * y2 - y1 * x2)
                - (y1 - y2) * (x3 * y4 - y3 * x4)) / d);
        // creating a point with the intersection x and y values.
        Point p = new Point((xIntersect), (yIntersect));
        // checking if the intersection point is in the range

        if (xIntersect < Math.min(x1, x2)
                || xIntersect > Math.max(x1, x2)) {
            return null;
        }
        if (xIntersect < Math.min(x3, x4)
                || xIntersect > Math.max(x3, x4)) {
            // System.out.println("smaller than min X2");
            return null;
        }
        if (yIntersect < Math.min(y1, y2)
                || yIntersect > Math.max(y1, y2)) {
            // System.out.println("smaller than min y1");
            return null;
        }
        if (yIntersect < Math.min(y3, y4)
                || yIntersect > Math.max(y3, y4)) {
            // System.out.println("smaller than min y2");
            return null;
        }
        return p;
    }

    // return true is the lines are equal, false otherwise
    /**
     * @param other the line.
     * @return true or false.
     */
    public boolean equals(Line other) {
        if (this.start().equals(other.start())
                && this.end().equals(other.end())) {
            return true;
        }
        return false;
    }

    // If this line does not intersect with the rectangle, return null.
    // Otherwise, return the closest intersection point to the
    // start of the line.
    /**
     * closestIntersectionToStartOfLine function.
     * @param rect Rectangle.
     * @return interPoints Points
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        int counter = 0;
        // creating list of intersection Points.
        List<Point> interPoints = new ArrayList<Point>();
        // getting the intersection points.
        interPoints = rect.intersectionPoints(this);
        for (int i = 0; i < interPoints.size(); i++) {
            if (interPoints.get(i) != null) {
                // there is an intersection.
                counter++;
            }
        }
        // in case there is one intersection point.
        if (counter == 1) {
            for (int i = 0; i < interPoints.size(); i++) {
                if (interPoints.get(i) != null) {
                    // there is an intersection.
                    return interPoints.get(i);
                }
            }
        } else if (counter != 1 && counter != 0) {
            // there is more than one intersection point, mostly 2.
            // we get the one which is closer to the ball's center.
            Point point1 = new Point();
            Point point2 = new Point();
            for (int i = 0; i < interPoints.size(); i++) {
                if (interPoints.get(i) != null) {
                    point1 = interPoints.get(i);
                    break;
                }
            }
            for (int i = interPoints.size() - 1; i >= 0; i--) {
                if (interPoints.get(i) != null) {
                    point2 = interPoints.get(i);
                    break;
                }
            }
            if (this.v.getDx() > 0) {
                if (point1.getX() < point2.getX()) {
                    return point1;
                }
                return point2;
            } else {
                if (point1.getX() < point2.getX()) {
                    return point2;
                }
                return point1;
            }
        }
        // there is no intersection.
        return null;
    }

    // gets the ball velocity for the line in order to determine the beginning
    // and ending of the line.
    /**
     * @param ballv the velocity.
     */
    public void setVelocity(Velocity ballv) {
        this.v = ballv;
    }
}