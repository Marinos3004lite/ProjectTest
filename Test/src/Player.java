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
        p.image=(obj);


        setLayout(null);
        time = new Timer(30, this); // starting a timer and passing the
        // actionlistener for the running animation
        time.start(); // starting
        bulletsR = new ArrayList();
        bulletsL = new ArrayList();

        addKeyListener(new KeyAdapter() // Movement
        {
            public void keyPressed(KeyEvent kp) {
                //System.out.println(kp);
                if (kp.getKeyCode() == KeyEvent.VK_D & moveableRight) {
                    direction = 2; // right
                }
                if ((kp.getKeyCode() == KeyEvent.VK_A) & moveableLeft) {
                    direction = 3; // left
                }
                    /*if ((kp.getKeyCode() == KeyEvent.VK_RIGHT & moveableRight)&& (kp.getKeyCode() == KeyEvent.VK_F)) {
                        direction = 2; // right
                        fire();// fire
                        hp.takeDamage();
                    }
                    if (((kp.getKeyCode() == KeyEvent.VK_LEFT) & moveableLeft) && (kp.getKeyCode() == KeyEvent.VK_F)) {
                        direction = 3; // right
                        fire();// fire
                        hp.takeDamage();
                    }*/
                    /*if ((kp.getKeyCode() == KeyEvent.VK_F)) {
                        fireL();// fire
                        hp.takeDamage();
                    }*/

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
                if (kp.getKeyCode() == KeyEvent.VK_SPACE) {
                    if (!jump & p.y == 420) // if character standing of
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

        System.out.println(map.back1.getBounds().x);
        //System.out.println(p.getBounds().y);
        checkCollisions();

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
        if (moveableDown) {
            if (jump & p.y >= 50) // For upward motion during jump
            {
                if (jumpright)
                    p.image = rz_jump_right;
                else
                    p.image = rz_jump_left;

                p.y = p.y - 5;   //Speed
                if (p.y <= 50)
                    jump = false;

                if (p.y < 150) {
                    map.back1.y += 2;
                }
                //else if(p.y < 100)

            }
            if (!jump & p.y < 420) // For downward motion during jump
            {
                if (jumpright)
                    p.image = rz_jump_right;
                else
                    p.image = rz_jump_left;

                p.y = p.y + 5;       //Speed

                if (p.y <= 150)
                    map.back1.y -= 2;
            }
        }
    }
    public void checkCollisions()/// working
    {
        //Prwto keno
        if (map.back1.getBounds().x>=2375 &&map.back1.getBounds().x<=2540&&p.getBounds().y>= 420) {
            p.y = p.y + 10;   //Speed
            hp.hp=0;
            System.out.println("hola");
        }
        //Aniforo prwto
        if (map.back1.getBounds().x >= 4750 && map.back1.getBounds().x <= 5050)
        {
            //p.y = map.back1.getBounds().y;   //Speed
            addKeyListener(new KeyAdapter() // Movement
                           {
                               public void keyPressed(KeyEvent kp) {
                                   //System.out.println(kp);
                                   if (kp.getKeyCode() == KeyEvent.VK_D & moveableRight) {
                                       //direction = 2; // right
                                       p.y -= 10;
                                   }
                                   if ((kp.getKeyCode() == KeyEvent.VK_A) & moveableLeft) {
                                       //direction = 3; // left
                                   }
                               }
                           });

            //p.x += ;
            map.back1.y=0;
            System.out.println("hola");
            jump = false;
        }
        //Panw sto aniforo
        if (map.back1.getBounds().x > 5050 && map.back1.getBounds().x <= 5270){//&&p.getBounds().y>= 420) {
            p.y = 160 ;   //Speed
            System.out.println("hola");
            jump=false;
        }
        //Deftero keno
        if (map.back1.getBounds().x>=6085 &&map.back1.getBounds().x<=7560&&p.getBounds().y>= 420) {
            p.y = p.y + 10;   //Speed
            hp.hp=0;
            System.out.println("hola");
        }
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

