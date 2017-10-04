import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Launcher {

    public static void main(String args[]) {
        testComponent("TP4", new Panneau(800, 600));
    }

    public static final void testComponent (final String title, final Component component) {
        SwingUtilities.invokeLater (new Runnable () {
            public void run () {
                JFrame jFrame = new JFrame(title);
                jFrame.addWindowListener (new WindowAdapter() {
                    public void windowClosing (WindowEvent e) {
                        System.exit (0);
                    }
                });
                jFrame.getContentPane().add (component, BorderLayout.CENTER);
                jFrame.pack ();
                Dimension screenSize = Toolkit.getDefaultToolkit ().getScreenSize ();
                Dimension size = jFrame.getSize ();
                jFrame.setLocation ((screenSize.width - size.width)/4, (screenSize.height - size.height)/4);
                jFrame.setSize(new Dimension(800, 600));
                jFrame.setVisible (true);
            }
        });
    }
}
