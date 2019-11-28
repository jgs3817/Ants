import javax.swing.*;
import java.awt.*;
import java.net.URISyntaxException;
import java.sql.*;

public class Main {
    static GraphicsConfiguration gc; // Class field containing config info

    public static void main(String[] args) {

        String dbUrl = System.getenv("JDBC_DATABASE_URL");

        ResultSet rset = null;

        try{
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            Connection conn = DriverManager.getConnection(dbUrl);
            Statement s = conn.createStatement();
            String sqlStr = "SELECT * FROM Ant;";
            rset = s.executeQuery(sqlStr);

            while(rset.next()){
                System.out.println(rset.getInt("ant_id") + " " +  rset.getString("video_id"));
            }

            rset.close();
            s.close();
            conn.close();


        } catch (SQLException e) {
            e.printStackTrace();
        }

        JFrame frame = new JFrame(gc); // Create a new JFrame
        frame.setSize(500, 300);
        JPanel mainContainer = new JPanel();

        mainContainer.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 0;
        c.weightx=1;
        c.weighty=1;
        mainContainer.add(new ButtonIDContainer(),c);

        c.fill = GridBagConstraints.BOTH;
        c.gridx = 1;
        c.gridy = 0;
        c.weightx=5;
        c.weighty=1;
        mainContainer.add(new VideoFramesContainer(),c);

        frame.add(mainContainer);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
