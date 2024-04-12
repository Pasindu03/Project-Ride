package lk.ijse.model;

import lk.ijse.Tm.ReservationTm;
import lk.ijse.db.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ReservationDetailModel {
    public boolean saveOrderDetails(List<ReservationTm> reservationTmList, String reservationId) throws SQLException {
        for(ReservationTm tm : reservationTmList) {
            if(!saveOrderDetails(reservationId, tm)) {
                return false;
            }
        }
        return true;
    }

    private boolean saveOrderDetails(String reservationId, ReservationTm tm) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO reservation_details VALUES(?, ?, ?, ?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, reservationId);
        pstm.setString(2, tm.getTrainId());
        pstm.setInt(3, tm.getNoOfSeats());
        pstm.setDouble(4, tm.getTicketPrice());

        return pstm.executeUpdate() > 0;
    }
}
