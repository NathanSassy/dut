import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Panneau extends JPanel implements Runnable {
    public final static int MILLIS_WAIT = 100;
    private int width;
    private int height;

    ArrayList<Tableau> tableaux;

    public Panneau (int width, int height) {
        this.width = width;
        this.height = height;
        Dimension size = new Dimension (width, height);
        setMinimumSize(size);
        setPreferredSize(size);
        setDoubleBuffered(true);

        tableaux = new ArrayList<>();
        Tableau t1 = new Tableau(this, 30, 250, 100, 200, Color.BLUE);
        t1.setSpeedH(2);
        t1.setSpeedV(2);
        tableaux.add(t1);

        Tableau t2 = new Tableau(this, 5, 30, 60, 50, Color.RED);
        t2.setSpeedV(2);
        tableaux.add(t2);

        Tableau t3 = new Tableau(this, 300, 300, 100, 200, Color.GREEN);
        t3.setSpeedH(-4);
        t3.setSpeedV(-6);
        tableaux.add(t3);

        Tableau t4 = new Tableau(this, 30, 80, 50, 100, Color.BLACK);
        t4.setSpeedH(2);
        t4.setSpeedV(2);
        tableaux.add(t4);

        new Thread(this).start();
        enableCollision();
    }

    public void enableCollision() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    for(int i = 0; i < tableaux.size(); i++) {
                        Tableau t = tableaux.get(i);
                        for(int j = 0; j < tableaux.size(); j++) {
                            if(i != j && t.collision(tableaux.get(j))) {
                                //t.flip();
                                tableaux.get(j).flip();
                            }
                        }
                    }

                    try {
                        Thread.sleep(MILLIS_WAIT);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }).start();
    }

    @Override
    public void run() {
        while(true) {
            update();
        }
    }

    public synchronized void update () {
        repaint();
    }

    public void paint (Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setBackground (Color.WHITE);
        g2.clearRect (0, 0, width, height);

        for(Tableau t : tableaux) {
            if(t.isVisible()) {
                g2.setColor(t.getColor());
                g2.fillRect(t.getX(), t.getY(), t.getWidth(), t.getHeight());
            }
        }
    }

    public ArrayList<Tableau> getTableaux() {
        return tableaux;
    }
}