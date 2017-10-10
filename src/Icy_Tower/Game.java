package Icy_Tower;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public final class Game extends JFrame {

    private static Input input;
    private static Character player;
    private static Block[] block;
    private final int size = 150;
    private static Background background, border;
    private static int level = 1;
    private static int count = 0;

    public Game() {
        setSize(500, 500);
        setTitle("Icy Tower");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setIconImage(new ImageIcon(getClass().getResource("/Character/1.png")).getImage());
        init();
        //add key listener 
        addKeyListener(input);
        //add game panel
        add(new GamePanal());
    }

    public class GamePanal extends JPanel implements ActionListener {

        //Timer
        Timer timer;

        public GamePanal() {
            //make timer do the action every 10 m.s
            timer = new Timer(10, this);
            //start timer 
            timer.start();
        }

        @Override
        public void paint(Graphics grphcs) {
            super.paint(grphcs);
            //repeat the background and border
            if (background.y >= 0) {
                background.y = background.img.getHeight(null) * -1 + 475;
            }
            if (border.y >= 0) {
                border.y = border.img.getHeight(null) * -1 + 475;
            }
            //conter for make the character dance while standing
            count++;
            if (count % 20 == 0) {
                player.img = new ImageIcon(getClass().getResource("/Character/2.png")).getImage();
            }
            if (count % 30 == 0) {
                player.img = new ImageIcon(getClass().getResource("/Character/3.png")).getImage();
            }
            //draw background 
            grphcs.drawImage(background.img, background.x, background.y, this);
            //draw blocks
            for (int i = 0; i < size; i++) {
                grphcs.drawImage(block[i].img, block[i].x, block[i].y, this);
            }
            //draw border
            grphcs.drawImage(border.img, border.x, border.y, this);
            //draw character
            grphcs.drawImage(player.img, player.x, player.y, this);
            //drae score and level
            grphcs.setColor(Color.red);
            grphcs.drawString("Level : " + level, 150, 460);
            grphcs.drawString("Score : " + Block.getIndex * 10, 300, 460);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            //update every thing every 10 m.s
            player.update();
            background.update();
            border.update();
            for (int i = 0; i < size; i++) {
                block[i].update();
            }
            //repaint before any check
            repaint();
            //check if dead
            if (player.dead) {
                if (JOptionPane.showConfirmDialog(null, "               You Lose!\n         Your Score : " + Block.getIndex * 10 + "\n              Play Again !", "Confirm", JOptionPane.YES_NO_OPTION) == 0) {
                    removeAll();
                    init();
                } else {
                    timer.stop();
                    dispose();
                    new IcyTower().setVisible(true);
                }
                Block.getIndex = 0;
                level = 1;
                count = 0;
            }
            //check for level
            if (Block.getIndex != 0 && Block.getIndex / 50 == level) {
                background.stored_dy++;
                border.stored_dy++;
                for (int i = 0; i < size; i++) {
                    block[i].stored_dy++;
                }
                player.stored_dy++;
                player.jumpLevel -= 10;
                if (level == 3) {
                    player.dx++;
                }
                if (level < 3) {
                    level++;
                }
            }
            if (Block.getIndex == 150) {
                removeAll();
                if (JOptionPane.showConfirmDialog(null, "               You Win !\n         Your Score : " + Block.getIndex * 10 + "\n              Play Again !", "Confirm", JOptionPane.YES_NO_OPTION) == 0) {
                    init();
                } else {
                    timer.stop();
                    dispose();
                    new IcyTower().setVisible(true);
                }
                Block.getIndex = 0;
                level = 1;
                count = 0;
            }
        }
    }

    public void init() {
        input = new Input();
        player = new Character(new ImageIcon(getClass().getResource("/Character/1.png")).getImage(), input);
        background = new Background(new ImageIcon(getClass().getResource("/Background/background.png")).getImage(), 2, input);
        border = new Background(new ImageIcon(getClass().getResource("/Background/border2.png")).getImage(), 1, input);
        block = new Block[size];
        int change = 70;
        //draw block randomly in x and image but y is changed by 70 
        for (int i = 0; i < size; i++) {
            if ((i + 1) % 50 == 0) {
                block[i] = new Block(new ImageIcon(getClass().getResource("/Blocks/Block.png")).getImage(), 0, 475 - change, player, input);
            } else {
                block[i] = new Block(new ImageIcon(getClass().getResource("/Blocks/Block" + (int) ((Math.random() * 3) + 1) + ".png")).getImage(), (int) ((Math.random() * 195) + 75), 475 - change, player, input);
            }
            block[i].index = i + 1;
            change += 70;
        }
    }
}
