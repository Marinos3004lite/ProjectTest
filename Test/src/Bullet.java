import javax.swing.*;
import java.awt.*;
import java.lang.*;
import java.util.ArrayList;

public class Bullet
{
    PointerInfo a = MouseInfo.getPointerInfo();
    Point b = a.getLocation();
    double m_x = b.getX();
    double m_y = b.getY();
    private double x, y;
    private Image imgR;
    private Image imgL;
    private boolean visible;
    private boolean movementR;
    private boolean movementL;

    public Bullet(double startX, double startY)
    {
        this.x = startX;
        this.y = startY;
        ImageIcon newBulletR = new ImageIcon("src/bulletR.png");
        ImageIcon newBulletL = new ImageIcon("src/bulletL.png");
        imgR = newBulletR.getImage();
        imgL = newBulletL.getImage();
        visible = true;
        movementR = false;
        movementL = false;

    }

    public double getX()
    {
        return x;
    }

    public double getY()
    {
        return y;
    }

    public Image getImageR()
    {
        return imgR;
    }

    public Image getImageL()
    {
        return imgL;
    }

    public boolean getVisible()
    {
        return visible;
    }

    public boolean getMovementR()
    {
        return movementR;
    }

    public boolean getMovementL()
    {
        return movementL;
    }

    public void moveR()
    {
        if (!movementR && !movementL)
            movementR = true;
        if (movementR)
            x = x + 15;
        if (x > 1280)
        {
            visible = false;
            movementR = false;
        }
    }

    public void moveL()
    {
        if (!movementR && !movementL)
            movementL=true;
        if (movementL)
            x = x - 15;
        if (x  < 0)
        {
            visible = false;
            movementL = false;
        }

    }

    public void ShootBullet()
    {

    }


        /*double bulletVelocity = 5.0; //however fast you want your bullet to travel
        //mouseX/Y = current x/y location of the mouse
        //originX/Y = x/y location of where the bullet is being shot from
        double dx = m_x - x;
        double dy = m_y - y ;
        double angle = Math.atan2(dy, dx);
        double xVelocity = (bulletVelocity) * Math.cos(angle);
        double yVelocity = (bulletVelocity) * Math.sin(angle);

        double n_x = xVelocity * 2;
        double n_y = yVelocity * 2;

        x += n_x;  // movement in x-direction after dt time w/ constant velocity
        y += n_y;*/

       /* if (y != m_y )
            y += n_y ;
        if (y != m_y && y < m_y)
            y = y + 10;
        if (x != m_x && x < m_x)
            x = x + 10;
        if (x != m_x)
            x += n_x;*/

    //x = xVelocity;
    //y = yVelocity;


    //System.out.println(yVelocity);

       /* if (y != m_y )
            y += n_y ;
        if (y != m_y && y < m_y)
            y = y + 10;
        if (x != m_x && x < m_x)
            x = x + 10;
        if (x != m_x)
            x += n_x;*/

    //x = xVelocity;
    //y = yVelocity;


    //System.out.println(yVelocity);
}
