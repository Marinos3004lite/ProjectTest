import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Time;
import java.util.ArrayList;

public class GamePanel extends Player
{
        Enemy enemy = new Enemy();

        Tank tank1 = new Tank(62 - map.back1.x, 450);

        public void colwithEnemy()
        {
            Rectangle pl = p.getBounds();
            //Rectangle tank  = tank1.tankip.getBounds();
            Rectangle tank  = new Rectangle(enemy.tankip.x - map.back1.x, enemy.tankip.y, enemy.tankip.width,
                    enemy.tankip.height);

            if (pl.intersects(tank)){
                enemy.giveDmg();
                hp.takeDamage();
            System.out.println("hi");
            }
        }

        public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        requestFocus(); // get focus after changing card
        setFocusable(true);
        //test.setBackground(g);
        // setting background points and cash in the game
        map.setBackground(g2d);
        ammo.paintComponent(g2d);

        //enemy.helimove2(g2d
        enemy.tankmove(g2d);

        point.paintComponent(g2d);
        ///map.sprite1.setVisible(false);
        if (start == 0)
        {
            setCoin();
            newCoins = getCoins();
        }
        if (start == 1)
        {
            CheckCoin();
            newCoins = getCoins();
        }
        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("Monospaced", Font.BOLD, 20));
        g2d.drawString("COINS = " + coinscol,450, 60);

        // checking jump collision and enemy death
        Thread j = new Thread(() -> {
            Jump();
        });
        j.start();

        hp.isDead();
        if (hp.dead||hp.win)
            j.stop();

        if (((p.y == 420)||(p.y == 40)||(p.y == 165)||(p.y == 120)||(p.y == 0)) & direction != 3 & direction != 2) // to turn player
        // in normal still
        // state after jump
        {
            if (p.image == rz_jump_left)
                p.image = rz_still_left;
            if (p.image == rz_jump_right)
                p.image = rz_still_right;
        }
        int x =2160;
        int k = 4000;
        for (int w = 0; w < newCoins.size(); w++)
        {
            checkCollisionsC(w);
            if (newCoins.get(w).visible)
                g2d.drawImage(coins.get(w).image, newCoins.get(w).x, newCoins.get(w).y, null); // Drawing background
            x = x + 100;
            k = k + 100;
            //System.out.println(map.coins.get(w).coin.isVisible());
        }

        /*g2d.drawImage(tank1.tankip.image, tank1.tankip.x - map.back1.x, tank1.tankip.y, null);
        if (tank1.movement2 == 0 && tank1.tankip.x - map.back1.x < 500) {
            if (tank1.tankip.x > 20 ) {
                    tank1.tankip.x -= tank1.Speed2;
                    g2d.drawImage(tank1.tankip.image, tank1.tankip.x - map.back1.x,tank1.tankip.y, null);
            } else {
                tank1.movement2 = 1;
            }
        } else {
            tank1.tankip.x += tank1.Speed2;
                g2d.drawImage(tank1.tankip.image, tank1.tankip.x - map.back1.x, tank1.tankip.y, null);
                if ((tank1.tankip.x > 840)) {
                    tank1.movement2 = 0;
                }
        }*/

        g2d.drawImage(p.image, p.x, p.y, this); // Drawing the character image
        g2d.drawImage(map.back4.image, 700 - map.back1.x, map.back1.y, null); // Drawing background
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
        // TESTING
        /*Rectangle pl = p.getBounds();
        //g2d.fill(pl);
        g2d.setColor(Color.orange);
        //g2d.fillRect(2970 - map.back1.x,615,60,500); // keno1
        //g2d.fillRect(6665 - map.back1.x,615,1375,500); // keno2
        g2d.fillRect(2130 - map.back1.x, 40 ,340,10); // plat1
        for (int w = 0; w < 85 ; w++)
            g2d.fillRect(5300 + (w * 3) - map.back1.x, 620 - (w * 3), 10, 10);
        g2d.fillRect(5570 - map.back1.x, 365,245,10);
        for (int w = 85; w >=0 ; w--)
            g2d.fillRect(6070-(w*3) - map.back1.x, 620 - (w*3),10,10);
        g2d.fillRect(6070 - map.back1.x, 620,500,10);
        g2d.fillRect(695 - map.back1.x, 620,2150,10);
        g2d.fillRect(3160 - map.back1.x, 620,2150,10);

        g2d.fillRect(1900 - map.back1.x, 230 ,150,150); // prin1
        g2d.fillRect(2540 - map.back1.x, 230 ,150,150); // meta1
        g2d.fillRect(2970 - map.back1.x, 230 ,150,150);
        g2d.fillRect(2765 - map.back1.x, 40 ,100,10); //plat2
        g2d.fillRect(5100 - map.back1.x, 230 ,330,90);
        g2d.fillRect(5900 - map.back1.x, 230 ,320,300);
        g2d.fillRect(6750 - map.back1.x, 120 ,630,10);//plat3
        g2d.fillRect(6500 - map.back1.x, 120 ,150,200);
        g2d.fillRect(7450 - map.back1.x, 300 ,1100,100);
        g2d.fillRect(7565 - map.back1.x, 0 ,230,10);//plat4
        g2d.fillRect(7450 - map.back1.x, 20 ,20,20);
        g2d.fillRect(7840 - map.back1.x, 190 ,700,50);
        g2d.fillRect(8125 - map.back1.x, 620,250,10);
        g2d.fillRect(8425 - map.back1.x, 620,800,500);*/

        //Rectangle tank  = enemy.tankip.getBounds();Rectangle tank  = new Rectangle(enemy.tankip.x - map.back1.x, enemy.tankip.y, enemy.tankip.width,
         //           enemy.tankip.height);
        //System.out.println(tank);
        //g2d.fill(tank);
        //g2d.fill(pl);

        //System.out.print("x = "+ (enemy.tankip.x));
        //System.out.println(" x2 = "+ (enemy.tankip.x - map.back1.x));
        colwithEnemy();
        checkCollisions();
        repaint();
    } // end paintComponent
}// end class

