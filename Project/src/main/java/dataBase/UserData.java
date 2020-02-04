package dataBase;

import org.apache.log4j.Logger;
import java.sql.*;

public class UserData{
    private String login;
    private String password;
    private Logger logger = Logger.getLogger(UserData.class);

    public UserData(String parameters) {
        this.setUserDataFromDataBase(parameters);
    }

    private UserData() {
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    private void setUserDataFromDataBase(String parameters) {
        String url = "jdbc:mysql://localhost:3306/user?useSSL=false&useUnicode=true&serverTimezone=UTC";
        String user = "boroda";
        String password = "CRonaldo77";
        String query = "SELECT * FROM user.userdata WHERE parameters='" + parameters + "';";
        try (Connection con = DriverManager.getConnection(url, user, password);
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(query)) {
            if (rs.next()) {
                this.login = rs.getString(2);
                this.password = rs.getString(3);
            }
            logger.info("data has got from DataBase");
        } catch (SQLException ex) {
            logger.error("Error while getting data from dataBase");
            ex.printStackTrace();
        }
    }
}