import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class GamePanel extends Player
{
    //MapManager map = new MapManager();


    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        requestFocus(); // get focus after changing card
        setFocusable(true);

        // setting background points and cash in the game
        map.setBackground(g2d);

        ammo.paintComponent(g2d);
        coin.paintComponent(g2d);
        point.paintComponent(g2d);
        // checking jump collision and enemy death

        Thread j = new Thread(() -> {
            Jump();
        });
        j.start();
        hp.isDead();
        if (hp.dead)
            j.stop();

        if (rz_y == 420 & direction != 3 & direction != 2) // to turn player
        // in normal still
        // state after jump
        {
            if (obj == rz_jump_left)
                obj = rz_still_left;
            if (obj == rz_jump_right)
                obj = rz_still_right;
        }

        g2d.drawImage(obj, rz_x, rz_y, this); // Drawing the character image

        hp.paintComponent(g2d);
        ArrayList bulletsL = getBulletsL();

        for (int w = 0; w < bulletsL.size(); w++)
        {
            Bullet m = (Bullet) bulletsL.get(w);
            g2d.drawImage(m.getImageL(), (int) m.getX(), (int) m.getY(), null);
        }
        ArrayList bulletsR = getBulletsR();
        for (int w = 0; w < bulletsR.size(); w++)
        {
            Bullet m = (Bullet) bulletsR.get(w);
            g2d.drawImage(m.getImageR(), (int) m.getX(), (int) m.getY(), null);
        }
        repaint();
    } // end paintComponent
}// end class

