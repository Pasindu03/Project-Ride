package lk.ijse.model;

import lk.ijse.db.DbConnection;
import lk.ijse.dto.PassengerDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PassengerModel {


    public static List<PassengerDto> getAllPassengerId() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT passengerId FROM passenger";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        ArrayList<PassengerDto> dtoList = new ArrayList<>();

        while(resultSet.next()) {
            dtoList.add(
                    new PassengerDto(
                            resultSet.getString(1)
                    )
            );
        }
        return dtoList;
    }

    public List<PassengerDto> getAllPassengers() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM passenger";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        ArrayList<PassengerDto> dtoList = new ArrayList<>();

        while(resultSet.next()) {
            dtoList.add(
                    new PassengerDto(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(5),
                            resultSet.getString(6)
                    )
            );
        }
        return dtoList;
    }

    public boolean addPassenger(PassengerDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO passenger VALUES(?,?,?,?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getId());
        pstm.setString(2, dto.getName());
        pstm.setString(3, dto.getAge());
        pstm.setString(4, dto.getGender());
        pstm.setString(5, dto.getAddress());
        pstm.setString(6, dto.getContact());

        return pstm.executeUpdate() > 0;
    }

    public boolean updatePassenger(PassengerDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "UPDATE passenger SET name=?,age=?,gender=?,address=?,contact=? WHERE passengerId=?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getName());
        pstm.setString(2, dto.getAge());
        pstm.setString(3, dto.getGender());
        pstm.setString(4, dto.getAddress());
        pstm.setString(5, dto.getContact());
        pstm.setString(6, dto.getId());

        return pstm.executeUpdate() > 0;
    }

    public boolean deletePassenger(String id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "DELETE FROM passenger WHERE passengerId=?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, id);

        return pstm.executeUpdate() > 0;
    }

    public PassengerDto searchPassenger(String id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM passenger WHERE passengerId=?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, id);

        ResultSet resultSet = pstm.executeQuery();

        PassengerDto dto = null;

       if (resultSet.next()) {
                    String passengerId = resultSet.getString(1);
                    String name = resultSet.getString(2);
                    String age = resultSet.getString(3);
                    String gender = resultSet.getString(4);
                    String address = resultSet.getString(5);
                    String contact = resultSet.getString(6);

                    dto = new PassengerDto(passengerId, name, age, gender, address, contact);
        }
       return dto;
    }
}
