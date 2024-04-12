package lk.ijse.model;

import lk.ijse.db.DbConnection;
import lk.ijse.dto.AttendanceDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AttendanceModel {
    public boolean addAttendance(AttendanceDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO attendance VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1, dto.getAttendanceId());
        pstm.setObject(2, dto.getEmpId());
        pstm.setObject(3, dto.getMonth());
        pstm.setObject(4, dto.getDate());
        pstm.setObject(5, dto.getStatus());

        return pstm.executeUpdate() > 0;
    }


    public boolean deleteAttendance(String attId) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "DELETE FROM attendance WHERE attendanceId = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1, attId);

        return pstm.executeUpdate() > 0;
    }

    public boolean updateAttendance(AttendanceDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "UPDATE attendance SET empId = ?, month = ?, date = ?, status = ? WHERE attendanceId = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1, dto.getEmpId());
        pstm.setObject(2, dto.getMonth());
        pstm.setObject(3, dto.getDate());
        pstm.setObject(4, dto.getStatus());
        pstm.setObject(5, dto.getAttendanceId());

        return pstm.executeUpdate() > 0;
    }

    public AttendanceDto searchAttendance(String attId) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM attendance WHERE attendanceId = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1, attId);

        ResultSet resultSet = pstm.executeQuery();

        AttendanceDto dto = null;

        if (resultSet.next()) {
            String attendanceId = resultSet.getString(1);
            String empId = resultSet.getString(2);
            String month = resultSet.getString(3);
            String date = resultSet.getString(4);
            String status = resultSet.getString(5);

            dto = new AttendanceDto(attendanceId, empId, month, date, status);
        }

        return dto;
    }

    public List<AttendanceDto> getAllAttendance() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM attendance";
        PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<AttendanceDto> dtoList = new ArrayList<>();

        while (resultSet.next()) {
            dtoList.add(
                    new AttendanceDto(
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
}
