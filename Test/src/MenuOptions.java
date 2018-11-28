import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuOptions {
    public JButton soundButton;
    public JButton videoButton;
    public JButton controlsButton;
    public JButton backButton;
    public static JFrame frame;
    public JPanel panel;
    public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();


    public MenuOptions() {
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuStartScreen.frame.setContentPane(new MenuStartScreen().panel1);
                MenuStartScreen.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                MenuStartScreen.frame.pack();
                MenuStartScreen.frame.setSize(1280,720);
                MenuStartScreen.frame.setVisible(true);
                MenuStartScreen.frame.setLocation((int)(screenSize.getWidth()- MenuStartScreen.frame.getSize().width)/2,(int)(screenSize.getHeight()- MenuStartScreen.frame.getSize().height)/2);
            }
        });
    }
}
