package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import battle.Square;
import battle.Player;

public class GraphicalGame extends SimpleFrame implements ActionListener
{
    private Player player;
    private JTable myGridTab;
    private JTable opponentGridTab;
    private JButton shipPlacementBtn;
    private JButton newShotBtn;
    private JTextArea logTextArea;
    private CordDialog shipPlacementDialog;
    private CordDialog newShotDialog;
    private int shipNumber;

  public GraphicalGame(Player hp)
  {
    this.player = hp;
    this.setTitle("Bataille Navale");
    this.shipPlacementDialog = new CordDialog(0, hp.getMyGrid().length, 0, hp.getMyGrid()[0].length);
    this.newShotDialog = new CordDialog(0, hp.getOpponentGrid().length, 0, hp.getOpponentGrid()[0].length);
    
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
    GridTableModel myGridModel = new GridTableModel(hp.getMyGrid());
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
    containerOperation.add(logTextArea);

    // Block 3
    GridTableModel opponentGridModel = new GridTableModel(hp.getOpponentGrid());
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
  }

  public void actionPerformed(ActionEvent e)
  {
    if(e.getSource() == shipPlacementBtn)
    {
        int x, y;
        for(int i = 0; i < player.getFleet().size(); i++)
        {
            //shipPlacementDialog.showIt();
            //player.shipPlacement(shipPlacementDialog.getX(), shipPlacementDialog.getY(), "H", i);
            //writeLog("Bateau placé en (" +  shipPlacementDialog.getX() + ", " + shipPlacementDialog.getY() + " )");
            x = Integer.parseInt(JOptionPane.showInputDialog(null, "x"));
            y = Integer.parseInt(JOptionPane.showInputDialog(null, "y"));
            player.shipPlacement(x, y, "H", i);
            writeLog("Bateau placé en (" +  x + ", " + y + " )");
            myGridTab.updateUI();

        }
        shipPlacementBtn.setEnabled(false);
        newShotBtn.setEnabled(true);
    }
  }

  private void writeLog(String log)
  {
    this.logTextArea.append("\n" + log);
  }

 
}