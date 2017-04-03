package ua.dzms.dao;

import ua.dzms.users.User;

import java.sql.*;
import java.util.List;

public abstract class DAO<T> {
    Connection connection;

    public DAO() {
        connection = getConnection();
    }

    public DAO(Connection connection) {
        this.connection = connection;
    }

    public abstract List<T> getAll();

    public abstract void addUser(User newUser);

    Connection getConnection() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/useraccount",
                    "root", "admin");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    PreparedStatement getPreparedStatement(String query) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return preparedStatement;
    }

    void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    void closePreparedStatement(PreparedStatement preparedStatement) {
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
