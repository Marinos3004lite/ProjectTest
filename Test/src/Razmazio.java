import javax.swing.*;

public class Razmazio extends JFrame {
    GamePanel gp = new GamePanel(this);
    public Razmazio() {

        add(gp);
        setSize(1024, 750);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }

    /*public static void main(String[] args) {
        System.out.println("hola ");
    }// end main*/
}// end frame class