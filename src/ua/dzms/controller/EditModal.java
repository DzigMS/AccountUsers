package ua.dzms.controller;


import javafx.stage.Stage;
import ua.dzms.dao.UserDAO;
import ua.dzms.users.User;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class EditModal extends Modal {

    public void onShow() {
        label.setText("EditModal user");
        User user = (User) label.getScene().getWindow().getUserData();
        inputFirstName.setText(user.getFirstName());
        inputLastName.setText(user.getLastName());
        inputDate.setValue(user.getDateOfBirth());
    }

    @Override
    void onClickOk() {
        User user = (User) label.getScene().getWindow().getUserData();
        user.setFirstName(inputFirstName.getText());
        user.setLastName(inputLastName.getText());
        user.setDateOfBirth(inputDate.getValue());
        UserDAO userDAO = new UserDAO();
        userDAO.updateUser(user);
        ((Stage)label.getScene().getWindow()).close();
    }
}
