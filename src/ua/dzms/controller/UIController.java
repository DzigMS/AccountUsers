package ua.dzms.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import ua.dzms.dao.UserDAO;
import ua.dzms.users.User;



public class UIController {
    @FXML
    private TableView<User> tableView;

    @FXML
    public void showAll(ActionEvent actionEvent) {
        UserDAO userDAO = new UserDAO();
        ObservableList<User> data = FXCollections.observableList(userDAO.getAll());
        tableView.setItems(data);
    }

    public void addUser(ActionEvent event) {
//        User user = new User();
    }

}
