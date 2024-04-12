package lk.ijse.model;

import lk.ijse.db.DbConnection;
import lk.ijse.dto.ScheduleDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ScheduleModel {
    public boolean addSchedule(ScheduleDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO schedule VALUES(?,?,?,?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getScheduleId());
        pstm.setString(2, dto.getTrainId());
        pstm.setString(3, dto.getDeparture());
        pstm.setString(4, dto.getArrival());
        pstm.setString(5, dto.getDepartureTime());
        pstm.setString(6, dto.getArrivalTime());

        return pstm.executeUpdate() > 0;
    }

    public boolean updateSchedule(ScheduleDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "UPDATE schedule SET trainId=?,departure=?,arrival=?,departureTime=?,arrivalTime=? WHERE scheduleId=?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getTrainId());
        pstm.setString(2, dto.getDeparture());
        pstm.setString(3, dto.getArrival());
        pstm.setString(4, dto.getDepartureTime());
        pstm.setString(5, dto.getArrivalTime());

        return pstm.executeUpdate() > 0;
    }

    public boolean deleteSchedule(String scheduleId) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "DELETE FROM schedule WHERE scheduleId=?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, scheduleId);

        return pstm.executeUpdate() > 0;
    }

    public List<ScheduleDto> getAllSchedule() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM schedule";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        ArrayList<ScheduleDto> dtoList = new ArrayList<>();

        while(resultSet.next()) {
            dtoList.add(
                    new ScheduleDto(
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

    public ScheduleDto searchSchedule(String scheduleId) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM schedule WHERE scheduleId=?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, scheduleId);

        ResultSet resultSet = pstm.executeQuery();

        ScheduleDto dto = null;

        if(resultSet.next()) {
            String scheduleId1 = resultSet.getString(1);
            String trainId = resultSet.getString(2);
            String departure = resultSet.getString(3);
            String arrival = resultSet.getString(4);
            String departureTime = resultSet.getString(5);
            String arrivalTime = resultSet.getString(6);

            dto = new ScheduleDto(scheduleId1, trainId, departure, arrival, departureTime, arrivalTime);
        }
        return dto;
    }
}
