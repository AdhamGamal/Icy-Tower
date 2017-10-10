package Icy_Tower;

import Icy_Tower.HowToPlay;
import Icy_Tower.Game;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public final class IcyTower extends JFrame {

    public IcyTower() {
        setSize(500, 500);
        setTitle("Icy Tower");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setIconImage(new ImageIcon(getClass().getResource("/Character/1.png")).getImage());

        Panel panel = new Panel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 25, 25));
        //buttons
        JButton Start = new JButton("Start");
        JButton HowToPlay = new JButton("How To Play");
        JButton Close = new JButton("Close");
        //setContentAreaFilled(false)
        Start.setContentAreaFilled(false);
        HowToPlay.setContentAreaFilled(false);
        Close.setContentAreaFilled(false);
        //setOpaque(true) to make setbackground effective
        Start.setOpaque(true);
        HowToPlay.setOpaque(true);
        Close.setOpaque(true);
        //change background color
        Start.setBackground(Color.LIGHT_GRAY);
        HowToPlay.setBackground(Color.LIGHT_GRAY);
        Close.setBackground(Color.LIGHT_GRAY);
        //add action listener
        Start.addActionListener((e) -> {
            dispose();
            new Game().setVisible(true);
        });
        HowToPlay.addActionListener((e) -> {
            dispose();
            new HowToPlay().setVisible(true);
        });
        Close.addActionListener((e) -> {
            System.exit(0);
        });
        //add buttons to panel
        panel.add(Start);
        panel.add(HowToPlay);
        panel.add(Close);
        //add panel
        add(panel);
    }

    public class Panel extends JPanel {

        @Override
        public void paintComponent(Graphics grphcs) {
            super.paintComponent(grphcs);
            //paint background images
            grphcs.drawImage(new ImageIcon(getClass().getResource("/Background/background.png")).getImage(), 0, 0, this);
            grphcs.drawImage(new ImageIcon(getClass().getResource("/Background/icytower.gif")).getImage(), 50, 70, this);
        }
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException ex) {
        }
        //open main frame
        new IcyTower().setVisible(true);
    }
}
