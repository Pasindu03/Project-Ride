package lk.ijse.model;

import lk.ijse.db.DbConnection;
import lk.ijse.dto.CartDto2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class DeliveryDetailModel {
    public static boolean saveOrderDetails(List<CartDto2> deliveryTmList, String deliveryId) throws SQLException {
        for (CartDto2 tm : deliveryTmList) {
            if (!saveOrderDetails(deliveryId, tm)) {
                return false;
            }
        }
        return true;
    }

    private static boolean saveOrderDetails(String deliveryId, CartDto2 tm) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO delivery_details VALUES(?, ?, ?, ?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, deliveryId);
        pstm.setString(2, tm.getTrainId());
        pstm.setInt(3, (int) tm.getWeight());
        pstm.setDouble(4, tm.getPrice());

        return pstm.executeUpdate() > 0;
    }
    }
