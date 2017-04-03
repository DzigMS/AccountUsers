package ua.dzms.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ua.dzms.Main;
import ua.dzms.dao.UserDAO;
import ua.dzms.users.User;

import java.net.URL;
import java.util.ResourceBundle;

public class UserController implements Initializable {
    @FXML
    private TableView<User> tableView;
    @FXML
    private TextField idField;
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private DatePicker fromDateField;
    @FXML
    private DatePicker toDateField;
    @FXML
    private TableColumn firstName;
    @FXML
    TableColumn lastName;
    @FXML
    private Button shawAll;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showAllUsers();

        firstName.setCellFactory(TextFieldTableCell.forTableColumn());
        lastName.setCellFactory(TextFieldTableCell.forTableColumn());

    }

    @FXML
    public void showAll(ActionEvent actionEvent) {
        firstNameField.setText("");
        lastNameField.setText("");
        fromDateField.setValue(null);
        toDateField.setValue(null);
        showAllUsers();
    }

    public void addUser(ActionEvent event) {
        Stage stage = newStage(event);
        UserDAO userDAO = new UserDAO();
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/modal.fxml"));

            loader.setController(new AddModal());
            Parent root = loader.load();
            stage.setScene(new Scene(root));

            stage.showAndWait();
            userDAO.addUser((User) stage.getUserData());
            stage.close();
            showAllUsers();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void contains(KeyEvent event) {
        UserDAO userDAO = new UserDAO();
        String query = "SELECT * FROM users WHERE firstName LIKE \'"
                + firstNameField.getText() + "%\'" +
                " AND lastName LIKE \'" + lastNameField.getText() + "%\'";
        if (fromDateField.getValue() != null) {
            if (toDateField.getValue() != null) {
                query += " AND (dateOfBirth BETWEEN \'" + fromDateField.getValue() + "\'" +
                        " AND \'" + toDateField.getValue() + "\')";
            } else {
                query += "AND dateOfBirth >= \'" + fromDateField.getValue() + "\'";
            }
        } else if (toDateField.getValue() != null) {
            query += "AND dateOfBirth <= \'" + toDateField.getValue() + "\'";
        }
        ObservableList<User> data = FXCollections.observableList(userDAO.contains(query));
        tableView.setItems(data);
    }

    private void showAllUsers() {
        UserDAO userDAO = new UserDAO();
        ObservableList<User> data = FXCollections.observableList(userDAO.getAll());
        tableView.setItems(data);
    }

    public void editCommit(TableColumn.CellEditEvent cellEditEvent) {
        String name = (String) cellEditEvent.getNewValue();
        User user = tableView.getSelectionModel().getSelectedItem();
        if (cellEditEvent.getTableColumn().getId().equals("firstName")) {
            user.setFirstName(name);
        } else {
            user.setLastName(name);
        }
    }

    public void editInNewStage(ActionEvent actionEvent) {
        UserDAO userDAO = new UserDAO();
        Stage stage = newStage(actionEvent);

        try {
            User user = tableView.getSelectionModel().getSelectedItem();
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/modal.fxml"));
            EditModal edit = new EditModal();

            loader.setController(edit);
            Parent root = loader.load();

            stage.setScene(new Scene(root));

            stage.setUserData(user);
            edit.onShow();
            stage.showAndWait();
            tableView.refresh();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Stage newStage(ActionEvent actionEvent){
        Stage stage = new Stage();
        stage.setTitle(((Button) actionEvent.getSource()).getText() + " View");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(tableView.getScene().getWindow());

        return stage;
    }


    public void close(ActionEvent actionEvent) {
        ((Stage)tableView.getScene().getWindow()).close();
    }

    public void delete(ActionEvent actionEvent) {
        User user = tableView.getSelectionModel().getSelectedItem();
    }
}
