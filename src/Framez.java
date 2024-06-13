import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static java.lang.Integer.parseInt;

public class Framez extends JFrame implements MouseListener,ActionListener {
    public JPanel[] panels = new JPanel[9];
    public JButton[] buttons = new JButton[9];
    int buttonx;
    int buttony;
    int panelx;
    int panely;
    int buttonloc = -1;
    int win = 0;
    JFrame frame;
    public Framez(){
        frame = new JFrame("Big Tac Toe");
        frame.setSize(1000, 1000);
        frame.setLayout(null);
        frame.setVisible(true);
        int x = 50;
        int y = 50;
        for(int i = 0; i < panels.length; i++){
            panels[i] = new JPanel();
            panels[i].setBackground(new Color(50,50,56));
            panels[i].setBounds(x, y, 250,250);
            panels[i].setLayout(new GridLayout(3,3));
            panels[i].addMouseListener(this);
            for(int j = 0; j < 9; j++){
                JButton current;
                panels[i].add(current = new JButton());
                current.addMouseListener(this);
            }
            panels[i].setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 4));
            x+= 250;
            if(x>750){
                x=50;
                y+=250;
            }
            frame.add(panels[i]);
        }
        frame.setSize(1000, 1000);


        // Using WindowListener for closing the window
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e)
            {
                System.exit(0);
            }
        });
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() instanceof JButton) {
            String current = e.toString();
            current = current.substring(current.indexOf("javax.swing.JButton[,") + 21);
            buttonx = Integer.parseInt(current.substring(0, current.indexOf(",")));
            current = current.substring(current.indexOf(",") + 1);
            buttony = Integer.parseInt(current.substring(0, current.indexOf(",")));
        }
        JButton sourceButton = (JButton)e.getSource();
        // Use Container abstract class / polymorphism
        Container parent = sourceButton.getParent();

        // Traverse up the hierarchy to find the panel
        while (!(parent instanceof JPanel) && parent != null) {
            parent = parent.getParent();
        }

        // Get the parent panel
        if (parent != null) {
            JPanel parentPanel = (JPanel) parent;
            String current = parentPanel.toString();
            current = current.substring(current.indexOf("javax.swing.JPanel[,") + 20);
            panelx = Integer.parseInt(current.substring(0, current.indexOf(",")));
            current = current.substring(current.indexOf(",") + 1);
            panely = Integer.parseInt(current.substring(0, current.indexOf(",")));
        }
        int loc = 0;
        int row = 0;
        int col = 0;
        //Find button row
        if(buttonx == 85) {
            row = 1;
        } else if(buttonx == 165){
            row = 2;
        }
        //Find button column
        if(buttony == 85) {
            col = 1;
        } else if(buttony == 165){
            col = 2;
        }
        //parse rows and columns

        //Find panels
        if(panely == 300) {
            loc = loc + 3;
        } else if(panely == 550){
            loc = loc + 6;
        }
        if(panelx == 300){
            loc = loc + 1;
        } else if(panelx == 550){
            loc = loc + 2;
        }

        if(Board.board[loc][row][col].equals("") || !Board.isWon(loc)) {
            if((buttonloc == -1 || buttonloc == loc) && Board.board[loc][col][row].equals("")) {

                //set button text to the player that clicked on it
                    ((JButton) e.getSource()).setText(Board.player);
                    Board.board[loc][col][row] = Board.player;

                //Check if the board is won
                if (Board.winSmall(loc, col, row)) {

                    //setting graphics
                    panels[loc].removeAll();
                    JButton name;
                    panels[loc].setLayout(new GridLayout(1,1));
                    panels[loc].add(name = new JButton(Board.player));
                    name.setBounds(0, 0, 250, 250);
                    name.setBackground(Color.DARK_GRAY);
                    name.setFont(new Font("Arial", 0, 200));
                    if (Board.player.equals("X")) {
                        name.setForeground(new Color(255, 191, 205));
                    }
                    if (Board.player.equals("O")) {
                        name.setForeground(new Color(191, 238, 255));
                    }
                    //setting board
                    for (int j = 0; j < Board.board[0].length; j++) {
                        for (int k = 0; k < Board.board[0][0].length; k++) {
                            Board.board[loc][j][k] = Board.player;
                        }
                    }
                    if (Board.winBig()) {
                        JPanel panel = new JPanel();
                        panel.setBounds(50, 50, 750, 750);
                        if (Board.player.equals("X")) {
                            panel.setBackground(new Color(191, 238, 255));
                        }
                        if (Board.player.equals("O")) {
                            panel.setBackground(new Color(255, 191, 205));
                        }
                        JLabel label = new JLabel("Winner: " + Board.player + "!");
                        label.setBounds(0, 0, 750, 750);
                        panel.add(label);
                        frame.add(panel);
                        label.setFont(new Font("Times New Roman", 1, 150));
                        win = 1;
                        for(int i = 0; i < panels.length; i++){
                            frame.remove(panels[i]);
                        }
                    }
                } else{
                    if(Board.boardCheck(loc)){
                        panels[loc].removeAll();
                        JButton current;
                        panels[loc].setLayout(new GridLayout(1,1));
                        panels[loc].add(current = new JButton("-"));
                        current.setBounds(0, 0, 250, 250);
                        current.setBackground(Color.DARK_GRAY);
                        current.setFont(new Font("Arial", 0, 300));
                    }
                }
                if (win == 0){
                // Find location of next move
                buttonloc = 0;
                if (buttony == 85) {
                    buttonloc = buttonloc + 3;
                } else if (buttony == 165) {
                    buttonloc = buttonloc + 6;
                }
                if (buttonx == 85) {
                    buttonloc = buttonloc + 1;
                } else if (buttonx == 165) {
                    buttonloc = buttonloc + 2;
                }
                if (Board.isWon(buttonloc)) {
                    buttonloc = -1;
                    for (int i = 0; i < panels.length; i++) {
                        if (!Board.isWon(i) && i != loc) {
                            if (Board.player.equals("O")) {
                                panels[i].setBackground(new Color(191, 238, 255));
                            }
                            if (Board.player.equals("X")) {
                                panels[i].setBackground(new Color(255, 191, 205));
                            }
                        } else {
                            panels[loc].setBackground(Color.DARK_GRAY);
                        }
                    }
                } else {
                    for (int i = 0; i < panels.length; i++) {
                        panels[i].setBackground(Color.DARK_GRAY);
                    }
                    if (Board.player.equals("X")) {
                        panels[buttonloc].setBackground(new Color(191, 238, 255));
                    }
                    if (Board.player.equals("O")) {
                        panels[buttonloc].setBackground(new Color(255, 191, 205));
                    }
                }

                Board.swapPlayer();
            }
            }
        }


    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
    @Override
    public void actionPerformed(ActionEvent e) {


    }
}
