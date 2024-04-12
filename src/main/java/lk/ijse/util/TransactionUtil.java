package lk.ijse.util;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionUtil {
    static Connection connection = null;
    public static boolean setAutoCommit(boolean b) throws SQLException {
        if (b) {
            connection.setAutoCommit(true);
        } else {
            connection.setAutoCommit(false);
        }
        return false;
    }

    public static void rollback() throws SQLException {
        connection.rollback();
    }

    public static void commit() throws SQLException {
        connection.commit();
    }
}
