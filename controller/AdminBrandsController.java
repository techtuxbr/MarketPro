package controller;

import exceptions.BrandIDCollision;
import exceptions.BrandNotFound;
import exceptions.NullData;
import facade.BrandFacade;
import factory.BrandFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.input.MouseEvent;
import model.Brand;

import javax.swing.*;
import javax.xml.soap.Text;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AdminBrandsController extends AdminDashboardController implements Initializable{
    @FXML
    private TableView<Brand> brandsTable;
    @FXML
    private TableColumn<Brand, String> idColumn;
    @FXML
    private TableColumn<Brand, String> nameColumn;
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
    private TextField nameField;
    @FXML
    private TextArea notesField;

    private Brand selectedBrand;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        BrandFactory bf = new BrandFactory();
        idColumn.setCellValueFactory(new PropertyValueFactory<Brand, String>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Brand, String>("name"));
        brandsTable.setItems(brandsList());
    }

    @FXML
    public void brandSelected(ActionEvent event){
        if(brandsTable.getSelectionModel().getSelectedItem() != null){
            populateFields(brandsTable.getSelectionModel().getSelectedItem());
        }
    }

    private void populateFields(Brand brand){
        selectedBrand = brand;
        nameEditField.setText(brand.getName());
        idEditField.setText(brand.getId());
    }

    private void despopulateFields(String message){
        selectedBrand = null;
        nameEditField.setText("");
        idEditField.setText("");
        JOptionPane.showMessageDialog(null,message);
    }

    @FXML
    private void brandEditSaved(ActionEvent event){
        if(selectedBrand != null){
            try {
                BrandFacade.update(selectedBrand.getId(),nameEditField.getText());
                brandsTable.setItems(brandsList());
            } catch (NullData nullData) {
                JOptionPane.showMessageDialog(null,nullData.toString());
            } catch (BrandNotFound brandNotFound) {
                JOptionPane.showMessageDialog(null,"Marca não encontrada");
            }
            despopulateFields("Marca editada");
        }else{
            JOptionPane.showMessageDialog(null,"Você precisa selecionar uma marca!");
        }
    }

    @FXML
    private void brandEditDeleted(ActionEvent event){
        if(selectedBrand != null){
            try {
                BrandFacade.remove(selectedBrand.getId());
                brandsTable.setItems(brandsList());
            } catch (NullData nullData) {
                JOptionPane.showMessageDialog(null,nullData.toString());
            }
            despopulateFields("Marca: " + selectedBrand.getName() + " deletada!");
        }else{
            JOptionPane.showMessageDialog(null,"Você precisa selecionar uma marca!");
        }
    }

    @FXML
    private void searchAction(ActionEvent event){
        if(searchField.getText().isEmpty()){
            brandsTable.setItems(brandsList());
        }else{
            ObservableList<Brand> list = brandListSearched(searchField.getText());
            if(list != null){
                brandsTable.setItems(list);
            }else{
                brandsTable.getItems().clear();
            }
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

    private ObservableList<Brand> brandsList(){
        return FXCollections.observableList(BrandFacade.getList());
    }

    private ObservableList<Brand> brandListSearched(String query){
        Brand finded = null;
        try {
            finded = BrandFacade.get(query);
            if(finded == null){
                finded = BrandFacade.getByName(query);
            }
        } catch (NullData nullData) {
            nullData.printStackTrace();
        }finally {
            if(finded == null){
                return null;
            }else{
                List<Brand> brandSearched = new ArrayList<Brand>();
                brandSearched.add(finded);
                return FXCollections.observableList(brandSearched);
            }
        }
    }
}
