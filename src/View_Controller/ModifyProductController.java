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
import static Model.Inventory.getProduct;
import static View_Controller.mainScreenController.modifyThisPartIndex;
import static View_Controller.mainScreenController.modifyThisProductIndex;
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
public class ModifyProductController implements Initializable {
        private ObservableList<Part> currentlyAddedParts = FXCollections.observableArrayList();
    private int idOfProduct;
        @FXML TextField modifyProductIdField = new TextField();    
        @FXML TextField modifyProductNameField = new TextField();    
        @FXML TextField modifyProductInvField = new TextField();    
        //@FXML TextField idAmountModify = new TextField();    
        @FXML TextField modifyProductMinField = new TextField();
        @FXML TextField modifyProductMaxField = new TextField();       
        @FXML TextField modifyProductPriceField = new TextField();
        @FXML TextField modifyProductAddPartSearchTextfield = new TextField();
        @FXML TextField modifyProductDeletePartSearchTextfield = new TextField();

    @FXML private TableColumn<Part, Integer> ModifyProductTableAddPartIdCol;
    @FXML private TableColumn<Part, String> ModifyProductTableAddPartNameCol;
    @FXML private TableColumn<Part, Integer> ModifyProductTableAddPartInvCol;
    @FXML private TableColumn<Part, Double> ModifyProductTableAddPartPriceCol;
    
    @FXML private TableColumn<Part, Integer> ModifyProductTableDeletePartIdCol;
    @FXML private TableColumn<Part, String> ModifyProductTableDeletePartNameCol;
    @FXML private TableColumn<Part, Integer> ModifyProductTableDeletePartInvCol;
    @FXML private TableColumn<Part, Double> ModifyProductTableDeletePartPriceCol;

        //private boolean productOutSourcedBoolean;
    @FXML private TableView<Part> modifyAddProductsTableAdd;
    @FXML private TableView<Part> modifyDeleteProductsTableDelete;
        int productIndex = modifyThisProductIndex();
        private String productStringError = new String();

  
        @FXML public void modifyAddProductClickSave(ActionEvent event) throws IOException
        {
        String nameOfTheProductInput = modifyProductNameField.getText();
        String productInvAmountInput = modifyProductInvField.getText();
        String priceCostInput = modifyProductPriceField.getText();
        String minimumValueInput = modifyProductMinField.getText();
        String maximumValueInput = modifyProductMaxField.getText();
        try {
            productStringError = Product.isProductInputValid(nameOfTheProductInput, Integer.parseInt(minimumValueInput), Integer.parseInt(maximumValueInput), Integer.parseInt(productInvAmountInput),
                    Double.parseDouble(priceCostInput), currentlyAddedParts, productStringError);
            if (productStringError.length() > 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error Adding Product!");
                alert.setHeaderText("Error!");
                alert.setContentText(productStringError);
                alert.showAndWait();
            } else {
                Product addedProduct = new Product();
                addedProduct.setProduct_ID(idOfProduct);
                addedProduct.setProductName(nameOfTheProductInput);
                addedProduct.setProductPrice(Double.parseDouble(priceCostInput));
                addedProduct.setProductInventory(Integer.parseInt(productInvAmountInput));
                addedProduct.setProductPartMin(Integer.parseInt(minimumValueInput));
                addedProduct.setProductPartMax(Integer.parseInt(maximumValueInput));
                addedProduct.setCurrentProductParts(currentlyAddedParts);
                Inventory.updateProduct(productIndex, addedProduct);    
            Parent tableViewParent = FXMLLoader.load(getClass().getResource("mainScreen.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);    
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();      
        window.setScene(tableViewScene);
        window.show();
        
            
            } 
        } catch (NumberFormatException e) {
            System.out.println("Blank Field");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error Adding Part");
            alert.setHeaderText("Error!");
            alert.setContentText("Form cannot contain blank fields!");
            alert.showAndWait();
        }
                
        }
        @FXML
    void modifyProductAddPartButtonClicked(ActionEvent event) {
        Part part = modifyAddProductsTableAdd.getSelectionModel().getSelectedItem();
        currentlyAddedParts.add(part);
        currentPartTableViewUpdate();
        
    }
    @FXML
    void modifyButtonSearchButtonDeletePartClicked(ActionEvent event) {
        String searchPartString = modifyProductDeletePartSearchTextfield.getText();
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
            modifyDeleteProductsTableDelete.setItems(tempSearchPartList);
        }
    }
    @FXML
    void modifyButtonSearchButtonAddPartClicked(ActionEvent event) {
        String searchPartString = modifyProductAddPartSearchTextfield.getText();
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
            modifyAddProductsTableAdd.setItems(tempSearchPartList);
        }
    }
    @FXML public void modifyAddProductClickCancel(ActionEvent event) throws IOException{
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Cancellation Alert");
        alert.setContentText("Are you sure you want to cancel modifying this part?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            Parent tableViewParent = FXMLLoader.load(getClass().getResource("mainScreen.fxml"));
            Scene tableViewScene = new Scene(tableViewParent);    
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();      
            window.setScene(tableViewScene);
            window.show();
        } else {
    
        }
    }
    @FXML
    void modifyAddPartClickUndoSearch(ActionEvent event) {
        updatePartaTable();

    }
    
    @FXML
    void modifyDeletePartClickUndoSearch(ActionEvent event) {
        currentPartTableViewUpdate();
    }
    @FXML
    void modifyProductClickDelete(ActionEvent event) {
        Part part = modifyDeleteProductsTableDelete.getSelectionModel().getSelectedItem();
        
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Look, a Confirmation Dialog");
        alert.setContentText("Are you sure you want to delete this part?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
    
                currentlyAddedParts.remove(part);

        }     else {
    
            }
        }
    public void updatePartaTable() {
        modifyAddProductsTableAdd.setItems(getPart());
    }
    public void currentPartTableViewUpdate() {
        modifyDeleteProductsTableDelete.setItems(currentlyAddedParts);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Product product = getProduct().get(productIndex);
        idOfProduct = getProduct().get(productIndex).getProduct_ID();
        modifyProductIdField.setText("Product ID autoset to: " + idOfProduct);
        modifyProductNameField.setText(product.getProductName());
        modifyProductInvField.setText(Integer.toString(product.getProductInventory()));
        modifyProductPriceField.setText(Double.toString(product.getProductPrice()));
        modifyProductMinField.setText(Integer.toString(product.getProductPartMin()));
        modifyProductMaxField.setText(Integer.toString(product.getProductPartMax()));
        currentlyAddedParts = product.getCurrentProductParts();
        //ModifyProductTableAddPartIdCol.setCellValueFactory(new PropertyValueFactory<Part, Integer>("part_ID>"));
        ModifyProductTableAddPartIdCol.setCellValueFactory(cellData -> cellData.getValue().partIDProperty().asObject());
        ModifyProductTableAddPartNameCol.setCellValueFactory(cellData -> cellData.getValue().partNameProperty());
        //ModifyProductTableAddPartNameCol.setCellValueFactory(new PropertyValueFactory<Part, String>("Name"));
        //ModifyProductTableAddPartInvCol.setCellValueFactory(new PropertyValueFactory<Part, Integer>("inventory"));
        ModifyProductTableAddPartInvCol.setCellValueFactory(cellData -> cellData.getValue().partInvProperty().asObject());
        ModifyProductTableAddPartPriceCol.setCellValueFactory(cellData -> cellData.getValue().partPriceProperty().asObject());

        //ModifyProductTableAddPartPriceCol.setCellValueFactory(new PropertyValueFactory<Part, Double>("price")); 
        //ModifyProductTableDeletePartIdCol.setCellValueFactory(new PropertyValueFactory<Part, Integer>("part_ID>"));
        ModifyProductTableDeletePartIdCol.setCellValueFactory(cellData -> cellData.getValue().partIDProperty().asObject());
        ModifyProductTableDeletePartNameCol.setCellValueFactory(new PropertyValueFactory<Part, String>("Name"));
        ModifyProductTableDeletePartInvCol.setCellValueFactory(new PropertyValueFactory<Part, Integer>("inventory"));
        ModifyProductTableDeletePartPriceCol.setCellValueFactory(new PropertyValueFactory<Part, Double>("price")); 
        updatePartaTable();
        currentPartTableViewUpdate();
        
    }    
    
}
