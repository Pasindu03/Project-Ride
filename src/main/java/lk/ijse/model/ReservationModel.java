package lk.ijse.model;

import lk.ijse.db.DbConnection;
import lk.ijse.util.CrudUtil;

import java.sql.*;
import java.time.LocalDate;

public class ReservationModel {

    public static String generateNextOrderId() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT reservationId FROM reservation ORDER BY reservationId DESC LIMIT 1";
        PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();
        if(resultSet.next()) {
            return splitOrderId(resultSet.getString(1));
        }
        return splitOrderId(null);
    }

    private static String splitOrderId(String currentReservationId) {
        if(currentReservationId != null) {
            String[] split = currentReservationId.split("O0");

            int id = Integer.parseInt(split[1]); //01
            id++;
            return "O00" + id;
        } else {
            return "O001";
        }
    }

    public static boolean saveOrder(String reservationId, String passengerId, LocalDate date) throws SQLException, ClassNotFoundException {
        boolean execute = CrudUtil.execute("INSERT INTO reservation VALUES(?, ?, ?)", reservationId, passengerId, Date.valueOf(date));
        return execute;
    }
}
