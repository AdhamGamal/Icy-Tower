package Icy_Tower;

import java.awt.Image;
import java.awt.event.KeyEvent;

public class Background extends ImageModel {

    private final Input input;

    public Background(Image img, double dy, Input input) {
        this.img = img;
        x = 0;
        y = img.getHeight(null) * -1 + 475;
        stored_dy = dy;
        this.input = input;
    }

    @Override
    public void update() {
        //the first space the player will press will make the game began
        if (input.keyIs(KeyEvent.VK_SPACE)) {
            //make background move down
            dy = stored_dy;
        }
        y += dy;
    }
}
