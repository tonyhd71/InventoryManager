/**
 *
 * @author Tony
 */

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class inHousePart extends Part {

    private final IntegerProperty machineID;

    public inHousePart() {
        super();
        machineID = new SimpleIntegerProperty();
    }

    public void setPartMachineID(int machineID) {
        this.machineID.set(machineID);
    }

    public int getPartMachineID() {
        return this.machineID.get();
    }
}
