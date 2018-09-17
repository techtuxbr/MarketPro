package controller;

import exceptions.NullData;
import facade.ItemFacade;
import factory.OrderFactory;
import helper.NumberHelper;
import helper.PDFHelper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputEvent;
import model.Item;
import model.Order;
import model.OrderItem;

import javax.swing.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;

public class EmployeeDashboardController implements Initializable {

    @FXML private TextField barcodeTextField, amountTextField, productNameField, valueTextField, totalTextField, finalTotal, receivedTextField,paybackTextField;
    @FXML private TableView<OrderItem> orderTable;
    @FXML private TableColumn<OrderItem, String> barcodeColumn, nameColumn;
    @FXML private TableColumn<OrderItem, ?> priceColumn, amountColumn, totalColumn;
    @FXML private DatePicker dateField;
    Item actualItem;
    Order mainOrder;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mainOrder = new OrderFactory().create(10,8,1999,new ArrayList<OrderItem>());
        barcodeColumn.setCellValueFactory(new PropertyValueFactory<>("itemBarcode"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
        totalColumn.setCellValueFactory(new PropertyValueFactory<>("total"));
        updateTable();
    }

    private ObservableList<OrderItem> getOrderItems(){
        return FXCollections.observableList(mainOrder.getItemList());
    }

    private void updateTable(){
        orderTable.setItems(getOrderItems());
    }

    @FXML
    private void searchProduct(ActionEvent event){
        clearFields();
        if(barcodeTextField.getText().isEmpty()){
            JOptionPane.showMessageDialog(null,"Voce precisa preencher o campo de C.de barras");
            clearFields();
            return;
        }
        try {
            Item i = ItemFacade.get(barcodeTextField.getText());
            if(i != null){
                actualItem = i;
                populateFields(i);
            }else{
                JOptionPane.showMessageDialog(null,"Produto nao encontrado!");
                clearFields();
            }
        } catch (NullData nullData) {
            JOptionPane.showMessageDialog(null,"Houve um erro interno durante a pesquisa do produto1");
            clearFields();
        }
    }

    @FXML
    private void setAmountTextField(InputEvent event){
        if(!amountTextField.getText().isEmpty() && !valueTextField.getText().isEmpty() && NumberHelper.isInteger(amountTextField.getText())){
            totalTextField.setText(String.format("%.2f",Integer.parseInt(amountTextField.getText()) * (actualItem.getPrice())));
        }
    }

    private void populateFields(Item i){
        productNameField.setText(i.getName());
        valueTextField.setText(String.format("%.2f",i.getPrice()));
    }

    private void clearFields(){
        productNameField.setText("");
        valueTextField.setText("");
        totalTextField.setText("");
        amountTextField.setText("");
        actualItem = null;
    }

    @FXML private void addProductToOrderList(ActionEvent event){
        if(actualItem != null && NumberHelper.isInteger(amountTextField.getText())){
            int amount = Integer.parseInt(amountTextField.getText());
            if(actualItem.getInStock() < amount){
                JOptionPane.showMessageDialog(null,"Sem estoque para esse pedido, estoque atual: " + actualItem.getInStock());
                return;
            }
            OrderItem oi = new OrderItem(actualItem,amount);
            mainOrder.getItemList().add(oi);
            ItemFacade.decrementStock(actualItem.getBarCode(),amount);
            updateTable();
            finalTotal.setText(String.format("%.2f",mainOrder.getTotal()));
            clearFields();
        }else{
            JOptionPane.showMessageDialog(null,"Preencha todos os campos!");
        }
    }

    @FXML private void cancelItem(ActionEvent event){
        OrderItem oi = orderTable.getSelectionModel().getSelectedItem();
        if( oi != null){
            ItemFacade.incrementStock(oi.getItemBarcode(),oi.getAmount());
            mainOrder.getItemList().remove(orderTable.getSelectionModel().getSelectedIndex());
            updateTable();
            JOptionPane.showMessageDialog(null,"Item removido com sucesso!");
        }
    }

    @FXML private void typingReceived(InputEvent event){
        if(!finalTotal.getText().isEmpty() && NumberHelper.isFloat(receivedTextField.getText())){
            float total = Float.parseFloat(finalTotal.getText());
            float received = Float.parseFloat(receivedTextField.getText());
            if(total > received){
                return;
            }
            float payback = received-total;
            paybackTextField.setText(String.format("%.2f",payback));
        }
    }

    @FXML private void endOrder(ActionEvent event){
        if(dateField.getValue() == null){
            JOptionPane.showMessageDialog(null,"Selecione uma data!");
            return;
        }
        if(NumberHelper.isFloat(receivedTextField.getText()) && NumberHelper.isFloat(finalTotal.getText())){
            float total = Float.parseFloat(finalTotal.getText());
            float received = Float.parseFloat(receivedTextField.getText());
            int day = dateField.getValue().getDayOfMonth();
            int month = dateField.getValue().getMonthValue();
            int year = dateField.getValue().getYear();
            if(total > received){
                JOptionPane.showMessageDialog(null,"Pagamento insuficiente!!");
                return;
            }
            float payback = received-total;
            JOptionPane.showMessageDialog(null,"Compra finalizada com sucesso!");
            paybackTextField.setText(String.format("%.2f",payback));
            JOptionPane.showMessageDialog(null,"Data: " + day + "/"+ month + "/" + year +"\nTotal: " + total + " R$\nRecebido: " + received + " R$\nTroco: " + payback+" R$");
            receivedTextField.setText("");
            finalTotal.setText("");
            paybackTextField.setText("");
            clearFields();
            Calendar c = Calendar.getInstance();
            c.set(Calendar.DAY_OF_MONTH,day);
            c.set(Calendar.MONTH,month);
            c.set(Calendar.YEAR,year);
            mainOrder.setDate(c);
            PDFHelper.writeOrder(mainOrder,total,received,payback);
            mainOrder.getItemList().clear();
            updateTable();
        }else{
            JOptionPane.showMessageDialog(null,"Insira um valor valido!!");
        }
    }
}
