package View_Controller;
import Model.Inventory;
import static Model.Inventory.getPart;
import Model.Part;
import Model.inHousePart;
import Model.outSourcePart;
import static View_Controller.mainScreenController.modifyThisPartIndex;
import static java.awt.SystemColor.window;
import javafx.event.EventHandler;
import java.io.IOException;
import static java.lang.Integer.max;
import static java.lang.Integer.min;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.lang.model.element.Name;

/**
 * @FXML Controller class
 *
 * @author Tony
 */       
public class ModifyPartController implements Initializable {   
    //
        private int idOfPart;

    @FXML Label machineOrCompanyLabel;
    @FXML Button saveButton = new Button("Save");
    @FXML Button Search = new Button("Search");
    @FXML TextField searchTerm = new TextField();
    @FXML TextField nameOfThePartModify = new TextField();
    @FXML TextField machineOrCompany = new TextField();
        @FXML TextField machineOrCompanyModify = new TextField();

    @FXML TextField idAmountModify = new TextField();    
    @FXML TextField invAmountModify = new TextField();
    @FXML TextField priceCostModify = new TextField();
    @FXML TextField maxValueModify = new TextField();
    @FXML TextField minValueModify = new TextField();
    @FXML RadioButton outSourceModify = new RadioButton("Outsourced");        
    @FXML RadioButton inHouseModify = new RadioButton("inHouse");
    @FXML ToggleGroup group = new ToggleGroup();
    private boolean outSourcedBoolean;
    //@FXML TextField searchTerm = new TextField();
    @FXML int idAmountInputModify; 
        int partIndex = modifyThisPartIndex();
            private String inputError = new String();


       @FXML public void ModifyPartsOutSourceClickSave(ActionEvent event) throws IOException
    {
        String idAmountInput = idAmountModify.getText();
        String nameOfThePartInput = nameOfThePartModify.getText();
        String invAmountInput = invAmountModify.getText();
        String priceCostInput = priceCostModify.getText();
        String minimumValueInput = minValueModify.getText();
        String maximumValueInput = maxValueModify.getText();
        String mOcInput = machineOrCompanyModify.getText();

        try {
            inputError = Part.isPartInputValid(nameOfThePartInput, Integer.parseInt(minimumValueInput), Integer.parseInt(maximumValueInput), Integer.parseInt(invAmountInput), Double.parseDouble(priceCostInput), inputError);
            if (inputError.length() > 0) {
                Alert errorAddingPartAlert = new Alert(Alert.AlertType.INFORMATION);
                errorAddingPartAlert.setTitle("Error Adding Part");
                errorAddingPartAlert.setHeaderText("Error");
                errorAddingPartAlert.setContentText(inputError);
                errorAddingPartAlert.showAndWait();
                inputError = "";
            } else {
        
        
        if (outSourcedBoolean == false) {
                    inHousePart inHousePart = new inHousePart();
                    inHousePart.setPart_ID(idOfPart);
                    inHousePart.setName(nameOfThePartInput);
                    inHousePart.setPrice(Double.parseDouble(priceCostInput));
                    inHousePart.setInventory(Integer.parseInt(invAmountInput));
                    inHousePart.setPartMin(Integer.parseInt(minimumValueInput));
                    inHousePart.setPartMax(Integer.parseInt(maximumValueInput));
                    inHousePart.setPartMachineID(Integer.parseInt(mOcInput));
                    Inventory.updatePart(partIndex, inHousePart);
                } else {
                    outSourcePart outSourcePart = new outSourcePart();
                    outSourcePart.setPart_ID(idOfPart);
                    outSourcePart.setName(nameOfThePartInput);
                    outSourcePart.setPrice(Double.parseDouble(priceCostInput));
                    outSourcePart.setInventory(Integer.parseInt(invAmountInput));
                    outSourcePart.setPartMin(Integer.parseInt(minimumValueInput));
                    outSourcePart.setPartMax(Integer.parseInt(maximumValueInput));
                    outSourcePart.setPartCompanyName(mOcInput);
                    Inventory.updatePart(partIndex, outSourcePart);;
                }           
       
         
            }
            
          
        
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("mainScreen.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);    
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();      
        window.setScene(tableViewScene);
        window.show();
        } 
        
        catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error Adding Part");
            alert.setHeaderText("Error");
            alert.setContentText("Form cannot contain blank fields.");
            alert.showAndWait();
        }
    }
       @FXML public void clickInHouseRadioButton (ActionEvent event) throws IOException {
        outSourcedBoolean = false;
        machineOrCompanyLabel.setText("Machine ID");
    }
    @FXML public void clickOutSourceRadioButton (ActionEvent event) throws IOException {
        outSourcedBoolean = true;
        machineOrCompanyLabel.setText("Company Name");
    }   
           
    @FXML public void ModifyCancelButtonClicked  (ActionEvent event) throws IOException {
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Comfirm cancel modify this part");
        alert.setContentText("Do you really want to cancel modifying this part?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            Parent aBc = FXMLLoader.load(getClass().getResource("mainScreen.fxml"));
            Scene dEf = new Scene(aBc);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(dEf);
            window.show();      
        }
        
    }
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        inHouseModify.setToggleGroup(group);
        outSourceModify.setToggleGroup(group);  
        
        Part part = getPart().get(partIndex);
        idOfPart = getPart().get(partIndex).getPart_ID();
        idAmountModify.setText("Part ID autoset to: " + idOfPart);
        nameOfThePartModify.setText(part.getName());
        invAmountModify.setText(Integer.toString(part.getInventory()));
        priceCostModify.setText(Double.toString(part.getPrice()));
        minValueModify.setText(Integer.toString(part.getPartMin()));
        maxValueModify.setText(Integer.toString(part.getPartMax()));
        if (part instanceof inHousePart) {
            machineOrCompanyModify.setText(Integer.toString(((inHousePart) getPart().get(partIndex)).getPartMachineID()));
            machineOrCompanyLabel.setText("Machine ID");
            inHouseModify.setSelected(true);

        } else {
            machineOrCompanyModify.setText(((outSourcePart) getPart().get(partIndex)).getPartCompanyName());
            machineOrCompanyLabel.setText("Company Name");
            outSourceModify.setSelected(true);
        }
        
    }    
    /**
     *
     * @param event
     * @throws IOException
     */
}
