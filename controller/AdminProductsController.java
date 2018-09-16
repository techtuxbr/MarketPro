package controller;

import exceptions.InvalidNegativeValue;
import exceptions.ItemNotFound;
import exceptions.NullData;
import exceptions.ProductIDCollision;
import facade.BrandFacade;
import facade.CategoryFacade;
import facade.ItemFacade;
import factory.ItemFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Brand;
import model.Category;
import model.Item;
import singleton.ItemListSingleton;

import javax.swing.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AdminProductsController extends AdminDashboardController implements Initializable {
    /*@FXML
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
    */
    @FXML
    private TableView<Item> productsTable;
    @FXML
    private TableColumn<Item,String> barcodeColumn;
    @FXML
    private TableColumn<Item, String> nameColumn;
    @FXML
    private TableColumn<Item, ?> priceColumn;
    @FXML
    private TableColumn<Item, ?> inStockColumn;
    @FXML
    private TableColumn<Item, Brand> brandColumn;
    @FXML
    private TableColumn<Item, Category> categoryColumn;
    @FXML
    private TextField nameEditField;
    @FXML
    private TextField priceEditField;
    @FXML
    private TextField inStockEditField;

    @FXML
    private TextField nameField;
    @FXML
    private ComboBox<Category> categoriesComboBox;
    @FXML
    private ComboBox<Brand> brandsComboBox;
    @FXML
    private ComboBox<Category> categoriesEditComboBox;
    @FXML
    private ComboBox<Brand> brandsEditComboBox;
    @FXML
    private TextField priceField;
    @FXML
    private TextField inStockField;
    @FXML
    private TextArea notesField;
    @FXML
    private TextField searchField;

    private Item productSelected;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<Category> categories = FXCollections.observableList(CategoryFacade.getList());
        ObservableList<Brand> brands = FXCollections.observableList(BrandFacade.getList());
        categoriesComboBox.setItems(categories);
        brandsComboBox.setItems(brands);
        categoriesEditComboBox.setItems(categories);
        brandsEditComboBox.setItems(brands);

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        inStockColumn.setCellValueFactory(new PropertyValueFactory<>("inStock"));
        brandColumn.setCellValueFactory(new PropertyValueFactory<>("brand"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        barcodeColumn.setCellValueFactory(new PropertyValueFactory<>("barCode"));
        productsTable.setItems(itemsObservable());
    }

    @FXML
    public void saveProduct(ActionEvent event){
        try {
            ItemFacade.register(new ItemFactory().create(nameField.getText(),brandsComboBox.getSelectionModel().getSelectedItem(),Integer.parseInt(inStockField.getText()),Float.parseFloat(priceField.getText()),categoriesComboBox.getSelectionModel().getSelectedItem()));
            JOptionPane.showMessageDialog(null,"Produto criado com sucesso!");
            clearFields(event);
        } catch (ProductIDCollision productIDCollision) {
            JOptionPane.showMessageDialog(null,"Colisão de código de barras");
        } catch (NullData nullData) {
            JOptionPane.showMessageDialog(null,"Erro ao criar o produto");
        }
        productsTable.setItems(itemsObservable());
    }

    @FXML
    public void clearFields(ActionEvent event){
        nameField.setText("");
        priceField.setText("");
        inStockField.setText("");
        notesField.setText("");
        nameEditField.setText("");
        priceEditField.setText("");
        inStockEditField.setText("");
        productSelected = null;
    }

    @FXML
    public void productSelected(ActionEvent event){
        if(productsTable.getSelectionModel().getSelectedItem() != null){
            populateFields(productsTable.getSelectionModel().getSelectedItem());
        }
    }

    private void populateFields(Item item){
        productSelected = item;
        nameEditField.setText(item.getName());
        priceEditField.setText(String.format("%.2f",item.getPrice()));
        inStockEditField.setText(String.valueOf(item.getInStock()));
        for(int i = 0; i < brandsEditComboBox.getItems().size();i++){
            if(brandsEditComboBox.getItems().get(i).getName().equals(item.getBrand().getName())){
                brandsEditComboBox.getSelectionModel().select(i);
                break;
            }
        }
        for(int i = 0; i < categoriesEditComboBox.getItems().size();i++){
            if(categoriesEditComboBox.getItems().get(i).getName().equals(item.getType().getName())){
                categoriesEditComboBox.getSelectionModel().select(i);
                break;
            }
        }

    }

    @FXML
    private void saveEditProduct(ActionEvent event){
        if(productSelected != null){
            try {
                ItemFacade.update(productSelected.getBarCode(),nameEditField.getText(),brandsEditComboBox.getSelectionModel().getSelectedItem(),Integer.parseInt(inStockEditField.getText()),Float.parseFloat(priceEditField.getText()),categoriesEditComboBox.getSelectionModel().getSelectedItem());
                JOptionPane.showMessageDialog(null,"Produto editado!");
            } catch (NullData nullData) {
                JOptionPane.showMessageDialog(null,"Valores inválidos");
            } catch (ItemNotFound itemNotFound) {
                JOptionPane.showMessageDialog(null,"Colisão de código de barras");
            } catch (InvalidNegativeValue invalidNegativeValue) {
                JOptionPane.showMessageDialog(null,"Impossível receber valores negativos");
            }
            productsTable.setItems(itemsObservable());
            clearFields(event);
        }else{
            JOptionPane.showMessageDialog(null,"Você precisa selecionar um produto!");
        }
    }

    @FXML
    private void deleteProduct(ActionEvent event){
        if(productSelected != null){
            try {
                ItemFacade.remove(productSelected.getBarCode());
            } catch (NullData nullData) {
                JOptionPane.showMessageDialog(null,"Valores inválidos");
            }
            clearFields(event);
            productsTable.setItems(itemsObservable());
        }else{
            JOptionPane.showMessageDialog(null,"Você precisa selecionar um produto!");
        }
    }

    @FXML
    private void searchAction(ActionEvent event){
        System.out.println("AQUU");
        if(searchField.getText().isEmpty()){
            productsTable.setItems(itemsObservable());
        }else{
            ObservableList<Item> list = productsListSearched(searchField.getText());
            if(list != null){
                productsTable.setItems(list);
            }else{
                productsTable.getItems().clear();
            }
        }
    }

    private ObservableList<Item> productsListSearched(String query){
        Item finded = null;
        List<Item> findeds = new ArrayList<>();
        try {
            finded = ItemFacade.get(query);
            if(finded == null){
                findeds = ItemFacade.getByBrand(query);
            }
        } catch (NullData nullData) {
            nullData.printStackTrace();
        }finally {
            if(finded == null && findeds.size() == 0){
                return null;
            }
            else if(finded != null){
                List<Item> itemSearched = new ArrayList<Item>();
                itemSearched.add(finded);
                return FXCollections.observableList(itemSearched);
            }else{
                return FXCollections.observableList(findeds);
            }
        }
    }

    private ObservableList<Item> itemsObservable(){
        return FXCollections.observableList(ItemFacade.getList());
    }

}
