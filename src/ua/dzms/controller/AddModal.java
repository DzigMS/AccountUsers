package ua.dzms.controller;

import javafx.stage.Stage;
import ua.dzms.users.User;

import java.time.LocalDate;
import java.util.Date;

public class AddModal extends Modal{
    @Override
    void onClickOk() {
        Stage stage = (Stage)label.getScene().getWindow();
        String firstName = inputFirstName.getText();
        String lastName = inputLastName.getText();
        LocalDate dateOfBirth = inputDate.getValue();
        User user = new User(0 , firstName, lastName, dateOfBirth);

        stage.setUserData(user);
        stage.hide();
    }
}
