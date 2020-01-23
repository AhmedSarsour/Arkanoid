import java.util.List;

/**
 * GameEnvironment.
 * @author ahmed.
 * @since 20.05.2017.
 */
public class GameEnvironment {
    private List<Collidable> collidableList;
    private Velocity velocity;

    // add the given collidable to the environment.
    /**
     * addCollidable function.
     * @param c collidable object.
     */
    public void addCollidable(Collidable c) {
        this.collidableList.add(c);
    }
    // constructor,getting a collidable objects list.

    /**
     * GameEnvironment constructor.
     * @param allCollidables for the Game Environment.
     */
    public GameEnvironment(List<Collidable> allCollidables) {
        this.collidableList = allCollidables;
    }

    // Assume an object moving from line.start() to line.end().
    // If this object will not collide with any of the collidables
    // in this collection, return null. Else, return the information
    // about the closest collision that is going to occur.
    /**
     * getClosestCollision function.
     * @param trajectory the line with no collision.
     * @return CollisionInfo point and object.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        /*
         * depending on the velocity we read the collidables'list and also
         * we determine which point(end or start) of the trajectory line we use.
         */
        if (this.velocity.getDx() < 0) {
            for (int i = 0; i < collidableList.size(); i++) {
                // getting the sides of the collidable object.
                double leftX = collidableList.get(i)
                        .getCollisionRectangle().getUpperLeft()
                        .getX();
                double upY = collidableList.get(i)
                        .getCollisionRectangle().getUpperLeft()
                        .getY();
                double rightX = collidableList.get(i)
                        .getCollisionRectangle().getUpperLeft().getX()
                        + collidableList.get(i)
                                .getCollisionRectangle().getWidth();
                double downY = collidableList.get(i)
                        .getCollisionRectangle().getUpperLeft().getY()
                        + collidableList.get(i)
                                .getCollisionRectangle().getHeight();
                trajectory.setVelocity(this.velocity);
                // depending on the velocity's signal we decide which point of
                // the line to work upon.
                if (trajectory.start().getX() >= leftX
                        && trajectory.start().getX() <= rightX) {
                    if (trajectory.start().getY() <= downY
                            && trajectory.start().getY() >= upY) {
                        // returning CollisionInfo about a collision that
                        // occurred.
                        return new CollisionInfo(
                                trajectory
                                        .closestIntersectionToStartOfLine(
                                                (collidableList.get(i)
                                                        .getCollisionRectangle())),
                                collidableList.get(i));
                    }
                }
            }
        } else {
            for (int i = collidableList.size() - 1; i >= 0; i--) {
                // getting the sides of the collidable object.
                double leftX = collidableList.get(i)
                        .getCollisionRectangle().getUpperLeft()
                        .getX();
                double upY = collidableList.get(i)
                        .getCollisionRectangle().getUpperLeft()
                        .getY();
                double rightX = collidableList.get(i)
                        .getCollisionRectangle().getUpperLeft().getX()
                        + collidableList.get(i)
                                .getCollisionRectangle().getWidth();
                double downY = collidableList.get(i)
                        .getCollisionRectangle().getUpperLeft().getY()
                        + collidableList.get(i)
                                .getCollisionRectangle().getHeight();
                trajectory.setVelocity(this.velocity);
                // depending on the velocity's signal we decide which point(end
                // or start) of the line to work upon.
                if (trajectory.end().getX() >= leftX
                        && trajectory.end().getX() <= rightX) {
                    if (trajectory.end().getY() <= downY
                            && trajectory.end().getY() >= upY) {
                        // returning the collision point and collision object.
                        return new CollisionInfo(
                                trajectory
                                        .closestIntersectionToStartOfLine(
                                                (collidableList.get(i)
                                                        .getCollisionRectangle())),
                                collidableList.get(i));
                    }
                }
            }
        }
        // no collision then return null.
        return null;
    }

    // getting the velocity of the given ball in order to use the trajectory
    // line efficiently.
    /**
     * getVelocity.
     * @param v the velocity.
     * @return Velocity of the given ball.
     */
    public Velocity getVelocity(Velocity v) {
        this.velocity = v;
        return this.velocity;
    }
}