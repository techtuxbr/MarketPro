package application;
	
import java.io.IOException;
import java.util.Locale;

import exceptions.UsernameCollision;
import facade.*;
import factory.UserFactory;
import helper.SceneHelper;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import model.Category;
import model.Order;
import singleton.UserListSingleton;

import javax.swing.*;


public class Main extends Application{
	private static Stage stage;
	@Override
	public void start(Stage stage) {
		stage.setTitle("MarketPRO");
		setStage(stage);
		loadScreen(SceneHelper.getLoginScreen());
	}

	public static void setStage(Stage stagep){
		stage = stagep;
	}

	public void loadScreen(String path){
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
			Pane main = loader.load();
			Scene scene = new Scene(main);
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		UserFacade.allocateUsers();
		ItemFacade.allocateItems();
		OrderFacade.allocateOrders();
		CategoryFacade.allocateCategories();
		BrandFacade.allocateBrands();
		// área de testes
		// Fim área de testes
		launch(args);
	}


}
