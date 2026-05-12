package com.example.courierms.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class MainFormController {
    public TextField txtUserName;
    public TextField txtPassword;
    public AnchorPane root;

    String getUserName1 = "user1";
    String getPassword1 = "1234";
    String getUserName2 = "user2";
    String getPassword2 = "4321";

    public void loginButtonOnAction(ActionEvent actionEvent) throws IOException {
        String userName = txtUserName.getText().trim();
        String password = txtPassword.getText().trim();

        if (!userName.isEmpty() && !password.isEmpty()) {
            if (userName.equalsIgnoreCase(getUserName1) && password.equals(getPassword1)) {
                // Start mLogin
                Stage window = (Stage) this.root.getScene().getWindow();
                window.setScene(new Scene(FXMLLoader.load(Objects.requireNonNull(this.getClass()
                        .getResource("/com/example/courierms/view/DashBoardForm.fxml")))));
                window.centerOnScreen();
            } else if (userName.equalsIgnoreCase(getUserName2) && password.equals(getPassword2)) {
                // End mLogin
                Stage window = (Stage) this.root.getScene().getWindow();
                window.setScene(new Scene(FXMLLoader.load(Objects.requireNonNull(this.getClass()
                        .getResource("/com/example/courierms/view/DashBoardForm.fxml")))));
                window.centerOnScreen();
            } else {
                new Alert(Alert.AlertType.WARNING, "Try Again!", ButtonType.OK).show();
            }
        } else {
            new Alert(Alert.AlertType.WARNING, "User Name Or Password Empty!", ButtonType.OK).show();
        }
    }
}
