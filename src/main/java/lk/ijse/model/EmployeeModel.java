package lk.ijse.model;

import lk.ijse.db.DbConnection;
import lk.ijse.dto.EmployeeDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeModel {
    public boolean addEmployee(EmployeeDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO employee VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1, dto.getEmpId());
        pstm.setObject(2, dto.getName());
        pstm.setObject(3, dto.getAge());
        pstm.setObject(4, dto.getGender());
        pstm.setObject(5, dto.getAddress());
        pstm.setObject(6, dto.getContact());

        return pstm.executeUpdate() > 0;
    }

    public boolean updateEmployee(EmployeeDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "UPDATE employee SET name = ?, age = ?, gender = ?, address = ?, contact = ? WHERE empId = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1, dto.getName());
        pstm.setObject(2, dto.getAge());
        pstm.setObject(3, dto.getGender());
        pstm.setObject(4, dto.getAddress());
        pstm.setObject(5, dto.getContact());
        pstm.setObject(6, dto.getEmpId());

        return pstm.executeUpdate() > 0;
    }

    public boolean deleteEmployee(String id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "DELETE FROM employee WHERE empId = ?";
        try {
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setObject(1, id);

            return pstm.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public List<EmployeeDto> getAllEmployees() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM employee";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        ArrayList<EmployeeDto> dtos = new ArrayList<>();

        while (resultSet.next()) {
            dtos.add(
                    new EmployeeDto(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6)
                    )
            );
        }
        return dtos;
    }
}
