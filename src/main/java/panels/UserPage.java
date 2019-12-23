package panels;

import data_transfer.TalkServlet;
import panels.LandingPage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserPage extends JPanel {
    //Variables to be used in all functions
    private JButton startButton;
    private JLabel greet;
    private JTextField username;
    //private Font font = new Font("Comic Sans MS",NORMAL, 30);
    private LandingPage vidMenu;
    static String user1;
    private static boolean userpageFlag;

    public UserPage(){
        userpageFlag = false;
        //System.out.println("panels.UserPage constructor called");
        user1 = new String();
        greet = new JLabel("<html> Welcome to Ants GUI <br/>Enter your name</html>");

        startButton = new JButton("Start");

        username = new JTextField();
        username.setPreferredSize((new Dimension(100,20)));
        //username.setFont(font);

        setLayout(new GridBagLayout());

        ActionListener whatsmyname = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                user1 = username.getText();
                nameReturn(); // print user input on terminal
            }
        };

        // add components
        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.BOTH;
        c.anchor=GridBagConstraints.FIRST_LINE_START;
        c.gridx = 1;
        c.gridy = 0;
        c.weightx= 1;
        c.weighty= 2;
        add(greet ,c);
        greet.setHorizontalAlignment(JLabel.CENTER);  // center align JLabel header

        c.gridx = 1;
        c.gridy = 1;
        c.weightx= 1;
        c.weighty= 1;
        add(username ,c);

        c.gridx = 1;
        c.gridy = 4;
        c.weightx= 1;
        c.weighty= 1;
        add(startButton,c);

        // add action to button
        startButton.addActionListener(whatsmyname); // username will be
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userpageFlag=true;
                TalkServlet.postInit();
            }
        });
    }

    public static String nameReturn(){
        System.out.println(user1);
        return user1;
    }

    public static boolean getUserFlag(){
        return userpageFlag;
    }

    public static void setUserFlag(boolean state){
        userpageFlag = state;
    }
}