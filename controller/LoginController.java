package controller;

import application.Main;
import exceptions.WrongPassword;
import exceptions.WrongUsername;
import facade.UserFacade;
import helper.SceneHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.security.Principal;
import java.util.Locale;

public class LoginController {
    @FXML
    protected TextField usernameField;
    @FXML
    protected PasswordField passwordField;
    @FXML
    private void loginButtonClicked(ActionEvent event) {
        try {
            UserFacade.authUser(usernameField.getText(),passwordField.getText());
            if(UserFacade.isLogged() && UserFacade.isAdmin()){
                SceneHelper.loader.loadScreen(SceneHelper.getAdminDashboardScreen());
            }else if(UserFacade.isLogged()){
                SceneHelper.loader.loadScreen(SceneHelper.getEmployeeDashboardScreen());
            }
        } catch (WrongPassword wrongPassword) {
            JOptionPane.showMessageDialog(null,"Senha incorreta");
        } catch (WrongUsername wrongUsername) {
            JOptionPane.showMessageDialog(null,"Usuario nao existente");
        }
    }
}
