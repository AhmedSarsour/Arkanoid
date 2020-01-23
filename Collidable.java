/**
 * Collidable interface.
 * @author ahmed.
 * @since 20.05.2017.
 */
public interface Collidable {
    // Return the "collision shape" of the object.
    /**
     * getCollisionRectangle function.
     * @return object the Rectangle shape.
     */
    Rectangle getCollisionRectangle();

    // Notify the object that we collided with it at collisionPoint with
    // a given velocity.
    // The return is the new velocity expected after the hit (based on
    // the force the object inflicted on us).
    /**
     * hit function.
     * @param collisionPoint the Point.
     * @param currentVelocity the Velocity.
     * @param hitter the ball.
     * @return Velocity after colliding.
     */
    Velocity hit(Ball hitter, Point collisionPoint,
            Velocity currentVelocity);
}