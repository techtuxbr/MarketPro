package controller;

import exceptions.CategoryNotFound;
import exceptions.NullData;
import facade.CategoryFacade;
import factory.CategoryFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Category;

import javax.swing.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AdminCategoriesController extends AdminDashboardController implements Initializable {
    @FXML
    private TableView<Category> categoriesTable;
    @FXML
    private TableColumn<Category, String> idColumn;
    @FXML
    private TableColumn<Category, String> nameColumn;
    @FXML
    private Button editButton;
    @FXML
    private TextField nameEditField;
    @FXML
    private TextField idEditField;
    @FXML
    private Button searchButton;
    @FXML
    private TextField searchField;
    /*
    @FXML
    private TextField nameField;
    @FXML
    private TextArea notesField;
    */
    private Category selectedCategory;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        CategoryFactory bf = new CategoryFactory();
        idColumn.setCellValueFactory(new PropertyValueFactory<Category, String>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Category, String>("name"));
        categoriesTable.setItems(categoriesList());
    }

    @FXML
    public void categorySelected(ActionEvent event){
        if(categoriesTable.getSelectionModel().getSelectedItem() != null){
            populateFields(categoriesTable.getSelectionModel().getSelectedItem());
        }
    }

    private void populateFields(Category category){
        selectedCategory = category;
        nameEditField.setText(category.getName());
        idEditField.setText(category.getId());
    }

    private void despopulateFields(String message){
        selectedCategory = null;
        nameEditField.setText("");
        idEditField.setText("");
        JOptionPane.showMessageDialog(null,message);
    }

    @FXML
    private void categoryEditSaved(ActionEvent event){
        if(selectedCategory != null){
            try {
                CategoryFacade.update(selectedCategory.getId(),nameEditField.getText(),selectedCategory.getSaleType());
                categoriesTable.setItems(categoriesList());
            } catch (NullData nullData) {
                JOptionPane.showMessageDialog(null,nullData.toString());
            } catch (CategoryNotFound brandNotFound) {
                JOptionPane.showMessageDialog(null,"Categoria não encontrada");
            }
            despopulateFields("Categoria editada");
        }else{
            JOptionPane.showMessageDialog(null,"Você precisa selecionar uma categoria!");
        }
    }

    @FXML
    private void categoryEditDeleted(ActionEvent event){
        if(selectedCategory != null){
            try {
                CategoryFacade.remove(selectedCategory.getId());
                categoriesTable.setItems(categoriesList());
            } catch (NullData nullData) {
                JOptionPane.showMessageDialog(null,nullData.toString());
            }
            despopulateFields("Categoria: " + selectedCategory.getName() + " deletada!");
        }else{
            JOptionPane.showMessageDialog(null,"Você precisa selecionar uma categoria!");
        }
    }

    /*
    @FXML
    private void searchAction(ActionEvent event){
        if(searchField.getText().isEmpty()){
            brandsTable.setItems(brandsList());
        }else{
            brandsTable.setItems(brandListSearched(searchField.getText()));
        }
    }

    @FXML void clearField(ActionEvent event){
        nameField.setText("");
        notesField.setText("");
    }

    @FXML void saveBrandAction(ActionEvent event){
        BrandFactory bf = new BrandFactory();
        try {
            BrandFacade.register(bf.create(nameField.getText()));
            JOptionPane.showMessageDialog(null,"Marca criada com sucesso!");
            clearField(event);
            brandsTable.setItems(brandsList());
        } catch (BrandIDCollision brandIDCollision) {
            JOptionPane.showMessageDialog(null,"Uma marca com o mesmo id já existe!");
        } catch (NullData nullData) {
            JOptionPane.showMessageDialog(null,"Erro durante a criação da marca, tente novamente!");
        }
    }

*/
    private ObservableList<Category> categoriesList(){
        return FXCollections.observableList(CategoryFacade.getList());
    }

    /*
    private ObservableList<Category> categoriesListSearched(String query){
        Category finded = null;
        try {
            finded = CategoryFacade.get(query);
            if(finded == null){
                finded = CategoryFacade.getByName(query);
            }
        } catch (NullData nullData) {
            nullData.printStackTrace();
        }finally {
            if(finded == null){
                return FXCollections.observableList(null);
            }else{
                List<Brand> brandSearched = new ArrayList<Brand>();
                brandSearched.add(finded);
                return FXCollections.observableList(brandSearched);
            }
        }
    }
    */
}
