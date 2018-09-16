package controller;

import exceptions.NullData;
import exceptions.UserNotFound;
import exceptions.UsernameCollision;
import facade.UserFacade;
import factory.UserFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.User;


import javax.swing.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AdminUsersController extends AdminDashboardController implements Initializable {

    @FXML private TextField nameField, usernameField, nameEditField, usernameEditField, searchField;
    @FXML private PasswordField passwordField, passwordField2;
    @FXML private ComboBox<String> roleComboBox;
    @FXML private TableView<User> usersTable;
    @FXML private TableColumn<User, String> idColumn,nameColumn,usernameColumn;
    @FXML private TableColumn<User, ?> typeColumn;
    private User selectedUser;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        roleComboBox.setItems(FXCollections.observableArrayList("Caixa","Gerente"));
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        usersTable.setItems(usersObservable());
    }

    ObservableList<User> usersObservable(){
        return FXCollections.observableList(UserFacade.getList());
    }


    @FXML private void selectedUserAction(ActionEvent event){
        selectedUser = usersTable.getSelectionModel().getSelectedItem();
        nameEditField.setText(selectedUser.getName());
        usernameEditField.setText(selectedUser.getUsername());
    }

    @FXML private void saveEdit(ActionEvent event){
        if(selectedUser != null){
            try {
                UserFacade.update(selectedUser.getId(),nameEditField.getText(),usernameEditField.getText(),selectedUser.getPassword(),selectedUser.getType());
                clearField(event);
                JOptionPane.showMessageDialog(null,"Usuario editado com sucesso!");
                usersTable.setItems(usersObservable());
            } catch (NullData nullData) {
                JOptionPane.showMessageDialog(null,"Entrada inválida");
            } catch (UserNotFound userNotFound) {
                JOptionPane.showMessageDialog(null,"Usuario inválido");
            }
        }else{
            JOptionPane.showMessageDialog(null,"Você precisa selecionar um usuario!");
        }
    }

    @FXML private void saveNewUser(ActionEvent event){
        if(passwordField.getText().equals(passwordField2.getText())){
            if(roleComboBox.getSelectionModel().getSelectedIndex() == 1){
                try {
                    UserFacade.registerUser(new UserFactory().createAdmin(nameField.getText(),usernameField.getText(),passwordField.getText()));
                    clearField(event);
                    JOptionPane.showMessageDialog(null,"Usuário criado com sucesso!");
                } catch (UsernameCollision usernameCollision) {
                    JOptionPane.showMessageDialog(null,"Já existe um usuário com este username");
                }
            }else{
                try {
                    UserFacade.registerUser(new UserFactory().createEmployee(nameField.getText(),usernameField.getText(),passwordField.getText()));
                    clearField(event);
                    JOptionPane.showMessageDialog(null,"Usuário criado com sucesso!");
                } catch (UsernameCollision usernameCollision) {
                    JOptionPane.showMessageDialog(null,"Já existe um usuário com este username");
                }
            }
        }else{
            JOptionPane.showMessageDialog(null,"As senhas não conferem!");
        }
        usersTable.setItems(usersObservable());
    }

    @FXML void searchAction(ActionEvent event){
        if(searchField.getText().isEmpty()){
            usersTable.setItems(usersObservable());
        }else{
            try {
                User u = UserFacade.getByUsername(searchField.getText());
                List<User> users = new ArrayList<User>();
                if(u == null){
                    usersTable.setItems(FXCollections.observableList(users));
                }else{
                    users.add(u);
                    usersTable.setItems(FXCollections.observableList(users));
                }
            } catch (NullData nullData) {
                JOptionPane.showMessageDialog(null,"Houve um erro interno");
            }
        }
    }

    @FXML void deleteUser(ActionEvent event){
        if(selectedUser == null){
            JOptionPane.showMessageDialog(null,"Voce precisa selecionar um usuario!");
        }else{
            try {
                UserFacade.remove(selectedUser.getId());
                clearField(event);
                usersTable.setItems(usersObservable());
                JOptionPane.showMessageDialog(null,"Usuario deletado com sucesso!");
            } catch (NullData nullData) {
                JOptionPane.showMessageDialog(null,"Houve um erro interno");
            }
        }
    }

    @FXML private void clearField(ActionEvent event){
        nameField.setText("");
        usernameField.setText("");
        passwordField.setText("");
        passwordField2.setText("");
        usernameEditField.setText("");
        nameEditField.setText("");
        selectedUser = null;
    }
}
