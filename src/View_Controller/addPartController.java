package View_Controller;
import Model.Inventory;
import Model.Part;
import Model.inHousePart;
import Model.outSourcePart;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/**
 * @FXML Controller class
 *
 * @author Tony
 */       
public class addPartController implements Initializable {   
    //
        private String inputError = new String();
    private int pardIdValue;

    @FXML Label machineOrCompanyLabel;
    
    @FXML TextField searchTerm = new TextField();
    @FXML TextField nameOfThePart = new TextField();
    @FXML TextField idAmount = new TextField();    
    @FXML TextField machineOrCompany = new TextField();    
    @FXML TextField invAmount = new TextField();
    @FXML TextField priceCost = new TextField();
    @FXML TextField maxValue = new TextField();
    @FXML TextField minValue = new TextField();
    @FXML RadioButton outSource = new RadioButton("Outsourced");        
    @FXML RadioButton inHouse = new RadioButton("inHouse");
    @FXML ToggleGroup group = new ToggleGroup();
    private boolean outSourcedBoolean;
    @FXML int idAmountInput; 
    @FXML public void clickInHouseRadioButton (ActionEvent event) throws IOException {
        outSourcedBoolean = false;
        machineOrCompanyLabel.setText("Machine ID");
    }
    @FXML public void clickOutSourceRadioButton (ActionEvent event) throws IOException {
        outSourcedBoolean = true;
        machineOrCompanyLabel.setText("Company Name");
    }  
       @FXML public void addPartsOutSourceClickSave(ActionEvent event) throws IOException
    {
        String nameOfThePartInput = nameOfThePart.getText();
        String invAmountInput = invAmount.getText();
        String priceCostInput = priceCost.getText();
        String minimumValueInput = minValue.getText();
        String maximumValueInput = maxValue.getText();
        String mOcInput = machineOrCompany.getText();

       
        try {
            inputError = Part.isPartInputValid(nameOfThePartInput, Integer.parseInt(minimumValueInput), Integer.parseInt(maximumValueInput), Integer.parseInt(invAmountInput), Double.parseDouble(priceCostInput), inputError);
            if (inputError.length() > 0) {
                Alert inputErrorAlert = new Alert(Alert.AlertType.INFORMATION);
                inputErrorAlert.setTitle("Error Adding Part");
                inputErrorAlert.setHeaderText("Error");
                inputErrorAlert.setContentText(inputError);
                inputErrorAlert.showAndWait();
                inputError = "";
            } else {
        
        if (outSourcedBoolean == false) {
                    inHousePart inHousePart = new inHousePart();

                    inHousePart.setPart_ID(pardIdValue);
                    inHousePart.setName(nameOfThePartInput);
                    inHousePart.setPrice(Double.parseDouble(priceCostInput));
                    inHousePart.setInventory(Integer.parseInt(invAmountInput));
                    inHousePart.setPartMin(Integer.parseInt(minimumValueInput));
                    inHousePart.setPartMax(Integer.parseInt(maximumValueInput));
                    inHousePart.setPartMachineID(Integer.parseInt(mOcInput));
                    Inventory.addPart(inHousePart);
                } else {
                    outSourcePart outSourcePart = new outSourcePart();
                    outSourcePart.setPart_ID(pardIdValue);
                    outSourcePart.setName(nameOfThePartInput);
                    outSourcePart.setPrice(Double.parseDouble(priceCostInput));
                    outSourcePart.setInventory(Integer.parseInt(invAmountInput));
                    outSourcePart.setPartMin(Integer.parseInt(minimumValueInput));
                    outSourcePart.setPartMax(Integer.parseInt(maximumValueInput));
                    outSourcePart.setPartCompanyName(mOcInput);
                    Inventory.addPart(outSourcePart);
                }
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("mainScreen.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);    
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();      
        window.setScene(tableViewScene);
        window.show();
       
        }
            
        
        }    catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error Adding Part");
            alert.setHeaderText("Error");
            alert.setContentText("Form contains blank fields.");
            alert.showAndWait();
        }
        
        
    }
     
           
    @FXML public void cancelButtonClicked  (ActionEvent event) throws IOException {
        
    Alert alert = new Alert(AlertType.CONFIRMATION);
    alert.setTitle("Confirmation Dialog");
    alert.setHeaderText("Comfirm delete part");
    alert.setContentText("Do you really want to cancel adding this part?");
    Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
    
        Parent aBc = FXMLLoader.load(getClass().getResource("mainScreen.fxml"));
        Scene dEf = new Scene(aBc);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(dEf);
        window.show();  
        }   else {
    
            Alert alert2 = new Alert(AlertType.INFORMATION);
        alert2.setTitle("Information Dialog");
        alert2.setHeaderText("Look, an Information Dialog");
        alert2.setContentText("You did not cancel this part");
        alert2.showAndWait();
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
        inHouse.setToggleGroup(group);
        outSource.setToggleGroup(group);  
        pardIdValue = Inventory.getPartIDCount();
        idAmount.setText("AUTO GEN- " + pardIdValue);
         
    }    

    /**
     *
     * @param event
     * @throws IOException
     */
    
  
}
