package Model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.TextField;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Tony
 */
public abstract class Part {
    private final IntegerProperty part_ID;
    private final StringProperty Name;
    private final IntegerProperty inventory;
    private final DoubleProperty price;
    private final IntegerProperty min;
    private final IntegerProperty max;
    public Part() {
        part_ID = new SimpleIntegerProperty();
        Name = new SimpleStringProperty();;
        inventory = new SimpleIntegerProperty();
        price = new SimpleDoubleProperty();
        min = new SimpleIntegerProperty();
        max = new SimpleIntegerProperty();
    }
    public IntegerProperty partIDProperty() {
        return part_ID;
    }
    public StringProperty partNameProperty() {
        return Name;
    }
    public DoubleProperty partPriceProperty() {
        return price;
    }
    public IntegerProperty partInvProperty() {
        return inventory;
    }
    
    public int getPart_ID() {
        return part_ID.get();
    }

    public void setPart_ID(int part_ID) {
        this.part_ID.set(part_ID);
    }

    public String getName() {
        return Name.get();
    }

    public void setName(String Name) {
        this.Name.set(Name);
    }

    public int getInventory() {
        return inventory.get();
    }

    public void setInventory(int inventory) {
        this.inventory.set(inventory);
    }

    public double getPrice() {
        return price.get();
    }

    public void setPrice(double price) {
        this.price.set(price);
    }
    public int getPartMin() {
        return this.min.get();
    }
    public void setPartMin(int min) {
        this.min.set(min);
    }
    public int getPartMax() {
        return this.max.get();
    }
    public void setPartMax(int max) {
        this.max.set(max);
    }
    

    public static String isPartInputValid(String Name, int min, int max, int inventory, double price, String inputError) {
        if (Name == null) {
            inputError = "Name field cannot be left blank.";
        }
        if (inventory < 1) {
            inputError = "The inventory must be greater than 0.";
        }
        if (price < 1) {
            inputError = "The price cannot be less than $0";
        }
        if (min > max) {
            inputError = "The inventory minimum must be less than the inventory maximum.";
        }
        if (inventory < min || inventory > max) {
            inputError = "Part inventory must be between minimum and maximum values.";
        }
        return inputError;
    }
    
}
