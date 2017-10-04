import javax.swing.*;
import javax.swing.plaf.metal.MetalBorders;
import java.awt.*;

public class Tableau implements Runnable {
    Panneau panneau;
    private int width;
    private int height;
    private Color color;
    private int x, y;
    private int speedH;
    private int speedV;
    private boolean visible;

    public Tableau(Panneau panneau, int x, int y, int width, int height, Color color) {
        this.panneau = panneau;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
        speedH = 0;
        speedV = 0;
        visible = false;

        Thread moove = new Thread(this);
        final Tableau myself = this;
        new Thread(new Runnable() {
            @Override
            public void run() {
                boolean ok = false;
                while(!ok) {
                    ok = true;
                    for(Tableau t : panneau.getTableaux()) {
                        if(t.isVisible() && myself.collision(t))
                            ok = false;
                    }

                    if(!ok) {
                        try {
                            Thread.sleep(Panneau.MILLIS_WAIT);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }
                visible = true;
                moove.start();
            }
        }).start();
    }

    public void flip() {
        speedH *= -1;
        speedV *= -1;
        x += speedH;
        y += speedV;
    }

    @Override
    public void run() {
        while(true) {

            if (x <= 0 || x + width >= panneau.getWidth()) {
                speedH *= -1;
            }

            if (y <= 0 || y + height >= panneau.getHeight()) {
                speedV *= -1;
            }

            x += speedH;
            y += speedV;

            try {
                Thread.sleep(Panneau.MILLIS_WAIT);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean collision(Tableau t) {
        boolean ret = false;
        if (x < (t.getX()+t.getWidth()) && (x+width) > t.getX()
                && y < t.getY()+t.getHeight() && (y+height) > t.getY()) {
            ret = true;
        }
        return ret;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setSpeedH(int speedH) {
        this.speedH = speedH;
    }

    public void setSpeedV(int speedV) {
        this.speedV = speedV;
    }

    public int getSpeedH() {
        return speedH;
    }

    public int getSpeedV() {
        return speedV;
    }

    public boolean isVisible() {
        return visible;
    }
}
