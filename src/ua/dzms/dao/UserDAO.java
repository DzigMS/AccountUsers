package ua.dzms.dao;


import ua.dzms.users.User;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserDAO extends DAO{
    public UserDAO() {
    }

    public UserDAO(Connection connection) {
        super(connection);
    }

    @Override
    public List<User> getAll() {
        return contains("SELECT * FROM users");
    }

    @Override
    public void addUser(User newUser) {
        String string = "INSERT INTO users (firstName, lastName, dateOfBirth) VALUES (?, ?, ?);";
        PreparedStatement preparedStatement = getPreparedStatement(string);
        try {
            preparedStatement.setString(1, newUser.getFirstName());
            preparedStatement.setString(2, newUser.getLastName());
            preparedStatement.setDate(3, Date.valueOf(newUser.getDateOfBirth()));
            preparedStatement.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            closePreparedStatement(preparedStatement);
            closeConnection();
        }
    }

    public List<User> contains(String query){
        List arrayList = new ArrayList();
        PreparedStatement preparedStatement = getPreparedStatement(query);
        try {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                LocalDate localDate = resultSet.getDate("dateOfBirth").toLocalDate();
                arrayList.add(new User(id, firstName, lastName, localDate));
            }

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            closePreparedStatement(preparedStatement);
            closeConnection();
        }
        return arrayList;
    }

    public void updateUser(User user){
        String string = "UPDATE users SET firstName = ?, lastName = ?, dateOfBirth = ? WHERE ID = ?;";

        PreparedStatement preparedStatement = getPreparedStatement(string);

        try {
            preparedStatement.setInt(4, user.getId());
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setDate(3, Date.valueOf(user.getDateOfBirth()));
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            closePreparedStatement(preparedStatement);
            closeConnection();
        }
    }
}
