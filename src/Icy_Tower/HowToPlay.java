package Icy_Tower;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public final class HowToPlay extends JFrame {

    public HowToPlay() {
        setSize(329, 160);
        setTitle("Icy Tower");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setIconImage(new ImageIcon(getClass().getResource("/Character/1.png")).getImage());
        Panel panel = new Panel();
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE || e.getKeyCode() == KeyEvent.VK_ENTER) {
                    new IcyTower().setVisible(true);
                    dispose();
                }
            }
        });
        add(panel);
    }

    public class Panel extends JPanel {

        @Override
        public void paint(Graphics grphcs) {
            super.paint(grphcs);
            //paint background images
            Image img = new ImageIcon(getClass().getResource("/Background/HowToPlay.png")).getImage();
            grphcs.drawImage(img, 0, 0, this);
        }
    }
}
