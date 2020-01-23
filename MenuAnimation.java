import java.util.ArrayList;
import java.util.List;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;

/**
 * MenuAntimation.
 * @author ahmed.
 * @param <T> the type.
 * @since 15.06.2017.
 */
public class MenuAnimation<T> implements Menu<T> {
    private String title;
    private GUI gui;
    private KeyboardSensor keyboard;
    private List<OptionsInfo<T>> keys;
    private T status;
    private boolean setAndGo;

    /**
     * menuAnimation constructor.
     * @param name the title.
     * @param theGui surface.
     */
    public MenuAnimation(String name, GUI theGui) {
        this.title = name;
        this.gui = theGui;
        this.keyboard = theGui.getKeyboardSensor();
        this.keys = new ArrayList<OptionsInfo<T>>();
        this.setAndGo = true;
    }

    @Override
    public void addSelection(String keyToPress, String phrase,
            T returnOpt) {
        this.keys.add(
                new OptionsInfo<T>(keyToPress, phrase, returnOpt));
    }

    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        KeyboardSensor keyb = this.gui.getKeyboardSensor();
        Sleeper sleeper = new Sleeper();
        while (true) {
            d = this.gui.getDrawSurface();
            d.setColor(java.awt.Color.black);
            d.fillRectangle(0, 0, 800, 600);
            this.drawDecorations(d);
            d.setColor(java.awt.Color.yellow);
            for (int i = 0; i < this.keys.size(); i++) {
                d.drawText(d.getWidth() / 2 - 32,
                        i * 50 + d.getHeight() / 3,
                        (String) this.keys.get(i).getOptStr(), 32);
            }
            // the gamer destroyed all the blocks, displaying a WIN phrase.
            d.setColor(java.awt.Color.yellow);
            // if the space key is pressed, ending the display of the endScreen.
            for (int i = 0; i < this.keys.size(); i++) {
                if (keyb.isPressed(this.keys.get(i).getPressKey())) {
                    System.out.println("clicked");
                    this.status = (T) this.keys.get(i).getOptStr();
                    this.setAndGo = false;
                    return;
                }
            }
            // drawing the current state of the level.
            this.gui.show(d);
            sleeper.sleepFor(50);
        }
    }

    /**
     * setting the animation to run again.
     */
    public void setSetAndGo() {
        this.setAndGo = true;
    }

    @Override
    public boolean shouldStop() {
        return !this.setAndGo;
    }

    /**
     * setStatus of the menu animation.
     * @param theStatus the new status.
     */
    public void setStatus(T theStatus) {
        this.status = theStatus;
    }

    @Override
    public T getStatus() {
        return this.status;
    }

    /**
     * drawDecoractions for the menu.
     * @param d the drawSurface.
     */
    public void drawDecorations(DrawSurface d) {
        String gameName = "ARKANOID";
        d.setColor(java.awt.Color.DARK_GRAY);
        int j = 0;
        for (int i = 7; i >= 0; i--) {
            d.drawText(120 + j * 80, 500 - j * 50,
                    String.valueOf(gameName.charAt(j)), 120);
            j++;
        }
        d.setColor(java.awt.Color.ORANGE);
        for (int i = 0; i < 35; i++) {
            d.drawLine(-100, i * 30, 1000, i * 15);
        }
        d.drawText(250, 80, "WELCOME TO", 30);
        d.setColor(java.awt.Color.red);
        d.drawText(250, 120, "ARKANOID!", 50);
    }
}
