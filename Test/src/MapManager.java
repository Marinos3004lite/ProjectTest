import javax.swing.*;
import java.awt.*;

public class MapManager
{
    Sprite back1 = new Sprite(695,0);
    Sprite back2 = new Sprite(695,535);
    Sprite back3= new Sprite(695,535);
    Sprite back4= new Sprite(695,0);
    /*public Image background;
    public int bk_x = 695; // background x and y coordinates
    public int bk_y = 0;
    private Image background2;
    public int bk2_x = 695; // background x and y coordinates
    public int bk2_y = 535;
    private Image background3;
    public int bk3_x = 695; // background x and y coordinates
    public int bk3_y = 535;*/

    final public int BKMIN_X = 1000, BKMAX_X = 10000; // Min and Max of

    //Coins coins = new Coins();

    public MapManager()
    {
        back1.loadImage("src/backgrounds\\bkg5.png");
        back4.loadImage("src/backgrounds\\level_back.png");
        //background = new ImageIcon("src/backgrounds\\bkg5.png").getImage();
        //back1.getImage()= background;
        ///background=back1.getImage();
        //back1.setVisible(true);

        back2.loadImage("src/backgrounds\\rear_bg1.png");
        back3.loadImage("src/backgrounds\\rear_bg2.png");
        //background2 = new ImageIcon("src/backgrounds\\rear_bg1.png").getImage();
        //background3 = new ImageIcon("src/backgrounds\\rear_bg2.png").getImage();

    }

    public void setBackground(Graphics g2d) {
        g2d.drawImage(back3.image, 700 - back3.x, -75, null); // Drawing background
        g2d.drawImage(back2.image, 700 - back2.x, 0, null); // Drawing background
        g2d.drawImage(back1.image, 700 - back1.x, back1.y, null); // Drawing background
        ///back1.image = background;
        //back1.getBounds();
        //back1.setVisible(false);
        // relative to
        // character
    }

    /*public void setCoin(Graphics g2d) {
        g2d.drawImage(coins.coin, 4000 - bk_x, 10, null); // Drawing background
        g2d.drawImage(coins.coin, 4150 - bk_x, 10, null); // Drawing background
        g2d.drawImage(coins.coin, 4300 - bk_x, 10, null); // Drawing background
    }// end*/
}
