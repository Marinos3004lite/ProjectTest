import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuHelp {
    public JPanel panel;
    public JButton dButton;
    public JButton rButton;
    public JButton spaceButton;
    public JButton enterButton;
    public JButton aButton;


    public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public JButton backButton;
    public Timer time;


    public MenuHelp() {
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
    }



    private void createUIComponents() {
        // TODO: place custom component creation code here
        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image background = new ImageIcon("src/animated_bg_finished.gif").getImage();
                g.drawImage(background, 0, 0,1280,720, null);
                time = new Timer(1, null);
                time.start();
                repaint();
            }
        };
    }
}
