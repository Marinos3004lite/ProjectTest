import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Player extends JPanel implements ActionListener {
    private Thread r, l, j, a;

    protected Image rz_still_right = new ImageIcon("src/razmazio\\still_right.gif").getImage(); // Standing still
    protected Image rz_still_left = new ImageIcon("src/razmazio\\still_left.gif").getImage(); // Walking left
    protected Image rz_walk_left2 = new ImageIcon("src/razmazio\\player_walk_left.gif").getImage(); //
    protected Image rz_jump_right = new ImageIcon("src/razmazio\\still_right.png").getImage(); // Jumping
    protected Image rz_jump_left = new ImageIcon("src/razmazio\\still_left.png").getImage(); //
    protected Image rz_walk_right2 = new ImageIcon("src/razmazio/player_walk_right.gif").getImage();

    protected Image obj = rz_still_right; // Temporary Image reference


    //protected int p.x = 500; // character x and y coordinates
    ///protected int p.y = 420;
    Sprite p = new Sprite(500,420); /// ??

    static int direction = 0; // 0=still 1=up , 2=right , 3=left , 4=down

    static ArrayList bulletsR;
    static ArrayList bulletsL;

    MapManager map = new MapManager();
    Health hp = new Health();
    Ammo ammo = new Ammo();
    //Coins coin = new Coins();
    Points point = new Points();
    int flag = 0;
    protected static boolean moveableRight = true; // variable for collision detection
    protected static boolean moveableLeft = true;
    protected static boolean moveableDown = false;
    protected boolean jumpright = true;

    protected static boolean jump = false; // For jump
    private Timer time;

    static boolean pause = false;
    private int run = 0;

    /*a = new Thread()
    {
        public void run()
        {
            while (true)
            {
                this.right();
                Thread.sleep(5);
            }
        }
    });*/

    protected Player() {
        p.image = obj;
        setLayout(null);
        time = new Timer(30, this); // starting a timer and passing the
        // actionlistener for the running animation
        time.start(); // starting
        bulletsR = new ArrayList();
        bulletsL = new ArrayList();

        addKeyListener(new KeyAdapter() // Movement
        {
            public void keyPressed(KeyEvent kp) {
                if (kp.getKeyCode() == KeyEvent.VK_D & moveableRight) {
                    direction = 2; // right
                }
                if ((kp.getKeyCode() == KeyEvent.VK_A) & moveableLeft) {
                    direction = 3; // left
                }
                if (p.image == rz_still_right || p.image == rz_walk_right2) {
                    if ((kp.getKeyCode() == KeyEvent.VK_ENTER)) {
                        //fireR();// fire
                        Thread fr = new Thread(() -> {
                            fireR();
                        });
                        fr.start();
                        hp.isDead();
                        if (hp.dead)
                            fr.stop();
                        hp.takeDamage();
                    }
                } else if (p.image == rz_still_left || p.image == rz_walk_left2) {
                    if ((kp.getKeyCode() == KeyEvent.VK_ENTER)) {
                        // fireL();// fire
                        Thread fl = new Thread(() -> {
                            fireL();
                        });
                        fl.start();
                        if (hp.dead)
                            fl.stop();
                        hp.takeDamage();
                    }
                }

                if ((kp.getKeyCode() == KeyEvent.VK_R) & moveableLeft) {
                    ammo.reload();// left
                    //coin.Sub_Coins(10);
                    point.Sub_Points(10);
                }

                if ((kp.getKeyCode() == KeyEvent.VK_E) & moveableLeft) {
                    ammo.addAmmo(10);
                }
                if (kp.getKeyCode() == KeyEvent.VK_SPACE)
                {
                    if ((!jump & p.y == 420)||(!jump & p.y == 40)) // if character standing of
                    // platform
                    {
                        jump = true;
                        moveableDown = true;
                        if (direction == 2)
                            jumpright = true;
                        if (direction == 3)
                            jumpright = false;
                    }

                }
            } // end keyPressed

            public void keyReleased(KeyEvent kr) {
                if (direction == 2)
                    p.image = rz_still_right; // if direction is right
                if (direction == 3)
                    p.image = rz_still_left; // if direction is left

                direction = 0; // set still image
            }
        });// end anonymous class and KeyListener
    }// end constructor

    public void actionPerformed(ActionEvent e) {

        //System.out.println(map.back1.getBounds().x);
        //System.out.println(p.getBounds().y);
        //System.out.println(flag);
        checkCollisions();
        checkCollisionsC();

        Thread r = new Thread(() -> {
            this.right();
        });
        Thread l = new Thread(() -> {
            this.left();
        });

        if (hp.isDead())
            direction = 2;
        if (direction == 2)
            r.start();
        if (direction == 3)
            l.start();
        hp.isDead();
        if (hp.dead) {
            r.stop();
            l.stop();
        }

        ArrayList bullets = Player.getBulletsR();
        ArrayList bulletss = Player.getBulletsL();
        Bullet m;
        Bullet m2;

        if (p.image == rz_still_right || p.image == rz_walk_right2) {
            for (int w = 0; w < bullets.size(); w++) {
                m = (Bullet) bullets.get(w);
                if (m.getVisible()) {
                    m.moveR();
                } else
                    bullets.remove(w);
            }
        } else if (p.image == rz_still_left || p.image == rz_walk_left2) {
            for (int x = 0; x < bulletss.size(); x++) {
                m2 = (Bullet) bulletss.get(x);
                if (m2.getVisible()) {
                    m2.moveL();
                } else
                    bulletss.remove(x);
            }
        }

        for (int w = 0; w < bulletss.size(); w++) {
            m2 = (Bullet) bulletss.get(w);
            if (m2.getMovementL())
                m2.moveL();
            else
                bulletss.remove(w);
        }
        for (int w = 0; w < bullets.size(); w++) {
            m = (Bullet) bullets.get(w);
            if (m.getMovementR())
                m.moveR();
            else
                bullets.remove(w);
        }
        // repaint(); //repaint after 30ms
    }

    private void right() {
        if (moveableRight & map.back1.x < map.BKMAX_X - 800) {
            map.back1.x += 8; // increasing xcoord while moving right
            map.back2.x += 3;
            map.back3.x += 1;

            if (run % 3 == 0 | run % 5 == 0)
                p.image = rz_still_right;
            else
                p.image = rz_walk_right2;
            run++;
        }// end if
    }// end right

    private void left() {
        if (moveableLeft & map.back1.x > map.BKMIN_X) {
            map.back1.x -= 8; // decrease xcoord while moving left
            map.back2.x -= 3;
            map.back3.x -= 1;

            if (run % 3 == 0 | run % 5 == 0)
                p.image = rz_still_left; // set image
            else
                p.image = rz_walk_left2;
            run++;
        }// end if
    }// end lefts

    public void fireR() {
        if (ammo.clip != 0) {
            Bullet bR = new Bullet(p.x + 78, p.y + 85);
            ammo.clip--;
            bulletsR.add(bR);
            //coin.Add_Coins(10);
            point.Add_Points(10);
        }
    }

    public void fireL() {
        if (ammo.clip != 0) {
            Bullet bL = new Bullet(p.x - 28, p.y + 85);
            ammo.clip--;
            bulletsL.add(bL);
        }
    }

    public static ArrayList getBulletsR() {
        return bulletsR;
    }

    public static ArrayList getBulletsL() {
        return bulletsL;
    }

    protected void Jump() // Jump mechanism
    {
        if (flag == 0)
        {
            if (moveableDown) {
                if (jump & p.y >= -10) // For upward motion during jump
                {
                    if (jumpright)
                        p.image = rz_jump_right;
                    else
                        p.image = rz_jump_left;

                    p.y = p.y - 5;   //Speed
                    if (p.y <= -10)
                        jump = false;

                    /*if (p.y < 50 && flag == 0) {
                        map.back1.y += 3;
                    }*/
                    //else if(p.y < 100)

                }
                if (!jump & p.y < 420) // For downward motion during jump
                {
                    if (jumpright)
                        p.image = rz_jump_right;
                    else
                        p.image = rz_jump_left;

                    p.y = p.y + 5;       //Speed

                    /*if (p.y <= 50 && flag == 0)
                        map.back1.y -= 3;*/
                }
            }
        }
        else if (flag == 1)
        {
            if (moveableDown) {
                if (jump & p.y >= -600) // For upward motion during jump
                {
                    System.out.println("fuck offfff ");
                    if (jumpright)
                        p.image = rz_jump_right;
                    else
                        p.image = rz_jump_left;

                    p.y = p.y - 1;   //Speed

                    if (p.y >= -600) {
                        System.out.println("fuck offfff 444 ");
                        jump = false;
                    }
                    System.out.println(jump);
                    if (p.y < -200){
                        map.back1.y += 3;
                    }

                }
                if (!jump & p.y < 40) // For downward motion during jump
                {
                    System.out.println("fuck offfff 2");
                    if (jumpright)
                        p.image = rz_jump_right;
                    else
                        p.image = rz_jump_left;

                    p.y = p.y + 1;       //Speed

                    if (p.y <= -200)
                        map.back1.y -= 3;
                }
            }
        }
    }
    public void checkCollisions()/// working
    {
        //Prwto keno
        /*if (map.back1.getBounds().x>=2375 &&map.back1.getBounds().x<=2540&&p.getBounds().y>= 420) {
            p.y = p.y + 10;   //Speed
            hp.hp=0;
        }*/
        //Aniforo prwto
         if (map.back1.getBounds().x >= 4750 && map.back1.getBounds().x <= 5050)
        {
            //p.y = map.back1.getBounds().y;   //Speed
            //p.x += ;
            map.back1.y=0;
            System.out.println("hola");
            jump = false;
        }
        //Panw sto aniforo
        else if (map.back1.getBounds().x > 5050 && map.back1.getBounds().x <= 5270){//&&p.getBounds().y>= 420) {
            p.y = 160 ;   //Speed
            flag = 1;
        }
        //Deftero keno
        else if (map.back1.getBounds().x>=6050 &&map.back1.getBounds().x<=7560&&p.getBounds().y>= 420) {
            p.y = p.y + 10;   //Speed
            hp.hp=0;
        }
        // prwto platform
        else if (map.back1.getBounds().x>=1550 &&map.back1.getBounds().x<=1950&&p.getBounds().y <= 40) {
            p.y = 40;   //Speed
            flag = 1;
            //jump = false;
        }
        // deftero platform
        else if (map.back1.getBounds().x>=2210 &&map.back1.getBounds().x<=2359&&p.getBounds().y <= 40) {
            p.y = 40;   //Speed
            flag = 1;
        }
        // trito platform
        else if (map.back1.getBounds().x>=6190 &&map.back1.getBounds().x<=6850&&p.getBounds().y <= 120) {
            p.y = 120;   //Speed
            flag = 1;
        }
        // tetarto platform
        else if (map.back1.getBounds().x>=6985 &&map.back1.getBounds().x<=7250&&p.getBounds().y <= -10) {
            p.y = -10;   //Speed
            flag = 1;
        }
        else if (map.back1.getBounds().x>=7725 &&map.back1.getBounds().x<=7815&&p.getBounds().y <= -10) {
            hp.hp=0;
        }
        // normal ground
        else
        {
            flag =0;
            //map.back1.y=0;
        }
    }

    public void checkCollisionsC()
    {
        Rectangle r = map.coins.coin.getBounds();
        Rectangle pl = p.getBounds();
        System.out.println(p.getBounds());
        if (pl.intersects(r))
            System.out.print("Hi");
    }
}
/* /*public void checkCollisions() {

        Rectangle r3 = coin.getBounds();
        Runnable r2 = rz_walk_left2.getWidth();

        if (r3.intersects(r2)) {

            m.setVisible(false);
            fire.setVisible(false);
        }
    }


        for (bulletsL f : fires) {

            Rectangle r2 = f.getBounds();

            if (r3.intersects(r2)) {

                drone.setVisible(false);
                f.setVisible(false);
                ingame = false;
            }
        }

        java.util.List<Missile> ms = drone.getMissiles();

        for (Missile m : ms) {

            Rectangle r1 = m.getBounds();

            for (Fire fire : fires) {

                Rectangle r2 = fire.getBounds();

                if (r1.intersects(r2)) {

                    m.setVisible(false);
                    fire.setVisible(false);
                }
            }
        }
    }*/

