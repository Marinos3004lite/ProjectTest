import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.*;

public class Sprite {

    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected boolean visible;
    protected Image image;
    private boolean movementR;
    private boolean movementL;

    public Sprite(int x, int y) {

        this.x = x;
        this.y = y;
        visible = true;
    }

    protected void getImageDimensions() {

        width = image.getWidth(null);
        height = image.getHeight(null);
    }

    protected void loadImage(String imageName) {

        ImageIcon ii = new ImageIcon(imageName);
        image = ii.getImage();
    }

    public Image getImage() {
        return image;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
        if (this.visible)
        {
            width=0;
            height=0;
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
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
}