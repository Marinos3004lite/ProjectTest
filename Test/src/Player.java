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
    protected Image rz_walk_right2 = new ImageIcon("src/razmazio/player_walk_right.gif").getImage();
    protected Image rz_jump_right = new ImageIcon("src/razmazio\\still_right.png").getImage(); // Jumping
    protected Image rz_jump_left = new ImageIcon("src/razmazio\\still_left.png").getImage(); //

    protected Image obj = rz_still_right; // Temporary Image reference


    //protected int p.x = 500; // character x and y coordinates
    //protected int p.y = 420;
    Sprite p = new Sprite(500,420); /// ??

    static int direction = 0; // 0=still 1=up , 2=right , 3=left , 4=down

    static ArrayList bulletsR;
    static ArrayList bulletsL;
    ArrayList<Sprite> newCoins;
    //static ArrayList<Sprite>  newCoins;
    int start =0;
    public static ArrayList<Sprite> coins;
    MapManager map = new MapManager();
    //Coins coins = new Coins(0,0);
    Health hp = new Health();
    Ammo ammo = new Ammo();
    //Coins coin = new Coins();
    Points point = new Points();
    public int coinscol=0;

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
        p.getImageDimensions();
        setLayout(null);
        time = new Timer(15, this); // starting a timer and passing the
        // actionlistener for the running animation
        time.start(); // starting
        bulletsR = new ArrayList();
        bulletsL = new ArrayList();
        newCoins = getCoins();

        //newCoins = new ArrayList();


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
                    if ((!jump & p.y == 420)||(!jump & p.y == 40)||(!jump & p.y == 165)||(!jump & p.y == 120)) // if character standing of
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
        //checkCollisionsC();

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
            map.back1.x += 15; // increasing xcoord while moving right
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
            map.back1.x -= 15; // decrease xcoord while moving left
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
    public static ArrayList getCoins() {
        return coins;
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
                    {
                        jump = false;
                    }

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
                if (jump & p.y >= -50) // For upward motion during jump
                {
                    System.out.println("fuck offfff ");
                    if (jumpright)
                        p.image = rz_jump_right;
                    else
                        p.image = rz_jump_left;

                    p.y = p.y - 3;   //Speed

                    if (p.y <= -50) {
                        System.out.println("fuck offfff 444 ");
                        jump = false;
                    }
                    System.out.println(jump);
                    if (p.y < 0){
                        map.back1.y = map.back1.y+12;
                    }

                }
                if (!jump & p.y < 165) // For downward motion during jump
                {
                    System.out.println("fuck offfff 2");
                    if (jumpright)
                        p.image = rz_jump_right;
                    else
                        p.image = rz_jump_left;

                    p.y = p.y + 3;       //Speed

                    if (p.y <= 1)
                        map.back1.y = map.back1.y-12;
                }
            }
        }
        else if (flag == 2)
        {
            if (moveableDown) {
                if (jump & p.y >= -50) // For upward motion during jump
                {
                    if (jumpright)
                        p.image = rz_jump_right;
                    else
                        p.image = rz_jump_left;

                    p.y = p.y - 3;   //Speed

                    if (p.y <= -50) {
                        jump = false;
                    }
                    System.out.println(jump);
                    if (p.y < 0){
                        map.back1.y = map.back1.y+12;
                    }

                }
                if (!jump & p.y < 40) // For downward motion during jump
                {
                    if (jumpright)
                        p.image = rz_jump_right;
                    else
                        p.image = rz_jump_left;

                    p.y = p.y + 3;       //Speed

                    if (p.y <= 1)
                        map.back1.y = map.back1.y-12;
                }
            }
        }
        else if (flag == 3)
        {
            if (moveableDown) {
                if (jump & p.y >= -50) // For upward motion during jump
                {
                    if (jumpright)
                        p.image = rz_jump_right;
                    else
                        p.image = rz_jump_left;

                    p.y = p.y - 3;   //Speed

                    if (p.y <= -50) {
                        jump = false;
                    }
                    System.out.println(jump);
                    if (p.y < 0){
                        map.back1.y = map.back1.y+12;
                    }

                }
                if (!jump & p.y < 120) // For downward motion during jump
                {
                    if (jumpright)
                        p.image = rz_jump_right;
                    else
                        p.image = rz_jump_left;

                    p.y = p.y + 3;       //Speed

                    if (p.y <= 1)
                        map.back1.y = map.back1.y-12;
                }
            }
        }
    }
    public void checkCollisions()/// working
    {
        Rectangle pl = p.getBounds();
        //Prwto keno
        Rectangle keno1 = new Rectangle(2970 - map.back1.x,615,60,500);
        if (pl.intersects(keno1)){
            p.y = p.y + 10;   //Speed
            hp.hp=0;
            System.out.println("hello there ");
        }
        //Deftero keno
        Rectangle keno2 = new Rectangle(6665 - map.back1.x,615,1375,500);
        if (pl.intersects(keno2)){
            p.y = p.y + 10;   //Speed
            hp.hp=0;
        }
        // prin prwto platform gia land
        Rectangle prin1 = new Rectangle(1900 - map.back1.x, 230 ,150,150);
        if (pl.intersects(prin1)){
            if (flag== 2)
            {
                flag=0;
            }
        }
        // prin deftero platform gia land
        Rectangle meta1 = new Rectangle(2540 - map.back1.x, 230 ,150,150);
        if (pl.intersects(meta1)){
            if (flag== 2)
            {
                flag=0;
            }
        }
        // meta deftero platform gia land
        Rectangle meta2 = new Rectangle(2960 - map.back1.x, 230 ,150,150);
        if (pl.intersects(meta2)){
            if (flag== 2)
            {
                flag=0;
            }
        }

        // prwto platform
        Rectangle plat1 = new Rectangle(2130 - map.back1.x, 40 ,340,10);
        if (pl.intersects(plat1)){
            if (flag== 0)
            {
                jump=false;
                p.y = 40;   //Speed
                flag=2;
            }
        }
        // deftero platform
        Rectangle plat2 = new Rectangle(2765 - map.back1.x, 40 ,100,10);
        if (pl.intersects(plat2)){
            if (flag== 0)
            {
                jump=false;
                p.y = 40;   //Speed
                flag=2;
            }
        }
        //katiforo prwto
        for (int w = 85; w >=0 ; w--) {
            Rectangle katif= new Rectangle(6070-(w*3) - map.back1.x, 620 - (w*3),10,10);
            if (pl.intersects(katif)){
                //if (flag== 0)
                {
                    p.y = 410 - (w * 3);   //Speed
                    flag=1;
                }
            }
        }
        //prin keno1
        Rectangle ground1 = new Rectangle(695 - map.back1.x, 620,2150,10);
        if (pl.intersects(ground1)){
            flag=0;
            p.y = 420;  //Speed
            map.back1.y=0;
        }
        // prin apo aniforo gia land
        Rectangle prin2 = new Rectangle(5100 - map.back1.x, 230 ,330,90);
        if (pl.intersects(prin2)){
            if (flag== 1)
            {
                flag=0;
            }
        }
        // meta apo aniforo gia land
        Rectangle meta3 = new Rectangle( 5900 - map.back1.x, 230 ,300,300);
        if (pl.intersects(meta3)){
            if (flag== 1)
            {
                flag=0;
            }
        }
        int temp;
        //prin apo aniforo
        Rectangle ground11 = new Rectangle(3160 - map.back1.x, 620,2150,10);
        if (pl.intersects(ground11)){
            flag=0;
            p.y = 420;  //Speed
            map.back1.y=0;
            temp=0;
        }
        else
            temp=1;
        //panw sto aniforo
        Rectangle flat = new Rectangle(5570 - map.back1.x, 365,245,10);
        if (pl.intersects(flat)){
            jump=false;
            p.y = 165;  //Speed
            flag=1;
            temp=0;
        }
        if (temp ==1)
        {
            //Aniforo prwto
            for (int w = 0; w < 85 ; w++) {
                Rectangle anif = new Rectangle(5300 + (w * 3) - map.back1.x, 620 - (w * 3), 10, 10);
                if (pl.intersects(anif)){
                    //if (flag== 0)
                    {
                        p.y = 460 - (w * 3);   //Speed
                        flag=1;
                    }
                }
            }
        }
        //meta apo aniforo
        Rectangle ground2 = new Rectangle(6070 - map.back1.x, 620,500,10);
        if (pl.intersects(ground2)){
            flag=0;
            p.y = 420;  //Speed
            map.back1.y=0;
        }
        // trite platform
        Rectangle plat3 = new Rectangle(6750 - map.back1.x, 120 ,600,10);
        if (pl.intersects(plat3)){
            if (flag== 0)
            {
                jump=false;
                p.y = 120;   //Speed
                flag=3;
            }
        }
        // prin trito  platform gia land
        Rectangle prin3 = new Rectangle(6500 - map.back1.x, 230 ,150,150);
        if (pl.intersects(prin3)){
            if (flag== 3)
            {
                flag=0;
            }
        }

    }

    public void setCoin() {
    coins = new ArrayList<>();
    int x=2160;
    int k= 4000;
    for (int i = 0; i < 3; i++) {
        Coins cn = new Coins(x - map.back1.x, 40);
        coins.add(cn.coin);
        x = x + 100;
    }
    for (int i = 3; i < 6; i++) {
        Coins cn = new Coins(k - map.back1.x, 420);
        coins.add(cn.coin);
        k = k + 100;
    }
}// end

    public void CheckCoin() {
    coins = new ArrayList<>();
    int x=2160;
    int k= 4000;
    for (int i=0;i < 3;i++ ) {
        Coins cn = new Coins(x - map.back1.x, 40);
        coins.add(cn.coin);
        if (!(newCoins.get(i).isVisible()))
            cn.coin.setVisible(false);
        x = x + 100;
    }
    for (int i = 3; i < 6; i++) {
        Coins cn = new Coins(k - map.back1.x, 420);
        coins.add(cn.coin);
        if (!(newCoins.get(i).isVisible()))
            cn.coin.setVisible(false);
        k = k + 100;
    }
}// end

    public void checkCollisionsC(int i)
    {

        Rectangle pl = p.getBounds();
            if(newCoins.get(i).isVisible())
            {
                if (pl.intersects(newCoins.get(i).getBounds()))
                {
                    //System.out.println("OH hi Mark! ");
                    newCoins.get(i).setVisible(false);
                    start=1;
                    coinscol++;
                }

            }
            //else System.out.println("OH BYE Mark! ");
    }
}

