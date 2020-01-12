package panels;

import data_transfer.TalkServlet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
The panels.UserPage class is a JPanel which is a page to allow
users to enter their username
*/

public class UserPage extends JPanel {
    private JButton startButton;
    private JLabel greet;
    private JTextField username;

    static String user;
    private static boolean userpageFlag;

    public UserPage(){
        userpageFlag = false;
        user = new String();
        greet = new JLabel("<html> Welcome to Ants GUI <br/>Enter your name</html>");

        startButton = new JButton("Start");
        username = new JTextField();
        username.setPreferredSize((new Dimension(100,20)));

        setLayout(new GridBagLayout());

        ActionListener whatsmyname = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                user = username.getText();
            }
        };

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.anchor=GridBagConstraints.FIRST_LINE_START;

        setGridBagLayout(c,1,0,1,2);
        greet.setFont(new Font("default", Font.BOLD, 36));
        add(greet,c);
        greet.setHorizontalAlignment(JLabel.CENTER);

        setGridBagLayout(c,1,1,1,1);
        add(username,c);

        setGridBagLayout(c,1,4,1,1);
        add(startButton,c);

        // add action to button
        startButton.addActionListener(whatsmyname);
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TalkServlet.postInit();
                userpageFlag=true;
            }
        });
    }

    private void setGridBagLayout(GridBagConstraints c, int gx, int gy, int wx, int wy){
        c.gridx = gx;
        c.gridy = gy;
        c.weightx = wx;
        c.weighty = wy;
    }

    public static boolean getUserFlag(){
        return userpageFlag;
    }

    public static void setUserFlag(boolean state){
        userpageFlag = state;
    }
}