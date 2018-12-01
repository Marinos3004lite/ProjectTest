import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MenuGameOver {
    public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public JPanel panel;
    private JButton BACKTOMAINMENUButton;
    public Timer time;

    public MenuGameOver() {
        BACKTOMAINMENUButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                MenuStartScreen.frame.setContentPane(new MenuStartScreen().panel);
                MenuStartScreen.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                MenuStartScreen.frame.pack();
                MenuStartScreen.frame.setSize(1280, 720);
                MenuStartScreen.frame.setVisible(true);
                MenuStartScreen.frame.setLocation((int) (screenSize.getWidth() - MenuStartScreen.frame.getSize().width) / 2, (int) (screenSize.getHeight() - MenuStartScreen.frame.getSize().height) / 2);
            }
        });

    }
    private void createUIComponents() {
        // TODO: place custom component creation code here
        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                this.paintComponent(g);
                this.setSize(1280,720);
               // Image background = new ImageIcon("src/src/animated_bg_finished.gif").getImage();
               // g.drawImage(background, 0, 0,1280,720, null);
                time = new Timer(1, null);
                time.start();
                repaint();
            }
        };
    }
}

