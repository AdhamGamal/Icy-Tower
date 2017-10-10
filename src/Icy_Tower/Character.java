package Icy_Tower;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Image;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

public class Character extends ImageModel {

    AudioClip acjump;
    Input input;
    int y_cpy = -1;
    boolean jump = false;
    boolean end_jump = true;
    boolean dead = false;
    int jumpLevel = 90;
    boolean right_space = false;
    boolean left_space = false;
    boolean right = false;
    boolean left = false;
    int countRight = 0;
    int countLeft = 0;

    public Character(Image img, Input input) {
        this.img = img;
        width = img.getWidth(null);
        height = img.getHeight(null);
        this.x = (500 - 5) / 2 - width / 2;
        this.y = 500 - 25 - height;
        dx = 3;
        dy = 0;
        this.input = input;
        stored_dy = 5;
        //add audio
        acjump = Applet.newAudioClip(getClass().getResource("/Sound/jump_lo.aiff"));
    }

    @Override
    public void update() {
        //check for arnold move in x axis
        if (x < 60) {
            x = 60;
        }
        if (x > 500 - width - 5 - 60) {
            x = 500 - width - 5 - 60;
        }
        //check for arnold move in y axis
        if (y > 500 - height - 25) {
            dead = true;
            AudioClip acfall = Applet.newAudioClip(getClass().getResource("/Sound/falling.aiff"));
            acfall.play();
            y = -y;
        }
        //check if arnold is jump
        if (jump) {
            //make him go up
            dy = -stored_dy;
            img = new ImageIcon(getClass().getResource("/Character/jump.png")).getImage();
        }
        //check if arnold is reach the height of his jump or the height of the screen
        if (y <= y_cpy - jumpLevel) {
            //change image if he jum to right block or left
            if (right_space) {
                img = new ImageIcon(getClass().getResource("/Character/jump2right.png")).getImage();
                right_space = false;
            } else if (left_space) {
                img = new ImageIcon(getClass().getResource("/Character/jump2left.png")).getImage();
                left_space = false;
            }
            //reset y_cpy
            y_cpy = -1;
            //make him go down
            dy = stored_dy;
            //make jump false and he don`t end the jump yet 
            //that mean he goes down
            jump = false;
            end_jump = false;
        } else if (y < 0) {
            y_cpy = -1;
            dy = stored_dy;
            jump = false;
            end_jump = false;
        }
        //check for keys input to change the position or jump
        //if key is right
        if (input.keyIs(KeyEvent.VK_RIGHT)) {
            x += dx;
            right = true;
            countRight++;
        }
        //if key is left
        if (input.keyIs(KeyEvent.VK_LEFT)) {
            x -= dx;
            left = true;
            countLeft++;
        }
        //if key is space
        if (input.keyIs(KeyEvent.VK_SPACE)) {
            if (!jump && end_jump) {
                acjump.play();
                jump = true;
                y_cpy = y;
            }
        }

        //if jump right or left
        if (input.keyIs(KeyEvent.VK_RIGHT) && input.keyIs(KeyEvent.VK_SPACE)) {
            right_space = true;
            img = new ImageIcon(getClass().getResource("/Character/jump1right.png")).getImage();
        } else if (input.keyIs(KeyEvent.VK_LEFT) && input.keyIs(KeyEvent.VK_SPACE)) {
            left_space = true;
            img = new ImageIcon(getClass().getResource("/Character/jump1left.png")).getImage();
        }

        //for walk images change right
        if (right && !jump) {
            switch (countRight) {
                case 1:
                    img = new ImageIcon(getClass().getResource("/Character/walk1right.png")).getImage();
                    right = false;
                    break;
                case 2:
                    img = new ImageIcon(getClass().getResource("/Character/walk2right.png")).getImage();
                    right = false;
                    break;
                case 3:
                    img = new ImageIcon(getClass().getResource("/Character/walk3right.png")).getImage();
                    countRight = 0;
                    right = false;
                    break;
                default:
                    countRight = 0;
                    right = false;
            }
        } //for walk images change left
        else if (left && !jump) {
            switch (countLeft) {
                case 1:
                    img = new ImageIcon(getClass().getResource("/Character/walk1left.png")).getImage();
                    left = false;
                    break;
                case 2:
                    img = new ImageIcon(getClass().getResource("/Character/walk2left.png")).getImage();
                    left = false;
                    break;
                case 3:
                    img = new ImageIcon(getClass().getResource("/Character/walk3left.png")).getImage();
                    countLeft = 0;
                    left = false;
                    break;
                default:
                    countLeft = 0;
                    left = false;
            }
        }
        y += dy;
    }
}
