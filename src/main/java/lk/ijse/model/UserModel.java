package lk.ijse.model;

import lk.ijse.db.DbConnection;
import lk.ijse.dto.UserDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserModel {
    public static List<UserDto> getAllId() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM user";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        ArrayList<UserDto> dtoList = new ArrayList<>();

        while(resultSet.next()) {
            dtoList.add(
                    new UserDto(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3)
                    )
            );
        }
        return dtoList;
    }

    public static UserDto searchUser(String userName) throws SQLException {
        Connection con = DbConnection.getInstance().getConnection();
        String sql = "SELECT * FROM user WHERE username = ?";
        PreparedStatement pstm = con.prepareStatement(sql);
        pstm.setString(1, userName);

        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            return new UserDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3)
            );
        }else {
            return null;
        }
    }

    public boolean addUser(UserDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO user VALUES(?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getEmail());
        pstm.setString(2, dto.getUsername());
        pstm.setString(3, dto.getPassword());

        return pstm.executeUpdate() > 0;
    }
}
