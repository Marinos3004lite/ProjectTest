import javax.swing.*;
import java.awt.*;

public class Health extends  JPanel{

    int hp = 300;
    public boolean win = false;
    public boolean dead =false;

    public void takeDamage()
    {
        if (dead == true)
            hp=0;
        else
            hp = hp - 10;
    }

    public void giveDamage()
    {

    }

    public boolean isDead()
    {
        if (hp<=0)
            dead = true;
        return dead;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g.setColor(Color.BLACK);
        g.setFont(new Font("Monospaced", Font.BOLD, 20));
        g.drawString(" HEALTH ", 20, 30);
        if (hp > 150) {
            g2d.setColor(Color.GREEN);
            g2d.fillRect(30, 50, hp, 20);
            g2d.setColor(Color.WHITE);
            g.drawString("" + (hp / 3), 50, 66);
        } else if (hp > 30) {
            g2d.setColor(Color.orange);
            g2d.fillRect(30, 50, hp, 20);
            g2d.setColor(Color.BLACK);
            g.drawString("" + (hp / 3), 50, 66);
        } else {
            g2d.setColor(Color.red);
            g2d.fillRect(30, 50, hp, 20);
            g2d.setColor(Color.BLACK);
            g.drawString("" + (hp / 3), 50, 66);
        }
        g.setFont(new Font("Monospaced", Font.BOLD, 70));
        g2d.setColor(Color.RED);
        if (hp <= 0) {

            g2d.setColor(Color.black);
            g2d.fillRect(0, 0, 1024, 750);
            g2d.setColor(Color.red);
            g.drawString("GAME OVER", 350, 200);
            g.drawString(":(", 490, 350);
            // create replay

            //Razmazio d = new Razmazio();
            //kati.panel.setVisible(true);
            //hp=300;
        }
        if (win)
        {
            Image firework = new ImageIcon("src/razmazio\\firework.gif").getImage();
            g.drawImage(firework,0,0,null);
            g.drawString("WINNER WINNER NO DINNER",20, 120);
        }
    }
}

