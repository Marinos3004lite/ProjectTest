import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuShop {
    public JPanel panel;

    public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public JButton backButton;


    public MenuShop() {
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
}
