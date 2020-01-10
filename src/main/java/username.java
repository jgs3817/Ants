import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
* ASDLKFJSAD;LKFJSAD;LKJFSAD;LKFJDSA;LFKJ
* */

public class username extends JFrame
{
    //Variables to be used in all functions
    private JButton startbtn;
    private JLabel greet;
    JTextField username;
    Font font = new Font("Comic Sans MS",NORMAL, 30);
    static String user1;

    public username(){
        user1 = new String();
        greet = new JLabel("<html> Welcome to Ants GUI <br/>Enter your name</html>");
        // greet.setFont(font);

        startbtn = new JButton("Start");
        // startbtn.setFont(font);



        username = new JTextField();
        username.setPreferredSize((new Dimension(100,20)));
        username.setFont(font);

        setLayout(new GridBagLayout());




        // Action: get username when user press start button
        ActionListener whatsmyname = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                user1 = username.getText();
                nameReturn(); // print user input on terminal
            }
        };
        // add action to button
        startbtn.addActionListener(whatsmyname); // username will be
        startbtn.addActionListener(actionEvent -> new VideoMenu());  // open new menu frame

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
        add(startbtn ,c);

        setVisible(true);
        setSize(500,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

<<<<<<< Updated upstream:src/main/java/username.java
=======
        // add action to button
        startButton.addActionListener(whatsmyname); // username will be
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userpageFlag=true;
                TalkServlet.postInit();
            }
        });

>>>>>>> Stashed changes:src/main/java/panels/UserPage.java
    }

    public static String nameReturn(){
        System.out.println(user1);
        return user1;
    } //returns username

}
