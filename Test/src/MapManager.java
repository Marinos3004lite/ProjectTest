import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MapManager
{
    Sprite back1 = new Sprite(695,0);
    Sprite back2 = new Sprite(695,535);
    Sprite back3 = new Sprite(695,535);
    Sprite back4 = new Sprite(695,0);
    final public int BKMIN_X = 1000, BKMAX_X = 10000; // Min and Max of
    //Coins coins = new Coins(0,0);



    public MapManager()
    {
        back1.loadImage("src/backgrounds\\bkg5.png");
        back2.loadImage("src/backgrounds\\rear_bg1.png");
        back3.loadImage("src/backgrounds\\rear_bg2.png");
        back4.loadImage("src/backgrounds\\level_back.png");
    }

    public void setBackground(Graphics g2d) {
        g2d.drawImage(back3.image, 700 - back3.x, -75, null); // Drawing background
        g2d.drawImage(back2.image, 700 - back2.x, 0, null); // Drawing background
        g2d.drawImage(back1.image, 700 - back1.x, back1.y, null); // Drawing background
    }


}
