package application;
	
import java.io.IOException;
import java.util.Locale;

import exceptions.NullData;
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
import model.User;
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
		// Gerando admin automáticamente... para testes!
		try {
			User u = UserFacade.getByUsername("admin");
			if(u == null){
				UserFacade.registerUser(new UserFactory().createAdmin("admin","admin","admin"));
			}
		} catch (NullData nullData) {
			// Ignorar
			//nullData.printStackTrace();
		} catch (UsernameCollision usernameCollision) {
			// Usuário já existe
		}
		// Fim área de testes
		launch(args);
	}


}
