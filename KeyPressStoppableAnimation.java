import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
/**
* keyPressStoppableAnimation.
* @author ahmed.
* @since 14.06.2017.
*/
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor keyboard;
    private String animationKey;
    private Animation animate;
    private boolean running;
    /**
    * constructor that accepts the keyboard sensor, a key and the animation.
    * @param sensor the keyboard.
    * @param key the key to press.
    * @param animation the animation.
    */
    public KeyPressStoppableAnimation(KeyboardSensor sensor,
            String key, Animation animation) {
        this.keyboard = sensor;
        this.animationKey = key;
        this.animate = animation;
        this.running = true;
    }

    @Override
    public void doOneFrame(DrawSurface d, double dt) {
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }
}