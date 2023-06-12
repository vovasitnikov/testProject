package com.example.demo.user.dao;


import com.example.demo.user.model.UserModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class UserDao {

    private final Connection connection;
    private final String GET_USER = "select * from users where id=?";
    private final String GET_ALL_USERS = "select * from users";
    private final String SAVE_USER = "insert into users(name, email) values(?, ?)";
    private final String UDATE_USER = "update users set name = ?, set email = ? where id = ?";
    private final String USER_DELETE = "delete from users where id = ?";

    public UserDao(Connection connection) {
        this.connection = connection;
    }

    public UserModel getUser(Long id) throws SQLException {
        PreparedStatement selectQuery = connection.prepareStatement(GET_USER);
        selectQuery.setLong(1, id);
        ResultSet resultSet = selectQuery.executeQuery();
        UserModel userModel = new UserModel();
        userModel.setName(resultSet.getString("name"));
        userModel.setEmail(resultSet.getString("email"));
        return userModel;
    }

    public List<UserModel> getAllUsers() throws SQLException {
        PreparedStatement selectQuery = connection.prepareStatement(GET_ALL_USERS);
        ResultSet resultSet = selectQuery.executeQuery();
        List<UserModel> userModelList = new ArrayList<>();
        while (resultSet.next()) {
            UserModel userModel = new UserModel();
            userModel.setName(resultSet.getString("name"));
            userModel.setEmail(resultSet.getString("email"));
            userModelList.add(userModel);
        }
        return userModelList;
    }

    public void saveUser(UserModel userModel) throws SQLException {
        PreparedStatement seleqtQuery = connection.prepareStatement(SAVE_USER);
        seleqtQuery.setString(1, userModel.getName());
        seleqtQuery.setString(2, userModel.getEmail());
        seleqtQuery.executeUpdate();
    }

    public void updateUser(UserModel userModel) throws SQLException {
        PreparedStatement seleqtQuery = connection.prepareStatement(UDATE_USER);
        seleqtQuery.setString(1, userModel.getName());
        seleqtQuery.setString(2, userModel.getEmail());
        seleqtQuery.executeUpdate();
    }

    public void deleteUser(Long id) throws SQLException {
        PreparedStatement selectQuery = connection.prepareStatement(USER_DELETE);
        selectQuery.execute();
    }
}
