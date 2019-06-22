package Model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class outSourcePart extends Part {

    private final StringProperty nameOfTheCompany;

    public outSourcePart() {
        super();
        nameOfTheCompany = new SimpleStringProperty();
    }

    public void setPartCompanyName(String nameOfTheCompany) {
        this.nameOfTheCompany.set(nameOfTheCompany);
    }

    public String getPartCompanyName() {
        return this.nameOfTheCompany.get();
    }
}
