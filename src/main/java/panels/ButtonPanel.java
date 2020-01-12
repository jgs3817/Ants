package panels;

import data_transfer.FBData;
import data_transfer.LandingData;
import data_transfer.TalkServlet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/*
The panels.ButtonPanel class is a JPanel that is used to display the
+ and - buttons which each have an action listener
*/

public class ButtonPanel extends JPanel {
    private static JButton lastButton;
    private JButton addButton;
    private JButton minusButton;
    private JButton backButton;
    private JPanel organiserPanel = new JPanel();

    private static boolean backFlag;
    static IDPanel idPanel = new IDPanel();
    private ArrayList<ArrayList<Integer>> antData;
    private ArrayList<ArrayList<Integer>> overlayAntData;
    private LandingData dataLanding;

    private int count=0;        //tracks the ant ID

    public ButtonPanel(){
        dataLanding = TalkServlet.getLandingData();

        addButton = new JButton("+");
        minusButton = new JButton("-");
        backButton = new JButton("Back");

        setLayout(new GridLayout(2,1));
        add(backButton);
        organiserPanel.setLayout(new GridLayout(1,0));
        organiserPanel.add(addButton);
        organiserPanel.add(minusButton);
        add(organiserPanel);

        if(antData != null) {
            for (ArrayList<Integer> i : antData) {
                JButton antButton = new JButton(String.valueOf(i.get(0)));
                idPanel.add(antButton);
                count = i.get(0);
            }
        }

        addButton.addActionListener(new ActionListener() {
            //When addButton is clicked, add a new ant button
            @Override
            public void actionPerformed(ActionEvent e) {
                count++;
                JButton antButton = new JButton(String.valueOf(count));       //ant button is created
                antButton.addActionListener(new ActionListener() {
                    //When ant button is clicked, save it as lastButton
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(antButton != lastButton){
                            antButton.setBackground(new Color(150,203,255));
                            if(lastButton!=null){
                                lastButton.setBackground(null);
                            }
                        }
                        lastButton = antButton;
                    }
                });
                idPanel.add(antButton);
                idPanel.revalidate();
                idPanel.repaint();
            }
        });

        minusButton.addActionListener(new ActionListener() {
            //When minusButton is clicked, remove the last clicked button
            @Override
            public void actionPerformed(ActionEvent e) {
                if(lastButton != null){
                    lastButton.getParent().remove(lastButton);
                    idPanel.revalidate();
                    idPanel.repaint();
                }
                lastButton = null;
            }
        });

        backButton.addActionListener(new ActionListener() {
            //When minusButton is clicked, remove the last clicked button
            @Override
            public void actionPerformed(ActionEvent e) {
                backFlag=true;
            }
        });
    }

    //Function for ID panel object to be accessed in other classes
    public static JPanel getIDPanel(){
        return idPanel;
    }

    //Function to return the last clicked button
    public static JButton getLastButton(){
        if (lastButton == null){
            return null;
        }
        return lastButton;
    }

    public static boolean getBackFlag(){
        return backFlag;
    }

    public static void setBackFlag(boolean state){
        backFlag=state;
    }
}