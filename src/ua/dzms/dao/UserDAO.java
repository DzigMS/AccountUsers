package ua.dzms.dao;


import ua.dzms.users.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserDAO extends DAO{
    public UserDAO() {
    }

    public UserDAO(Connection connection) {
        super(connection);
    }

    @Override
    public List<User> getAll() {
        List arrayList = new ArrayList();
        PreparedStatement preparedStatement = getPreparedStatement("SELECT * FROM users");
        try {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                Date dateOfBirth = resultSet.getDate("dateOfBirth");
                arrayList.add(new User(id, firstName, lastName, dateOfBirth));
            }

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            closePreparedStatement(preparedStatement);
            closeConnection();
        }
        return arrayList;
    }
}
