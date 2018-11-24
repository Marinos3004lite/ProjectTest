import javax.swing.*;

public class Razmazio extends JFrame {
    GamePanel gp = new GamePanel();

    public Razmazio() {
        add(gp);
        setSize(1024, 750);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        Razmazio rz = new Razmazio();
    }// end main
}// end frame class