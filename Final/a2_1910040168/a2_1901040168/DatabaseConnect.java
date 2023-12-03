import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnect {

    private static Connection conn = null;
    private static Constants constants = new Constants();
    private static final String DBMS = "sqlite";
    private static final String DB_NAME = ":library.db";
    private static final String JDBC_URL = "jdbc:" + DBMS + DB_NAME;

    public static Connection connect() {
        if (conn != null) return conn;
        else {
            try {
                conn = DriverManager.getConnection(JDBC_URL);
                System.out.println(constants.DB_CONNECTED_SUCCESS);
                return conn;
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(constants.DB_CONNECTED_FAIL);
            }
        }
    }
}
