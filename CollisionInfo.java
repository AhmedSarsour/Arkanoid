/**
 * CollisionInfo.
 * @author ahmed.
 * @since 20.05.2017.
 */
public class CollisionInfo {
    private Point collisionP;
    private Collidable object;

    // Constructor, gets the Collision Point and collision object.
    /**
     * CollisionInfo constructor.
     * @param collision Point.
     * @param collisionObject the shape.
     */
    public CollisionInfo(Point collision,
            Collidable collisionObject) {
        this.collisionP = collision;
        this.object = collisionObject;
    }

    // the point at which the collision occurs.
    /**
     * collisionPoint function.
     * @return Point Collision.
     */
    public Point collisionPoint() {
        return this.collisionP;
    }

    // the collidable object involved in the collision. Block,paddle or edges of
    // the screen.
    /**
     * collisionObject function.
     * @return object Collidable.
     */
    public Collidable collisionObject() {
        return this.object;
    }
}