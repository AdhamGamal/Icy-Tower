package Icy_Tower;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Image;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

public class Block extends ImageModel {

    private final AudioClip acfall;
    private final Character player;
    private final Input input;
    public int index = 0;
    public static int getIndex;

    public Block(Image img, int x, int y, Character player, Input input) {
        this.img = img;
        this.x = x;
        this.y = y;
        width = img.getWidth(null);
        height = img.getHeight(null);
        this.player = player;
        dy = 0;
        this.input = input;
        stored_dy = 1;
        acfall = Applet.newAudioClip(getClass().getResource("/Sound/edge.aiff"));
    }

    @Override
    public void update() {
        //check if arold is reach the surface of the block
        if (player.dy > stored_dy && player.y + player.height >= y && player.y + player.height <= y + height
                && player.x + player.width / 2 >= x && player.x + player.width / 2 <= x + width) {
            player.dy = stored_dy;
            getIndex = index;
            player.y = y - player.height + 2;
            player.jump = false;
            player.end_jump = true;
        } else if (player.dy == stored_dy && player.y + player.height >= y && player.y + player.height <= y + height
                && (player.x + player.width / 2 <= x || player.x + player.width / 2 >= x + width)) {
            //change image based on the direction
            if (player.right) {
                player.img = new ImageIcon(getClass().getResource("/Character/jump3right.png")).getImage();
            } else if (player.left) {
                player.img = new ImageIcon(getClass().getResource("/Character/jump3left.png")).getImage();
            }
            //play sound for fall
            acfall.play();
            //fall fast
            player.dy = stored_dy + 4;
            //he is falling from block
            player.jump = false;
            player.end_jump = false;
        }
        //the first space the player will press will make the game began
        if (input.keyIs(KeyEvent.VK_SPACE)) {
            //make block move down
            dy = stored_dy;
        }
        y += dy;
    }

}
