import javax.swing.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MenuStartScreen{
    public JButton playButton;
    public JButton optionsButton;
    public JButton helpButton;
    public JButton shopButton;
    public JButton exitButton;
    public static JFrame frame;
    public JPanel panel;
    public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public Image menuBackground = new ImageIcon("src/ezgif.com-video-to-gif.gif").getImage();
    public Timer time;

    public void setMenuBackground(Graphics g) {
        g.drawImage(menuBackground, 0, 0, null);
    }

    public MenuStartScreen() {


        optionsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuStartScreen.frame.setContentPane(new MenuOptions().panel);
                MenuStartScreen.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                MenuStartScreen.frame.pack();
                MenuStartScreen.frame.setSize(1280, 720);
                MenuStartScreen.frame.setVisible(true);
                MenuStartScreen.frame.setLocation((int) (screenSize.getWidth() - MenuStartScreen.frame.getSize().width) / 2, (int) (screenSize.getHeight() - MenuStartScreen.frame.getSize().height) / 2);
            }
        });
        shopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuStartScreen.frame.setContentPane(new MenuShop().panel);
                MenuStartScreen.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                MenuStartScreen.frame.pack();
                MenuStartScreen.frame.setSize(1280, 720);
                MenuStartScreen.frame.setVisible(true);
                MenuStartScreen.frame.setLocation((int) (screenSize.getWidth() - MenuStartScreen.frame.getSize().width) / 2, (int) (screenSize.getHeight() - MenuStartScreen.frame.getSize().height) / 2);
            }
        });
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //MenuStartScreen.frame.setVisible(false);
                MenuStartScreen.frame.setVisible(false);
                Razmazio rz = new Razmazio();
            }
        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(-1);
            }
        });
    }


    public static void main(String args[]) {
        frame = new JFrame("Menu");
        frame.setContentPane(new MenuStartScreen().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(1280, 720);
        frame.setVisible(true);
        frame.setLocation((int) (screenSize.getWidth() - frame.getSize().width) / 2, (int) (screenSize.getHeight() - frame.getSize().height) / 2);
    }

    private void createUIComponents()
    {
        // TODO: place custom component creation code here
        panel = new JPanel()
        {
            @Override
            protected void paintComponent(Graphics g)
            {
                super.paintComponent(g);
                Image background = new ImageIcon("src/animated_bg_finished.gif").getImage();
                g.drawImage(background, 0, 0, 1280, 720, null);
                time = new Timer(1, null);
                time.start();
                repaint();
            }
        };
    }
}
