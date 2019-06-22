/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;

import Model.Inventory;
import static Model.Inventory.getPart;
import static Model.Inventory.getProduct;
import static Model.Inventory.removePart;
import static Model.Inventory.removeProduct;
import Model.Part;
import Model.Product;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Tony
 */
public class mainScreenController implements Initializable {
     @FXML
    private TableView<Part> aTable;
    @FXML
    private TableColumn<Part, Integer> columnName;
    @FXML
    private TableColumn<Part, String> partName;
    @FXML
    private TableColumn<Part, Integer> inventoryLevel;
    @FXML
    private TableColumn<Part, Double> pricePerUnit;
    @FXML
    private TableView<Product> tableTwo;
    @FXML
    private TableColumn<Product, Integer> productColumnName;
    @FXML
    private TableColumn<Product, String> productPartName;
    @FXML
    private TableColumn<Product, Integer> productInventoryLevel;
    @FXML
    private TableColumn<Product, Double> productPricePerUnit;
    @FXML
   
    private TextField MainPartsSearchField;
    @FXML
    private TextField MainProductsSearchField;
        @FXML TextField productSearchTerm = new TextField(); 
    @FXML TextField searchTerm = new TextField();

    private static Part modifySelectedPart;
    private static int modifySelectedPartIndex;
    private static Product modifySelectedProduct;
    private static int modifySelectedProductIndex;
    
    public static int modifyThisPartIndex() {
        return modifySelectedPartIndex;
    }

    public static int modifyThisProductIndex() {
        return modifySelectedProductIndex;
    }
    /**
     * Initializes the controller class.
     */
    @FXML
    void addPartsOutSourceClickAdd(ActionEvent event) throws IOException {

         FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("addPart.fxml"));
        Parent aTableParentOutSource = loader.load();
        Scene aTableParentPutSourceScene = new Scene(aTableParentOutSource);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(aTableParentPutSourceScene);
        window.show();
    }

    @FXML
    void addProductButtonClicked(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("addProduct.fxml"));
        Parent addProductScreen = loader.load();
        Scene aTableParentPutSourceScene = new Scene(addProductScreen);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(aTableParentPutSourceScene);
        window.show();
    }
    @FXML
    void clickPartsMainModifyScreen(ActionEvent event) throws IOException {

        modifySelectedPart = aTable.getSelectionModel().getSelectedItem();
        modifySelectedPartIndex = getPart().indexOf(modifySelectedPart);
        Parent abc = FXMLLoader.load(getClass().getResource("modifyPart.fxml"));
        Scene scene = new Scene(abc);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
    
    @FXML
    void clickProductsMainModifyScreen(ActionEvent event) throws IOException {

        modifySelectedProduct = tableTwo.getSelectionModel().getSelectedItem();
        modifySelectedProductIndex = getProduct().indexOf(modifySelectedProduct);
        Parent abc = FXMLLoader.load(getClass().getResource("modifyProduct.fxml"));
        Scene scene = new Scene(abc);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
        
    @FXML
    void searchPartsButtonClicked(ActionEvent event) throws IOException {
        String searchPartString = searchTerm.getText();
        int searchPartIndex = -1;
        if (Inventory.searchPart(searchPartString) == -1) {
            Alert searchInputError = new Alert(Alert.AlertType.INFORMATION);
            searchInputError.setTitle("Search Error");
            searchInputError.setHeaderText("The part you searched was not found");
            searchInputError.setContentText("Search term entered does not match any Part");
            searchInputError.showAndWait();
        } else {
            searchPartIndex = Inventory.searchPart(searchPartString);
            Part tempSearchPart = Inventory.getPart().get(searchPartIndex);
            ObservableList<Part> tempSearchPartList = FXCollections.observableArrayList();
            tempSearchPartList.add(tempSearchPart);
            aTable.setItems(tempSearchPartList);
        }
    }
    @FXML
    void undoSearchPartButtonClick(ActionEvent event) {
                updatePartaTable();

    }
    @FXML
    void undoSearchProductButtonClick(ActionEvent event) {
        updateProductTableTwo();

    }
    @FXML
    void searchProductsButtonClicked(ActionEvent event) throws IOException {
        String searchProductString = productSearchTerm.getText();
        int searchProductIndex = -1;
        if (Inventory.searchProduct(searchProductString) == -1) {
            Alert searchInputError = new Alert(Alert.AlertType.INFORMATION);
            searchInputError.setTitle("Search Error");
            searchInputError.setHeaderText("The part you searched was not found");
            searchInputError.setContentText("Search term entered does not match any Part");
            searchInputError.showAndWait();
        } else {
            searchProductIndex = Inventory.searchProduct(searchProductString);
            Product tempSearchProduct = Inventory.getProduct().get(searchProductIndex);
            ObservableList<Product> tempSearchProductList = FXCollections.observableArrayList();
            tempSearchProductList.add(tempSearchProduct);
            tableTwo.setItems(tempSearchProductList);
        }
    }
    
    
    
    @FXML void mainScreenClickDeletePart (ActionEvent event) throws IOException {
        Part part = aTable.getSelectionModel().getSelectedItem();
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Look, a Confirmation Dialog");
        alert.setContentText("Are you sure you want to delete the part " + part.getName()+"?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
    
            removePart(part);
            updatePartaTable();
        } else {
    
        }
        
        
    }
    @FXML void mainScreenClickDeleteProduct(ActionEvent event) throws IOException {
        Product product = tableTwo.getSelectionModel().getSelectedItem();  
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText("Look, a Confirmation Dialog");
            alert.setContentText("Are you sure you want to delete the part ?");
            Optional<ButtonType> result = alert.showAndWait();  
        if (result.get() == ButtonType.OK){
                                    
            removeProduct(product);
            updateProductTableTwo();
        } else {
    
        }
    }
    
    @FXML
    void clickExitButton(ActionEvent event) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.initModality(Modality.NONE);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText("Confirm Exit");
            alert.setContentText("Are you sure you want to exit the application ?");
            Optional<ButtonType> result = alert.showAndWait();  
        if (result.get() == ButtonType.OK) {
         System.exit(0);           
        } else {
            
        }         
    }
      

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // 
        columnName.setCellValueFactory(cellData -> cellData.getValue().partIDProperty().asObject());
        partName.setCellValueFactory(cellData -> cellData.getValue().partNameProperty());
        inventoryLevel.setCellValueFactory(cellData -> cellData.getValue().partInvProperty().asObject());
        pricePerUnit.setCellValueFactory(cellData -> cellData.getValue().partPriceProperty().asObject());
        productColumnName.setCellValueFactory(cellData -> cellData.getValue().productIDProperty().asObject());
        productPartName.setCellValueFactory(cellData -> cellData.getValue().productNameProperty());
        productInventoryLevel.setCellValueFactory(cellData -> cellData.getValue().productInvProperty().asObject());
        productPricePerUnit.setCellValueFactory(cellData -> cellData.getValue().productPriceProperty().asObject());
        updatePartaTable();
        updateProductTableTwo();
    }    
    public void updatePartaTable() {
        aTable.setItems(getPart());
    }

    public void updateProductTableTwo() {
        tableTwo.setItems(getProduct());
    }

}
