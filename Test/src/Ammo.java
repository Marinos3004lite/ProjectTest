import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Ammo extends JPanel{

    int Max_ammo=1000;
    int current_ammo=100;
    int clip=30;

    void reload(){
        if ((current_ammo != 0)) {
            if ((clip >= 0) && (clip < 30)) {
                if (current_ammo > 30) {
                    current_ammo = current_ammo - (30 - clip);
                    clip = 30;
                }
                else{
                    if (clip + current_ammo <= 30) {
                        clip = clip + current_ammo;
                        current_ammo = 0;
                    }
                    else
                    {
                        current_ammo = current_ammo - (30 - clip);
                        clip = 30;
                    }
                }
            }
        }
    }

    public void addAmmo(int x)
    {
        if (Max_ammo > (current_ammo + x))
            current_ammo += x;
        else
            current_ammo = Max_ammo;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        //MapManager.background = new ImageIcon("src/backgrounds\\bkg5.png").getImage();
        //  g.drawImage(MapManager.background, 700 - bk_x, 0, null); // Drawing background
        g.setColor(Color.BLACK);
        g.setFont(new Font("Monospaced", Font.BOLD, 20));
        g.drawString("MAX AMMO = " + Max_ammo,780, 30);
        g.drawString("CURRENT AMMO = " + current_ammo,780, 60);
        g.drawString("CLIP AMMO = " + clip,780, 90);
        if ((clip == 0)&&(current_ammo == 0)){
            g.setColor(Color.red);
        g.setFont(new Font("Monospaced", Font.BOLD, 40));
            g.drawString("NO AMMO",800, 140);
        }
    }
}