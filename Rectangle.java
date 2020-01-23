import java.util.ArrayList;
import java.util.List;

/**
 * Rectangle.
 * @author ahmed.
 * @since 20.05.2017.
 */
public class Rectangle {
    private Point upLeft;
    private double wid;
    private double hght;
    private double dSide;
    private double uSide;
    private double lSide;
    private double rSide;

    // Constructor, Create a new rectangle with location and width/height.
    /**
     * Rectangle constructor.
     * @param upperLeft Point.
     * @param width of Rectangle.
     * @param height of Rectangle.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upLeft = upperLeft;
        this.wid = width;
        this.hght = height;
    }

    // Return a (possibly empty) List of intersection points.
    // with the specified line.
    /**
     * intersectionPoints function.
     * @param trajectory line.
     * @return list of intersection Points.
     */
    public List<Point> intersectionPoints(Line trajectory) {
        List<Point> interPoints = new ArrayList<Point>();
        // right x-axis point of the rectangle.
        double rectRPx = this.getUpperLeft().getX() + this.getWidth();
        // down y-axis point of the rectangle.
        double rectDPy = this.getUpperLeft().getY()
                + this.getHeight();
        // the intersection of rectangle points.
        Point check1 = new Point();
        Point check2 = new Point();
        Point check3 = new Point();
        Point check4 = new Point();
        // checking the intersection between each side of the rectange with the
        // line.
        check1 = trajectory.intersectionWith(new Line(
                new Point(rectRPx, rectDPy),
                new Point(this.getUpperLeft().getX(), rectDPy)));
        check2 = trajectory.intersectionWith(new Line(
                new Point(this.getUpperLeft().getX(),
                        this.getUpperLeft().getY()),
                new Point(this.getUpperLeft().getX(), rectDPy)));
        check3 = trajectory.intersectionWith(new Line(
                new Point(rectRPx, this.getUpperLeft().getY()),
                new Point(rectRPx, rectDPy)));
        check4 = trajectory.intersectionWith(new Line(
                new Point(this.getUpperLeft().getX(),
                        this.getUpperLeft().getY()),
                new Point(rectRPx, this.getUpperLeft().getY())));
        // saving the points in a list of points.
        interPoints.add(check1);
        interPoints.add(check2);
        interPoints.add(check3);
        interPoints.add(check4);
        // returning the list of the points.
        return interPoints;
    }

    // Return the width of the rectangle.
    /**
     * getWidth function.
     * @return width double.
     */
    public double getWidth() {
        return this.wid;
    }

    // Return the Height of the rectangle.
    /**
     * getHeight function.
     * @return width double.
     */
    public double getHeight() {
        return this.hght;
    }

    // Returns the upper-left point of the rectangle.
    /**
     * getUpperLeft function.
     * @return UpLeft Point.
     */
    public Point getUpperLeft() {
        return this.upLeft;
    }

    // Return the down side of the rectangle.
    /**
     * getWidth function.
     * @return dSide double.
     */
    public double getdSide() {
        this.dSide = this.getUpperLeft().getY() + this.getHeight();
        return this.dSide;
    }

    // Return the upper side of the rectangle.
    /**
     * getuSide function.
     * @return uSide double.
     */
    public double getuSide() {
        this.uSide = this.getUpperLeft().getY();
        return this.uSide;
    }

    // Return the left side of the rectangle.
    /**
     * getlSide function.
     * @return lSide double.
     */
    public double getlSide() {
        this.lSide = this.getUpperLeft().getX();
        return this.lSide;
    }

    // Return the right side of the rectangle.
    /**
     * getrSide function.
     * @return rSide double.
     */
    public double getrSide() {
        this.rSide = this.getUpperLeft().getX() + this.getWidth();
        return this.rSide;
    }
}