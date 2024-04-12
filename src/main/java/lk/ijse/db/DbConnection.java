package lk.ijse.db;

import lombok.Getter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Getter
public class DbConnection {
    private static DbConnection dbConnection;
    private final Connection connection;

        private DbConnection() throws SQLException {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/Project-Ride",
                    "root",
                    "Mixage03!"
            );
        }

        public static DbConnection getInstance() throws SQLException {
            return (null == dbConnection) ? dbConnection = new DbConnection() : dbConnection;
        }

        public Connection getConnection() {
            return connection;
        }

        public void close() throws SQLException {
            connection.close();
        }
}
