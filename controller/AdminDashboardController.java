package controller;

import helper.SceneHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class AdminDashboardController{
    @FXML protected Button productsButton;
    @FXML protected Button categoriesButton;
    @FXML protected Button brandsButton;
    @FXML protected Button usersButton;

    @FXML protected void productsScene(ActionEvent event){
        SceneHelper.loader.loadScreen(SceneHelper.getAdminProductsScreen());
    }

    @FXML protected void categoriesScene(ActionEvent event){
        SceneHelper.loader.loadScreen(SceneHelper.getAdminCategoriesScreen());
    }

    @FXML protected void brandsScene(ActionEvent event){
        SceneHelper.loader.loadScreen(SceneHelper.getAdminBrandsScreen());
    }

    @FXML protected void usersScene(ActionEvent event){
        SceneHelper.loader.loadScreen(SceneHelper.getAdminUsersScreen());
    }
}
