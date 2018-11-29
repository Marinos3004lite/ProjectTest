import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Coins extends JPanel {

    public int num_coins;
    Sprite coin;

    public Coins(int x, int y) {
        coin = new Sprite(x, y);
        coin.loadImage("src/razmazio\\coin.gif");
        coin.getImageDimensions();
    }

    public void checkVis()
    {
        if (coin.isVisible())
            System.out.println("Hi");
        else
            System.out.println("Not Hi");
    }
    public void Add_Coins(int x){
        if(x >= 0) {
            this.num_coins = this.num_coins + x;
        }
    }
    public void Sub_Coins(int x){
        if(x >= 0) {
            this.num_coins = this.num_coins - x;
        }
    }

    public void paintComponent(Graphics g) {
     super.paintComponent(g);
        g.setColor(Color.BLACK);
        //MapManager.background = new ImageIcon("src/backgrounds\\bkg5.png").getImage();
        //  g.drawImage(MapManager.background, 700 - bk_x, 0, null); // Drawing background
        g.setColor(Color.BLACK);
        g.setFont(new Font("Monospaced", Font.BOLD, 20));
        g.drawString("COINS = " + num_coins,450, 60);
    }

}
