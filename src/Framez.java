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
    public Framez(){
        JFrame frame = new JFrame("Big Tac Toe");
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

        if(Board.board[loc][row][col].isEmpty()) {
            if(buttonloc == -1 || buttonloc == loc) {

                ((JButton) e.getSource()).setText(Board.player);
                Board.board[loc][col][row] = Board.player;
                System.out.println(Board.getSmall(loc));
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
                if(Board.winSmall(loc, col, row)){
                    System.out.println("Win! " + Board.player);
                    buttonloc = -1;
                    for(int j = 0; j < Board.board[0].length; j++) {
                        for (int k = 0; k < Board.board[0][0].length; k++) {
                            Board.board[loc][j][k] = Board.player;
                        }
                    }
                }
                System.out.println("button location: " + buttonloc);
                System.out.println("panel location: " + loc);
                panels[loc].setBackground(Color.DARK_GRAY);
                if (Board.player.equals("X")) {
                    panels[buttonloc].setBackground(new Color(191, 238, 255));
                }
                if (Board.player.equals("O")) {
                    panels[buttonloc].setBackground(new Color(255, 191, 205));
                }

                Board.swapPlayer();

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
//        if(e.getSource() instanceof JPanel){
//            String current = e.toString();
//            current = current.substring(current.indexOf("javax.swing.JPanel[,") + 20);
//            panelx = Integer.parseInt(current.substring(0, current.indexOf(",")));
//            current = current.substring(current.indexOf(",") + 1);
//            panely = Integer.parseInt(current.substring(0, current.indexOf(",")));
//            System.out.println("Current panel " + panelx + " " + panely);
//        }
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
    @Override
    public void actionPerformed(ActionEvent e) {


    }
}
