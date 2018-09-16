package controller;

import exceptions.CategoryIDCollision;
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
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.cell.PropertyValueFactory;
import model.*;

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
    TableColumn<Category, SaleType> saleTypeColumn;
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
    @FXML
    private ComboBox<String> comboBox;
    @FXML
    private ComboBox<String> comboBoxCreation;
    @FXML
    private TextField nameField;
    @FXML
    private TextArea notesField;

    private Category selectedCategory;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        CategoryFactory bf = new CategoryFactory();
        comboBox.setItems(FXCollections.observableArrayList("Quilograma - KG","Litro - L","Unidade - U"));
        comboBoxCreation.setItems(FXCollections.observableArrayList("Quilograma - KG","Litro - L","Unidade - U"));
        comboBox.getSelectionModel().selectFirst();
        idColumn.setCellValueFactory(new PropertyValueFactory<Category, String>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Category, String>("name"));
        saleTypeColumn.setCellValueFactory(new PropertyValueFactory<Category,SaleType>("saleType"));
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
        setUnitiesOrder(category.getSaleType());
        System.out.println(category.getSaleType().toString());
    }

    private void setUnitiesOrder(SaleType type){
        switch (type.getId()){
            case 1:
                comboBox.getSelectionModel().selectFirst();
                break;
            case 2:
                comboBox.getSelectionModel().select(1);
                break;
            default:
                comboBox.getSelectionModel().selectLast();
                break;
        }
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
                SaleType st;
                int type = comboBox.getSelectionModel().getSelectedIndex();
                System.out.println(type);
                if(type == 0){
                    st = new Kilogram();
                }else if(type == 1){
                    st = new Liter();
                }else{
                    st = new Unity();
                }
                CategoryFacade.update(selectedCategory.getId(),nameEditField.getText(),st);
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


    @FXML
    private void searchAction(ActionEvent event){
        System.out.println("Entrou");
        if(searchField.getText().isEmpty()){
            System.out.println("Vazio");
            categoriesTable.setItems(categoriesList());
        }else{
            ObservableList<Category> list = categoriesListSearched(searchField.getText());
            if(list != null){
                categoriesTable.setItems(list);
            }else{
                categoriesTable.getItems().clear();
            }
        }
    }


    @FXML void clearField(ActionEvent event){
        nameField.setText("");
        notesField.setText("");
    }

    @FXML void saveCategoryAction(ActionEvent event){
        CategoryFactory cf = new CategoryFactory();
        try {
            SaleType st;
            int type = comboBoxCreation.getSelectionModel().getSelectedIndex();
            System.out.println(type);
            if(type == 0){
                st = new Kilogram();
            }else if(type == 1){
                st = new Liter();
            }else{
                st = new Unity();
            }
            CategoryFacade.register(cf.create(nameField.getText(),st));
            JOptionPane.showMessageDialog(null,"Categoria criada com sucesso!");
            clearField(event);
            categoriesTable.setItems(categoriesList());
        } catch (CategoryIDCollision err) {
            JOptionPane.showMessageDialog(null,"Uma categoria com o mesmo id já existe!");
        } catch (NullData nullData) {
            JOptionPane.showMessageDialog(null,"Erro durante a criação da categoria, tente novamente!");
        }
    }


    private ObservableList<Category> categoriesList(){
        return FXCollections.observableList(CategoryFacade.getList());
    }


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
                return null;
            }else{
                List<Category> categorySearched = new ArrayList<Category>();
                categorySearched.add(finded);
                return FXCollections.observableList(categorySearched);
            }
        }
    }
}
