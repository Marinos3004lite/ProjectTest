import javax.swing.*;
import java.awt.*;

public class MapManager extends JPanel
{
    public Image background;
    public int bk_x = 695; // background x and y coordinates
    public int bk_y = 0;
    private Image background2;
    public int bk2_x = 695; // background x and y coordinates
    public int bk2_y = 535;
    private Image background3;
    public int bk3_x = 695; // background x and y coordinates
    public int bk3_y = 535;

    final public int BKMIN_X = 1000, BKMAX_X = 10000; // Min and Max of

    //Coins coins = new Coins();

    public MapManager()
    {
        background = new ImageIcon("src/backgrounds\\bkg5.png").getImage();
        background2 = new ImageIcon("src/backgrounds\\rear_bg1.png").getImage();
        background3 = new ImageIcon("src/backgrounds\\rear_bg2.png").getImage();
    }

    public void setBackground(Graphics g2d) {
        g2d.drawImage(background3, 700 - bk3_x, -75, null); // Drawing background
        g2d.drawImage(background2, 700 - bk2_x, 0, null); // Drawing background
        g2d.drawImage(background, 700 - bk_x, bk_y, null); // Drawing background
        // relative to
        // character
    }

    /*public void setCoin(Graphics g2d) {
        g2d.drawImage(coins.coin, 4000 - bk_x, 10, null); // Drawing background
        g2d.drawImage(coins.coin, 4150 - bk_x, 10, null); // Drawing background
        g2d.drawImage(coins.coin, 4300 - bk_x, 10, null); // Drawing background
    }// end*/
}
