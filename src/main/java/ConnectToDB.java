import java.net.URI;
import java.net.URISyntaxException;
import java.sql.*;

public class ConnectToDB {

    public ConnectToDB(){

        ResultSet rset = null;

        try{
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        try {
            Connection conn = getConnection();
            Statement s = conn.createStatement();
            String sqlStr = "SELECT * FROM Ants;";
            rset = s.executeQuery(sqlStr);

            while(rset.next()){
                System.out.println(rset.getInt("id") + " " +  rset.getString("familyname"));
            }

            rset.close();
            s.close();
            conn.close();


        } catch (SQLException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private static Connection getConnection() throws URISyntaxException, SQLException {
        URI dbUri = new URI(System.getenv("DATABASE_URL"));

//        String username = dbUri.getUserInfo().split(":")[0];
//        String password = dbUri.getUserInfo().split(":")[1];
        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();
        System.out.println(dbUrl);

        return DriverManager.getConnection(dbUrl);
    }
}
