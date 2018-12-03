import sun.audio.AudioPlayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuOptions extends Sound{
    public JButton videoButton;
    public JButton controlsButton;
    public JButton backButton;
    public static JFrame frame;
    public JPanel panel;
    private JButton muteButton;
    public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public Timer time;

    public MenuOptions() {
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuStartScreen.frame.setContentPane(new MenuStartScreen().panel);
                MenuStartScreen.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                MenuStartScreen.frame.pack();
                MenuStartScreen.frame.setSize(1280,720);
                MenuStartScreen.frame.setVisible(true);
                MenuStartScreen.frame.setLocation((int)(screenSize.getWidth()- MenuStartScreen.frame.getSize().width)/2,(int)(screenSize.getHeight()- MenuStartScreen.frame.getSize().height)/2);
            }


        });
        controlsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MenuStartScreen.frame.setContentPane(new MenuHelp().panel);
                MenuStartScreen.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                MenuStartScreen.frame.pack();
                MenuStartScreen.frame.setSize(1280, 720);
                MenuStartScreen.frame.setVisible(true);
                MenuStartScreen.frame.setLocation((int) (screenSize.getWidth() - MenuStartScreen.frame.getSize().width) / 2, (int) (screenSize.getHeight() - MenuStartScreen.frame.getSize().height) / 2);
            }
        });
        muteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (MenuStartScreen.flag==1)
                {
                    muteButton.setText("Unmute");
                    MenuStartScreen.flag=0;

                }
                else
                {
                    muteButton.setText("Mute");
                    MenuStartScreen.flag=1;
                }

            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (MenuStartScreen.flag==1)
                {
                    muteButton.setText("Mute");
                }
                else
                {
                    muteButton.setText("Unmute");
                }
                Image background = new ImageIcon("src/animated_bg_finished.gif").getImage();
                g.drawImage(background, 0, 0,1280,720, null);
                time = new Timer(1000000000, null);
                time.start();
                repaint();
            }
        };
    }
}
