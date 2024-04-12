package lk.ijse.model;

import lk.ijse.db.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DeliveryModel {

    public static String generateNextOrderId() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "SELECT deliveryId FROM delivery ORDER BY deliveryId DESC LIMIT 1";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();
        if(resultSet.next()) {
            return splitOrderId(resultSet.getString(1));
        }
        return splitOrderId(null);
    }

    private static String splitOrderId(String currentDeliveryId) {
        if(currentDeliveryId != null) {
            String[] split = currentDeliveryId.split("O0");

            int id = Integer.parseInt(split[1]); //01
            id++;
            return "O00" + id;
        } else {
            return "O001";
        }
    }

    public static boolean saveOrder(String deliveryId, String passengerId, String date) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "INSERT INTO delivery VALUES(?, ?, ?)";
        PreparedStatement pstm = null;
        try {
            pstm = connection.prepareStatement(sql);
            pstm.setString(1, deliveryId);
            pstm.setString(2, passengerId);
            pstm.setString(3, date);
            return pstm.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
