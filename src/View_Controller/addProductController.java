/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;
import Model.Part;
import Model.Product;
import Model.Inventory;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import static Model.Inventory.getPart;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Tony
 */
public class addProductController implements Initializable {
        private ObservableList<Part> currentlyAddedParts = FXCollections.observableArrayList();
    private int idOfProduct;
    private String productInputError = new String();
                @FXML TextField addProductaddPartSearchField = new TextField();
                
        @FXML TextField addProductdeletePartSearchField = new TextField();

        @FXML TextField productIdField = new TextField();
        @FXML TextField productNameField = new TextField();
        @FXML TextField productInvField = new TextField();
        @FXML TextField productPriceField = new TextField();
        @FXML TextField productMaxField = new TextField();
        @FXML TextField productMinField = new TextField();
        @FXML private TableColumn<Part, Integer> addProductTableAddPartIdCol;
    @FXML private TableColumn<Part, String> addProductTableAddPartNameCol;
    @FXML private TableColumn<Part, Integer> addProductTableAddPartInvCol;
    @FXML private TableColumn<Part, Double> addProductTableAddPartPriceCol;
@FXML private TableColumn<Part, Integer> addProductTableDeletePartIdCol;
    @FXML private TableColumn<Part, String> addProductTableDeletePartNameCol;
    @FXML private TableColumn<Part, Integer> addProductTableDeletePartInvCol;
    @FXML private TableColumn<Part, Double> addProductTableDeletePartPriceCol;
        //private boolean productOutSourcedBoolean;
    @FXML private TableView<Part> AddProductsTableAdd;
    @FXML private TableView<Part> addProductsTableDelete;

    
        @FXML public void addProductClickSave(ActionEvent event) throws IOException
        {
        String nameOfTheProductInput = productNameField.getText();
        String productInvAmountInput = productInvField.getText();
        String priceCostInput = productPriceField.getText();
        String minimumValueInput = productMinField.getText();
        String maximumValueInput = productMaxField.getText();
        
   /*     Product addedProduct = new Product();

                addedProduct.setProduct_ID(idOfProduct);
                addedProduct.setProductName(nameOfTheProductInput);
                addedProduct.setProductPrice(Double.parseDouble(priceCostInput));
                addedProduct.setProductInventory(Integer.parseInt(productInvAmountInput));
                addedProduct.setProductPartMin(Integer.parseInt(minimumValueInput));
                addedProduct.setProductPartMax(Integer.parseInt(maximumValueInput));
                //addedProduct.setProductPart(currentlyAddedParts);
                //setCurrentProductParts
                                  addedProduct.setCurrentProductParts(currentlyAddedParts);
      
                Inventory.addProduct(addedProduct);    
            Parent tableViewParent = FXMLLoader.load(getClass().getResource("mainScreen.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);    
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();      
        window.setScene(tableViewScene);
        window.show();*/
        //Product newProduct = new Product();
        try {

            productInputError = Product.isProductInputValid(nameOfTheProductInput, Integer.parseInt(minimumValueInput), Integer.parseInt(maximumValueInput), Integer.parseInt(productInvAmountInput),
                    Double.parseDouble(priceCostInput), currentlyAddedParts, productInputError);

            if (productInputError.length() > 0) {

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error Adding Product");
                alert.setHeaderText("Error");
                alert.setContentText(productInputError);
                alert.showAndWait();
                productInputError = "";
            } else {
                System.out.println("Product name: " + nameOfTheProductInput);

                Product addedProduct = new Product();

                addedProduct.setProduct_ID(idOfProduct);
                addedProduct.setProductName(nameOfTheProductInput);
                addedProduct.setProductPrice(Double.parseDouble(priceCostInput));
                addedProduct.setProductInventory(Integer.parseInt(productInvAmountInput));
                addedProduct.setProductPartMin(Integer.parseInt(minimumValueInput));
                addedProduct.setProductPartMax(Integer.parseInt(maximumValueInput));
                addedProduct.setCurrentProductParts(currentlyAddedParts);
                Inventory.addProduct(addedProduct);

                Parent productsSave = FXMLLoader.load(getClass().getResource("mainScreen.fxml"));
                Scene scene = new Scene(productsSave);

                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

                window.setScene(scene);
                window.show();
            }
        } catch (NumberFormatException e) {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error Adding Part!");
            alert.setHeaderText("Error!");
            alert.setContentText("Fields cannot be left blank!");
            alert.showAndWait();
        }
    }
    @FXML
    void addProductClickSearchButton(ActionEvent event) throws IOException {
        String searchPartString = addProductaddPartSearchField.getText();
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
            AddProductsTableAdd.setItems(tempSearchPartList);
        }
    }    
    @FXML
    void addProductClickSearchButtonDeleteTable(ActionEvent event) throws IOException {
        String searchPartString = addProductdeletePartSearchField.getText();
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
            addProductsTableDelete.setItems(tempSearchPartList);
        }
    }   
    @FXML
    void addProductAddPartButtonClicked(ActionEvent event) {
        Part part = AddProductsTableAdd.getSelectionModel().getSelectedItem();
        currentlyAddedParts.add(part);
        currentPartTableViewUpdate();
        
    }
    
            @FXML
    void addProductClickCancel(ActionEvent event) throws IOException {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Cancel Add Product");
        alert.setHeaderText("Cancel Add Product");
        alert.setContentText("Are you sure you want to cancel adding this product?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){   
        Parent addProductScreen = FXMLLoader.load(getClass().getResource("mainScreen.fxml"));
        Scene mainScreen = new Scene(addProductScreen);    
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();      
        window.setScene(mainScreen);
        window.show();
        } else {
    
        }
        
    }
    @FXML
    void addProductDeleteClicked(ActionEvent event) {
        Part part = addProductsTableDelete.getSelectionModel().getSelectedItem();
        
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Look, a Confirmation Dialog");
        alert.setContentText("Are you sure you want to delete this part?");
        Optional<ButtonType> result = alert.showAndWait();
if (result.get() == ButtonType.OK){
    
                currentlyAddedParts.remove(part);

} else {
    
}
        
             
    }
    
    public void updatePartaTable() {
        AddProductsTableAdd.setItems(getPart());
    }
    @FXML
    void addPartClickUndoSearch(ActionEvent event) {
                updatePartaTable();

    }
    @FXML
    void addProductClickUndoSearch(ActionEvent event) {
        currentPartTableViewUpdate();
    }
    public void currentPartTableViewUpdate() {
        addProductsTableDelete.setItems(currentlyAddedParts);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        idOfProduct = Inventory.getProductIDCount();
        productIdField.setText("AUTO GEN: " + idOfProduct);
        //addProductTableAddPartIdCol.setCellValueFactory(new PropertyValueFactory<Part, Integer>("part_ID>"));
        addProductTableAddPartIdCol.setCellValueFactory(cellData -> cellData.getValue().partIDProperty().asObject());
        addProductTableAddPartNameCol.setCellValueFactory(new PropertyValueFactory<Part, String>("Name"));
        addProductTableAddPartInvCol.setCellValueFactory(new PropertyValueFactory<Part, Integer>("inventory"));
        addProductTableAddPartPriceCol.setCellValueFactory(new PropertyValueFactory<Part, Double>("price")); 
        
        //addProductTableDeletePartIdCol.setCellValueFactory(new PropertyValueFactory<Part, Integer>("part_ID>"));
        addProductTableDeletePartIdCol.setCellValueFactory(cellData -> cellData.getValue().partIDProperty().asObject());
        addProductTableDeletePartNameCol.setCellValueFactory(new PropertyValueFactory<Part, String>("Name"));
        addProductTableDeletePartInvCol.setCellValueFactory(new PropertyValueFactory<Part, Integer>("inventory"));
        addProductTableDeletePartPriceCol.setCellValueFactory(new PropertyValueFactory<Part, Double>("price"));  
        updatePartaTable();
        currentPartTableViewUpdate();
    }    
    
}
