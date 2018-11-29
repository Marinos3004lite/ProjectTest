import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Coins extends JPanel {

    public int coins;
    public int x, y;
    Sprite coin = new Sprite(x, y);

    /*public Coins(int x, int y) {
        super(x, y);
        {
            coin = new ImageIcon("src/razmazio\\coin.gif").getImage();
            getImageDimensions();
        }
*/
    public Coins() {
        coin.loadImage("src/razmazio\\coin.gif");
        Rectangle r = coin.getBounds();
    }
    /*void Add_Coins(int x){
        if(x >= 0) {
            this.coins = this.coins + x;
        }
    }
    void Sub_Coins(int x){
        if(x >= 0) {
            this.coins = this.coins - x;
        }
    }*/

    public void paintComponent(Graphics g) {
     super.paintComponent(g);
        g.setColor(Color.BLACK);
        //MapManager.background = new ImageIcon("src/backgrounds\\bkg5.png").getImage();
        //  g.drawImage(MapManager.background, 700 - bk_x, 0, null); // Drawing background
        g.setColor(Color.BLACK);
        g.setFont(new Font("Monospaced", Font.BOLD, 20));
        g.drawString("COINS = " + coins,450, 60);
    }

}
