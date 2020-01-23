import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import biuoop.DrawSurface;

/**
 * DeathRegionBLock.
 * @author ahmed.
 * @since 20.05.2017.
 */
public class DeathRegionBlock extends Block {
    private List<HitListener> hitListeners;
    private Rectangle recta;
    private java.awt.Color deathColor;

    // constructor, gets the essence of the block and a ballRemover hit
    // listener.
    /**
     * Block constructor.
     * @param rect the Rectangle shape.
     * @param color of block.
     * @param durability the hit points.
     * @param hitListen the ballRemover.
     */
    public DeathRegionBlock(Rectangle rect, Color color,
            int durability, BallRemover hitListen) {
        super(rect, color, durability);
        this.recta = rect;
        this.deathColor = color;
        this.hitListeners = new ArrayList<>();
        this.hitListeners.add((HitListener) hitListen);
    }

    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.deathColor);
        // filling the rectangle.
        surface.fillRectangle((int) this.recta.getUpperLeft().getX(),
                (int) this.recta.getUpperLeft().getY(),
                (int) this.recta.getWidth(),
                (int) this.recta.getHeight());
        surface.setColor(java.awt.Color.WHITE);
        // drawing the "DEATH" phrase in the middle of the block.
        surface.drawText(
                (int) this.recta.getlSide()
                        + (int) (this.recta.getWidth() / 4),
                (int) this.midPofX().getY()
                        - (int) this.recta.getHeight() / 3,
                "DEATH", (int) this.recta.getHeight() * 2 / 3);
        surface.setColor(java.awt.Color.black);
        surface.drawRectangle((int) this.recta.getUpperLeft().getX(),
                (int) this.recta.getUpperLeft().getY(),
                (int) this.recta.getWidth(),
                (int) this.recta.getHeight());
    }

    // there is no reduction in the hitpoints, this block is in-destructible.
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint,
            Velocity currentVelocity) {
        if (collisionPoint != null) {
            this.notifyHit(hitter);
        }
        return currentVelocity;
    }

    // notifying the hit listeners of a hit event.
    /**
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
