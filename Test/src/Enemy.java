import java.awt.*;

public class Enemy extends Player{

    protected Sprite  helip, tankip,zombik;
    int movement2=0,movement = 0;
    protected int posY3 = 350, posY2 = 450, posY = 100,
            posX3 = 3500, posX2 = 2100,posX = 2000, SpeedX = 2,
            Speed2 = 1,Speed = 2,Speed3 = 3;
    //protected double y;
    public int tankHp = 100;
    public int zombieHp = 100;
    public int heliHp = 100;

    public Enemy() {
        initPhoto3(posX3, posY3);
        initPhoto2(posX2, posY2);
        initPhoto(posX, posY);
    }

    private void initPhoto (int x, int y) {
        helip = new Sprite(x ,y);
        helip.loadImage("src/razmazio/EnemyHelicopter.png");
        helip.getImageDimensions();
        helip.visible = true;
    }

    private void initPhoto2(int x, int y) {
        tankip = new Sprite(x, y);
        tankip.loadImage("src/razmazio/tanky.gif");
        tankip.getImageDimensions();
        tankip.visible = true;
    }
    private void initPhoto3(int x, int y) {
        zombik = new Sprite(x, y);
        zombik.loadImage("src/razmazio/zombie.gif");
        zombik.getImageDimensions();
        zombik.visible = true;
    }

    /*public void helimove(Graphics2D g2d) {
        //System.out.println("PosX" + posX + " - PosY: " + posY);
        if (movement == 0) {
            if (posY < 350) {
                posX -= Speed;
                posY += Speed;
                g2d.drawImage(helip, posX, posY, null);
            } else {
                movement = 1;
            }
        } else {
            posX -= Speed;
            posY -= Speed;
            g2d.drawImage(helip, posX, posY, null);
            if ((posY < 50)) {
                movement = 0;
            }
        }
    }*/

    public void helimove2(Graphics2D g2d,int mapx,int mapy) {
        if (helip.isVisible()) {
            g2d.drawImage(helip.image, helip.x - mapx, mapy+helip.y, null);

            int UPDATE_RATE = 0;
            // Start the ball bouncing (in its own thread)
            Thread gameThread = new Thread() {
                public void run() {
                    try {
                        while (true) { // Execute one update step
                            // Calculate the ball's new position
                            helip.x += SpeedX;
                            helip.y += Speed;
                            // Check if the ball moves over the bounds
                            // If so, adjust the position and speed.
                            if (helip.x - helip.width < 0) {
                                SpeedX = -SpeedX; // Reflect along normal
                                helip.x = helip.width; // Re-position the ball at the edge
                            } else if (helip.x + helip.width > 1024) {
                                SpeedX = -SpeedX;
                                helip.x = 1024 - helip.width;
                            }
                            // May cross both x and y bounds
                            if (helip.y - helip.height < 0) {
                                Speed = -Speed;
                                helip.y = helip.height;
                            } else if (helip.y + helip.height > 350) {
                                Speed = -Speed;
                                helip.y = 350 - helip.height;
                            }
                            // Refresh the display
                            repaint(); // Callback paintComponent()
                            // Delay for timing control and give other threads a chance
                            try {
                                Thread.sleep(1000 / UPDATE_RATE);  // milliseconds
                            } catch (InterruptedException ex) {
                            }
                        }
                    } catch (ArithmeticException ex) {
                    }
                }

                private void repaint() {
                }
            };

            gameThread.start();  // Callback run()
        }
    }

    public void tankmove(Graphics2D g2d,int mapx,int mapy) {
        if (tankip.isVisible())
        {
            g2d.drawImage(tankip.image, tankip.x - mapx, mapy + tankip.y, null);
            if (movement2 == 0) {
                if (tankip.x  > 1500) {
                    tankip.x -= Speed2 * 5;
                    //g2d.drawImage(tankip.image, tankip.x - map.back1.x,tankip.y, null);
                }else {
                        movement2 = 1;
                }
            } else {
                tankip.x += Speed2 * 5;
                //g2d.drawImage(tankip.image, tankip.x - map.back1.x, tankip.y, null);
                if ((tankip.x  > 2700)) {
                    movement2 = 0;
                }
            }
        }
    }


    public void zombiemove(Graphics2D g2d,int mapx,int mapy) {
        if (zombik.isVisible()) {
            g2d.drawImage(zombik.image, zombik.x - mapx, mapy + zombik.y, null);
            if (movement == 0) {
                if (zombik.x > 20) {
                    zombik.x -= Speed3;
                    //g2d.drawImage(zombik.image, posX3 - map.back1.x, posY3, null);
                } else {
                    movement = 1;
                }
            } else {
                zombik.x += Speed3;

                //g2d.drawImage(zombik.image, posX3 - map.back1.x, posY3, null);
                if ((zombik.x > 840)) {
                    movement = 0;
                }
            }
        }
    }

    public void giveDmg()
    {
        hp.hp = hp.hp - 10;
    }

    public void takeDmgT()
    {
        tankHp -= 10;
    }
    public void takeDmgZ()
    {
        zombieHp -= 10;
    }
    public void takeDmgH()
    {
        heliHp -= 10;
    }

    public void checkEnemyLife()
    {
        if (tankHp<=0)
            tankip.setVisible(false);
        if (zombieHp<=0)
            zombik.setVisible(false);
        if (heliHp<=0)
            helip.setVisible(false);
    }
}










