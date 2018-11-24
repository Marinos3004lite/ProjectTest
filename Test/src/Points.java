import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;


    public class Points extends JPanel {
        public int points;

        void Add_Points(int x){
            if(x>=0) {
                this.points = this.points + x;
            }
        }
        void Sub_Points(int x){
            if(x>=0) {
                this.points = this.points - x;
            }
        }
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.BLACK);
            //MapManager.background = new ImageIcon("src/backgrounds\\bkg5.png").getImage();
            //  g.drawImage(MapManager.background, 700 - bk_x, 0, null); // Drawing background
            g.setColor(Color.BLACK);
            g.setFont(new Font("Monospaced", Font.BOLD, 20));
            g.drawString("POINTS = " + points,450, 30);
        }
    }


