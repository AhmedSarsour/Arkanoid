import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import biuoop.DrawSurface;

/**
 * BlockGenerator.
 * @author ahmed.
 * @since 20.05.2017.
 */
public class BlockGenerator extends Block {
    private List<HitListener> hitListeners;
    private Rectangle recta;
    private java.awt.Color deathColor;

    // Constructor, the same constructor as a block but has a BallGenerator
    // hitListener.
    /**
     * Constructor.
     * @param rect the shape.
     * @param color of the block.
     * @param durability of the block.
     * @param hitListen to the balls.
     */
    public BlockGenerator(Rectangle rect, Color color, int durability,
            BallGenerator hitListen) {
        super(rect, color, durability);
        this.recta = rect;
        this.deathColor = color;
        this.hitListeners = new ArrayList<>();
        this.hitListeners.add((HitListener) hitListen);
    }

    // a different draw method from that of a block.
    /**
     * drawOn function.
     * @param surface we work upon.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.deathColor);
        surface.fillRectangle((int) this.recta.getUpperLeft().getX(),
                (int) this.recta.getUpperLeft().getY(),
                (int) this.recta.getWidth(),
                (int) this.recta.getHeight());
        surface.setColor(java.awt.Color.WHITE);
        // drawing a "LIVE" in the middle of the block
        surface.drawText(
                (int) this.recta.getlSide()
                        + (int) (this.recta.getWidth() / 4),
                (int) this.midPofX().getY()
                        - (int) this.recta.getHeight() / 3,
                "LIVE", (int) this.recta.getHeight() * 2 / 3);
        surface.setColor(java.awt.Color.black);
        surface.drawRectangle((int) this.recta.getUpperLeft().getX(),
                (int) this.recta.getUpperLeft().getY(),
                (int) this.recta.getWidth(),
                (int) this.recta.getHeight());
    }
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint,
            Velocity currentVelocity) {
        if (collisionPoint != null) {
            this.notifyHit(hitter);
            double upSide = this.getCollisionRectangle()
                    .getUpperLeft().getY();
            double dwnSide = this.getCollisionRectangle()
                    .getUpperLeft().getY()
                    + this.getCollisionRectangle().getHeight();
            double lftSide = this.getCollisionRectangle()
                    .getUpperLeft().getX();
            double rghtSide = this.getCollisionRectangle()
                    .getUpperLeft().getX()
                    + this.getCollisionRectangle().getWidth();
            // making the ball bounce when hitting the block.
            if (collisionPoint.getX() == lftSide
                    || collisionPoint.getX() == rghtSide
                    || collisionPoint.getY() == upSide
                    || collisionPoint.getY() == dwnSide) {
                if (upSide == collisionPoint.getY()
                        || dwnSide == collisionPoint.getY()) {
                    currentVelocity
                            .setDy((-1) * currentVelocity.getDy());
                }
                if (lftSide == collisionPoint.getX()
                        || rghtSide == collisionPoint.getX()) {
                    currentVelocity
                            .setDx((-1) * currentVelocity.getDx());
                }
                // returning the new velocity if a occurred,
                return new Velocity(currentVelocity.getDx(),
                        currentVelocity.getDy());
            }
        }
        // returning the same velocity if there is no hit.
        return currentVelocity;
    }

    //notifies the hit listeners about a hit event.
    /**
     * notifyHit function.
     * @param hitter the ball.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(
                this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
}
