package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import battle.*;

/**
* The graphical game mode of battleship
*/
public class GraphicalGame extends SimpleFrame implements ActionListener
{
    private JTable myGridTab;
    private JTable opponentGridTab;
    private JButton shipPlacementBtn;
    private JButton newShotBtn;
    private JTextArea logTextArea;
    private CordDialog shipPlacementDialog;
    private CordDialog newShotDialog;
    private int shipNumber;

    private Game game;

  /**
  * The constructor
  * @param game the game
  */
  public GraphicalGame(Game game)
  {
    this.game = game;
    this.setTitle("Bataille Navale");
    this.shipPlacementDialog = new CordDialog(0, game.getPlayer1().getMyGrid().length-1, 0, game.getPlayer1().getMyGrid()[0].length-1);
    this.shipPlacementDialog.addDirectionChooser();
    this.newShotDialog = new CordDialog(0, game.getPlayer1().getOpponentGrid().length-1, 0, game.getPlayer1().getOpponentGrid()[0].length-1);
    
    // Containers
    Container container = new Container();
    container.setLayout(new GridLayout(1,3));
    Container containerOperation = new Container();
    containerOperation.setLayout(new GridLayout(3,1));
    Container myGridContainer = new Container();
    myGridContainer.setLayout(new BorderLayout());
    Container opponentGridContainer = new Container();
    opponentGridContainer.setLayout(new BorderLayout());

    // Block 1
    GridTableModel myGridModel = new GridTableModel(game.getPlayer1().getMyGrid());
    this.myGridTab = new JTable(myGridModel);
    // to adjust some parameters
    myGridTab.setShowGrid(true);
    myGridTab.setGridColor(Color.BLUE);
    myGridTab.setBackground(Color.LIGHT_GRAY);
    myGridTab.setRowHeight(50);
    myGridContainer.add(new JLabel("Ma grille"), BorderLayout.NORTH);
    myGridContainer.add(myGridTab, BorderLayout.CENTER);

    // Block 2
    this.shipPlacementBtn = new JButton("Placer les bateaux");
    this.shipPlacementBtn.addActionListener(this);
    this.newShotBtn = new JButton("Nouveau tir");
    this.newShotBtn.addActionListener(this);
    this.newShotBtn.setEnabled(false);
    this.logTextArea = new JTextArea();
    logTextArea.setEditable(false);
    JScrollPane sp = new JScrollPane(logTextArea);
    containerOperation.add(shipPlacementBtn);
    containerOperation.add(newShotBtn);
    //containerOperation.add(logTextArea);
    containerOperation.add(sp);

    // Block 3
    GridTableModel opponentGridModel = new GridTableModel(game.getPlayer1().getOpponentGrid());
    this.opponentGridTab = new JTable(opponentGridModel);
    // to adjust some parameters
    opponentGridTab.setShowGrid(true);
    opponentGridTab.setGridColor(Color.BLUE);
    opponentGridTab.setBackground(Color.LIGHT_GRAY);
    opponentGridTab.setRowHeight(50);
    opponentGridContainer.add(new JLabel("Grille adversaire"), BorderLayout.NORTH);
    opponentGridContainer.add(opponentGridTab, BorderLayout.CENTER);

    container.add(myGridContainer);
    container.add(containerOperation);
    container.add(opponentGridContainer);

    getContentPane().add(container);
    pack();
    setVisible(true);
    setExtendedState(this.MAXIMIZED_BOTH);
  }

  public void actionPerformed(ActionEvent e)
  {
    if(e.getSource() == shipPlacementBtn)
    {
        new Thread(new Runnable()
        {
            public void run()
            {
                boolean ok;
                for(int i = 0; i < game.getPlayer1().getFleet().size(); i++)
                {
                    ok = false;
                    while(!ok)
                    {
                        try
                        {
                            shipPlacementDialog.setTitle("Placez le " + game.getPlayer1().getFleet().get(i).getName());
                            shipPlacementDialog.showIt();
                            while(shipPlacementDialog.getLock())
                            {
                                try
                                {
                                    wait();
                                }
                                catch(Exception err){}
                                
                            }
                            game.getPlayer1().shipPlacement(shipPlacementDialog.getX(), shipPlacementDialog.getY(), shipPlacementDialog.getDirection(), i);
                            writeLog(game.getPlayer1().getFleet().get(i).getName() + " placé en (" +  shipPlacementDialog.getX() + ", " + shipPlacementDialog.getY() + " )");
                            ok = true;
                        }
                        catch(Exception wrongPlacement)
                        {
                            ok = false;
                            JOptionPane.showMessageDialog(new JFrame(), wrongPlacement.getMessage());

                        }

                        myGridTab.updateUI();
                    }
                }

                game.getPlayer2().shipPlacement();
                opponentGridTab.updateUI();

                shipPlacementBtn.setEnabled(false);
                newShotBtn.setEnabled(true);
            }
        }).start();
    }
    else if(e.getSource() == newShotBtn)
    {
        new Thread(new Runnable()
        {
            public void run()
            {
                newShotDialog.showIt();
                while(newShotDialog.getLock())
                {
                    try
                    {
                        wait();
                    }
                    catch(Exception err){}
                    
                }
                int shot[] = new int[2];
                shot[0] = newShotDialog.getX();
                shot[1] = newShotDialog.getY();
                writeLog(game.getPlayer1().getName() + " tire en (" + shot[0] + ", " + shot[1] + ") : " + game.analyzeShot(game.getPlayer1(), shot));
                opponentGridTab.updateUI();

                if(game.allSunk(game.getPlayer2()))
                    JOptionPane.showMessageDialog(new JFrame(), game.getPlayer1().getName() + " gagne !");

                shot = game.getPlayer2().newShot();
                writeLog(game.getPlayer2().getName() + " tire en (" + shot[0] + ", " + shot[1] + ") : " + game.analyzeShot(game.getPlayer2(), shot));
                myGridTab.updateUI();
                
                if(game.allSunk(game.getPlayer1()))
                    JOptionPane.showMessageDialog(new JFrame(), game.getPlayer2().getName() + " gagne !");
            }
        }).start();
    }
  }

  private void writeLog(String log)
  {
    this.logTextArea.append("\n" + log);
  }

 
}