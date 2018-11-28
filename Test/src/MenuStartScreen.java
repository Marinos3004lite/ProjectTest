import javax.swing.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.awt.*;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.image.ImageObserver;


public class MenuStartScreen extends GamePanel{
    public JButton playButton;
    public JButton optionsButton;
    public JButton helpButton;
    public JButton shopButton;
    public JButton exitButton;
    public static JFrame frame;
    public JPanel panel;
    public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public JLabel l1;
    public static ImageIcon i = new ImageIcon("texture.jpg");
    public static JLabel l = new JLabel();
    protected Image background = new ImageIcon("src/razmazio\\still_right.png").getImage(); // Standing still


    public MenuStartScreen() {
        optionsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuStartScreen.frame.setContentPane(new MenuOptions().panel);
                MenuStartScreen.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                MenuStartScreen.frame.pack();
                MenuStartScreen.frame.setSize(1280,720);
                MenuStartScreen.frame.setVisible(true);
                MenuStartScreen.frame.setLocation((int)(screenSize.getWidth()- MenuStartScreen.frame.getSize().width)/2,(int)(screenSize.getHeight()- MenuStartScreen.frame.getSize().height)/2);
            }
        });
        shopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuStartScreen.frame.setContentPane(new MenuShop().panel);
                MenuStartScreen.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                MenuStartScreen.frame.pack();
                MenuStartScreen.frame.setSize(1280,720);
                MenuStartScreen.frame.setVisible(true);
                MenuStartScreen.frame.setLocation((int)(screenSize.getWidth()- MenuStartScreen.frame.getSize().width)/2,(int)(screenSize.getHeight()- MenuStartScreen.frame.getSize().height)/2);
            }
        });
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Razmazio rz = new Razmazio();
                MenuStartScreen.frame.setVisible(false);
            }
        });
    }
    public void setBackground(Graphics g) {
        g.drawImage(background, 0, 0, null); // Drawing background
    }

    public static void main(String args[]) {
        frame = new JFrame("Menu");
        frame.setContentPane(new MenuStartScreen().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(1280,720);
        frame.setVisible(true);
        frame.setLocation((int)(screenSize.getWidth()-frame.getSize().width)/2,(int)(screenSize.getHeight()-frame.getSize().height)/2);

    }




    private void createUIComponents() {
        // TODO: place custom component creation code here
    }



}
