import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.WindowConstants;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.DataBufferByte;
import java.awt.image.DataBufferInt;

public class Image
{

    protected int[] pixels;
    protected int height;
    protected int width;
    protected static int display = 0;

    // constructeur par défaut}
    public Image(int width, int height)
    {
        this.width = width;
        this.height = height;
        pixels = new int[width*height];
        Arrays.fill(pixels,0);
    }

    // constructeur par recopie
    public Image(Image i)
    {
        width = i.width;
        height = i.height;
        pixels = Arrays.copyOf(i.pixels, i.pixels.length);
    }

    // constructeur par chargement d'image
    public Image(String filename)
    {
        try {
            BufferedImage bimg = ImageIO.read(new File(filename));
            Raster raster = bimg.getData();
            this.width = bimg.getWidth();
            this.height = bimg.getHeight();
            pixels = raster.getSamples(0,0, width, height,0, pixels);
        }
        catch(IOException ex) {
            System.out.println("Problème lecture du fichier : " + filename);
            ex.printStackTrace();
        }
    }

    // retourne la hauteur de l'image
    public int getHeight() {
        return height;
    }

    // retourne la largeur de l'image
    public int getWidth()
    {
        return width;
    }

    // accesseur en lecture
    public int getValue(int x, int y)
    {
        return pixels[x+y*width];
    }

    // accesseur en écriture
    public void setValue(int x, int y, int v)
    {
        pixels[x+y*width] = v;
    }

    // affichage de l'image
    public void display() {
        // conversion en BufferedImage
        BufferedImage bimg = new BufferedImage(width,height,BufferedImage.TYPE_BYTE_GRAY);
        byte [] buffer = ((DataBufferByte) bimg.getRaster().getDataBuffer()).getData();
        for (int p=0; p < buffer.length; p++)
            buffer[p] = (byte)(pixels[p]);

        // Création de la fenetre d'affichage et affichage
        display++;
        JFrame f = new JFrame("Display: "+display);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        ImageIcon imageIcon = new ImageIcon(bimg);
        JLabel jLabel = new JLabel();
        jLabel.setIcon(imageIcon);
        f.getContentPane().add(jLabel, BorderLayout.CENTER);
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }

    // affichage de l'image	en couleurs aléatoires
    public void displayColor() {
        // generation de la table de pseudo-couleurs
        int max = 0;
        for (int p = 0; p < pixels.length; p++)
            if(pixels[p] > max) max = pixels[p];
        int[] tab = new int[max+1];
        Random r = new Random();
        int l = 256*256*256;
        for(int t=0; t < tab.length; t++)
            tab[t] = r.nextInt(l);

        // conversion en BufferedImage
        BufferedImage bimg = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        int [] buffer = ((DataBufferInt) bimg.getRaster().getDataBuffer()).getData();
        for (int p = 0; p < buffer.length; p++)
            buffer[p] = tab[pixels[p]];

        // Création de la fenetre d'affichage et affichage
        display++;
        JFrame f = new JFrame("Display: " + display);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        ImageIcon imageIcon = new ImageIcon(bimg);
        JLabel jLabel = new JLabel();
        jLabel.setIcon(imageIcon);
        f.getContentPane().add(jLabel, BorderLayout.CENTER);
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }
}
