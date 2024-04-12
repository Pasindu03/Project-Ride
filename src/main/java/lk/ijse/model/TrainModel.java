package lk.ijse.model;

import lk.ijse.db.DbConnection;
import lk.ijse.dto.CartDto;
import lk.ijse.dto.CartDto2;
import lk.ijse.dto.TrainDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TrainModel {
    public static List<TrainDto> getAllTrain() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM trains";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        ArrayList<TrainDto> dtoList = new ArrayList<>();

        while(resultSet.next()) {
            dtoList.add(
                    new TrainDto(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(5)
                    )
            );
        }
        return dtoList;
    }

    public static void getCountTrain() {
    }

    public boolean addTrain(TrainDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO trains VALUES(?,?,?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getTrainId());
        pstm.setString(2, dto.getType());
        pstm.setString(3, dto.getNoOfSeats());
        pstm.setString(4, dto.getStatus());
        pstm.setString(5, dto.getTicketPrice());

        return pstm.executeUpdate() > 0;
    }


    public boolean updateTrains(List<CartDto> cartDtos) throws SQLException {
        for(CartDto tm : cartDtos) {
            System.out.println("Train Model " + tm);
            if(!updateQty(tm.getTrainId(), tm.getNoOfSeats())) {
                return false;
            }
        }
        return true;
    }

    public boolean updatesTrains(List<CartDto2> DeliveryTmList) throws SQLException {
        return true;
    }

    public boolean updateQty(String trainId, int seats) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "UPDATE trains SET noOfSeats = noOfSeats - ? WHERE trainId = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setInt(1, seats);
        pstm.setString(2, trainId);

        return pstm.executeUpdate() > 0; //false
    }

    public boolean updateTrain(TrainDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "UPDATE trains SET type=?,noOfSeats=?,status=? ticketPrice=? WHERE trainId=?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getType());
        pstm.setString(2, dto.getNoOfSeats());
        pstm.setString(3, dto.getStatus());
        pstm.setString(4, dto.getTicketPrice());
        pstm.setString(5, dto.getTrainId());

        return pstm.executeUpdate() > 0;
    }

    public boolean deleteTrain(String id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "DELETE FROM trains WHERE trainId=?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, id);

        return pstm.executeUpdate() > 0;
    }

    public TrainDto searchTrain(String id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM trains WHERE trainId=?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, id);

        ResultSet resultSet = pstm.executeQuery();

        TrainDto dto = null;

        if(resultSet.next()) {
            String trainId = resultSet.getString(1);
            String type = resultSet.getString(2);
            String noOfSeats = resultSet.getString(3);
            String status = resultSet.getString(4);
            String ticketPrice = resultSet.getString(5);

            dto = new TrainDto(trainId, type, noOfSeats, status, ticketPrice);
        }
        return dto;
    }
}
