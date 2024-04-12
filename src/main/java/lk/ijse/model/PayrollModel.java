package lk.ijse.model;

import lk.ijse.db.DbConnection;
import lk.ijse.dto.PayrollDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PayrollModel {

    public boolean addPayroll(PayrollDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO Payroll VALUES(?,?,?,?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1, dto.getEmployeeId());
        pstm.setObject(2, dto.getSalaryId());
        pstm.setObject(3, dto.getMonth());
        pstm.setObject(4, dto.getDate());
        pstm.setObject(5, dto.getStatus());
        pstm.setObject(6, dto.getAmount());

        return pstm.executeUpdate() > 0;
    }


    public boolean deletePayroll(String salaryID) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "DELETE FROM payroll WHERE salaryId = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1,salaryID);

        return pstm.executeUpdate() > 0;
    }


    public boolean updatePayroll(PayrollDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "UPDATE payroll SET employeeId = ?, month = ?, date = ?, status = ?, amount = ? WHERE salaryId = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1, dto.getEmployeeId());
        pstm.setObject(2, dto.getMonth());
        pstm.setObject(3, dto.getDate());
        pstm.setObject(4, dto.getStatus());
        pstm.setObject(5, dto.getAmount());
        pstm.setObject(6, dto.getSalaryId());

        return pstm.executeUpdate() > 0;
    }

    public List<PayrollDto> getAllPayroll() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM payroll";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        ArrayList<PayrollDto> dtoList = new ArrayList<>();

        while (resultSet.next()){
            dtoList.add(
                    new PayrollDto(
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
}
