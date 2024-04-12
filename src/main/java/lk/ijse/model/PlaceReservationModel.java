package lk.ijse.model;

import lk.ijse.db.DbConnection;
import lk.ijse.dto.ReservationDto;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;

public class PlaceReservationModel {
    private ReservationModel reservationModel = new ReservationModel();
    private static TrainModel trainModel = new TrainModel();
    private static ReservationDetailModel reservationDetailModel = new ReservationDetailModel();


    public static boolean placeReservation(ReservationDto placeReservationDto) throws SQLException, ClassNotFoundException {
        String reservationId = placeReservationDto.getReservationId();
        LocalDate orderDate = placeReservationDto.getOrderDate();
        String passengerId = placeReservationDto.getPassengerId();

        Connection connection = null;
        try {
            connection = DbConnection.getInstance().getConnection();
            connection.setAutoCommit(false);

            boolean isReservationSaved = ReservationModel.saveOrder(reservationId, passengerId, orderDate);
            if (!isReservationSaved){
              connection.rollback();
               return false;
            }
            TrainModel trainModel1 = new TrainModel();
            boolean b = trainModel1.updateTrains(placeReservationDto.getCartDtos());
            if (!b) {
                connection.rollback();
                return false;
            }
        } catch (SQLException e) {
            connection.rollback();
            return false;
        } finally {
            connection.setAutoCommit(true);
        }
        return true;
    }
}
