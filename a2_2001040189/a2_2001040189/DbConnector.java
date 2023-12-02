import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnector {
    private static final String JDBC_URL = "jdbc:sqlite:D:\\Check WPR\\a2_2001040189\\a2_2001040189\\library.db";
    private static Connection connection = null;

    public static Connection connect() {
        if (connection != null) return connection;
        else {
            try {
                connection = DriverManager.getConnection(JDBC_URL);
                System.out.println("Connected to the database");
                return connection;
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException("Error connecting to the database");
            }
        }
    }

    public static void disconnect() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Disconnected from the database");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error disconnecting from the database");
        }
    }
}
