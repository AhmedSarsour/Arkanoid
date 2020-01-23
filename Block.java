import java.util.ArrayList;
import java.util.List;

import biuoop.DrawSurface;

/**
 * Block.
 * @author ahmed.
 * @since 20.05.2017.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private double dSide;
    private double uSide;
    private double lSide;
    private double rSide;
    private Point upperleft;
    private double height;
    private double width;
    private int dure;
    private Rectangle blockRect;
    private java.awt.Color colors;
    private List<HitListener> hitListeners;

    // constructor, gets the essence of the block.
    /**
     * Block constructor.
     * @param rect the Rectangle shape.
     * @param color of block.
     * @param durability the hit points.
     */
    public Block(Rectangle rect, java.awt.Color color,
            int durability) {
        this.dure = durability;
        this.blockRect = rect;
        this.colors = color;
        this.upperleft = rect.getUpperLeft();
        this.height = rect.getHeight();
        this.width = rect.getWidth();
    }

    // constructor that gets the essence of a block but also has
    // accessibility to hit-listening method.
    /**
     * Block constructor.
     * @param rect the Rectangle shape.
     * @param color of block.
     * @param durability the hit points.
     * @param hitListen a hitlistener object.
     */
    public Block(Rectangle rect, java.awt.Color color, int durability,
            HitListener hitListen) {
        this.dure = durability;
        this.blockRect = rect;
        this.colors = color;
        this.upperleft = rect.getUpperLeft();
        this.height = rect.getHeight();
        this.width = rect.getWidth();
        this.hitListeners = new ArrayList<>();
        this.addHitListener((HitListener) hitListen);
    }

    // gets the middle x-axis point of the line.
    /**
     * midPofX function.
     * @return middle Point.
     */
    public Point midPofX() {
        Line upperPart = new Line(
                new Point(this.getlSide(), this.getdSide()),
                new Point(this.getrSide(), this.getdSide()));
        return upperPart.middle();
    }

    // getting the color of the block.
    /**
     * getColor.
     * @return colors of the block.
     */
    public java.awt.Color getColor() {
        return this.colors;
    }

    // gets the hit points of the block.
    /**
     * getHitPoints function.
     * @return dure string durability.
     */
    public int getHitPoints() {
        return this.dure;
    }

    // sets a changed hit-point.
    /**
     * setHitPoints function.
     * @param hitP of block.
     */
    public void setHitPoints(int hitP) {
        this.dure = hitP;
    }

    // gets the upper left point of the block.
    /**
     * getUpperL function.
     * @return upperleft point.
     */
    public Point getUpperL() {
        return this.upperleft;
    }

    // gets the width of the block.
    /**
     * getWidth function.
     * @return width of block.
     */
    public double getWidth() {
        return this.width;
    }

    // gets the height of the block.
    /**
     * getHeight function.
     * @return height of block.
     */
    public double getHeight() {
        return this.height;
    }

    // gets the y-axis down-side point of the block.
    /**
     * getdSide function.
     * @return dSide point.
     */
    public double getdSide() {
        this.dSide = this.blockRect.getUpperLeft().getY()
                + this.blockRect.getHeight();
        return this.dSide;
    }

    // gets the y-axis down-side point of the block.
    /**
     * getdSide function.
     * @return dSide point.
     */
    public double getuSide() {
        this.uSide = this.blockRect.getUpperLeft().getY();
        return this.uSide;
    }

    // gets the x-axis left-side point of the block.
    /**
     * getlSide function.
     * @return lSide point.
     */
    public double getlSide() {
        this.lSide = this.blockRect.getUpperLeft().getX();
        return this.lSide;
    }

    // gets the x-axis right-side point of the block.
    /**
     * getrSide function.
     * @return rSide point.
     */
    public double getrSide() {
        this.rSide = this.blockRect.getUpperLeft().getX()
                + this.blockRect.getWidth();
        return this.rSide;
    }

    // draws the block.
    /**
     * drawOn function.
     * @param surface draw on.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.colors);
        surface.fillRectangle(
                (int) this.blockRect.getUpperLeft().getX(),
                (int) this.blockRect.getUpperLeft().getY(),
                (int) this.blockRect.getWidth(),
                (int) this.blockRect.getHeight());
        surface.setColor(java.awt.Color.WHITE);
        // drawing the hit points of the block.
        if (this.dure >= 1) {
            surface.drawText((int) this.midPofX().getX(),
                    (int) this.midPofX().getY(),
                    Integer.toString(this.dure),
                    (int) this.blockRect.getHeight());
        } else {
            surface.drawText((int) this.midPofX().getX(),
                    (int) this.midPofX().getY(), "X",
                    (int) this.blockRect.getHeight());
        }
        surface.setColor(java.awt.Color.BLACK);
        surface.drawRectangle(
                (int) this.blockRect.getUpperLeft().getX(),
                (int) this.blockRect.getUpperLeft().getY(),
                (int) this.blockRect.getWidth(),
                (int) this.blockRect.getHeight());
    }

    @Override
    public void timePassed() {
        // this.timePassed();
        // TODO Auto-generated method stub
    }

    // returns the rectangular shape of the block.
    /**
     * getCollisionRectangle function.
     * @return blockRect the rectangle shape of block.
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.blockRect;
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint,
            Velocity currentVelocity) {
        if (collisionPoint != null) {
            this.notifyHit(hitter);
            // getting the sides of the rectangle.
            this.setHitPoints(this.dure - 1);
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

    // adds the block to the game by adding it to the SpriteCollection.
    /**
     * addToGame function.
     * @param g game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    // removes the block from the game by removing it from the
    // spritecollection..
    /**
     * addToGame function.
     * @param g the game.
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
    }

    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        List<HitListener> newHitList = new ArrayList<HitListener>(
                this.hitListeners);
        newHitList.remove(hl);
        this.hitListeners = newHitList;
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
