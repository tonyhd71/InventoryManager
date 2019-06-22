package Model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
public class Product {
        private static ObservableList<Part> productParts = FXCollections.observableArrayList();
    private final IntegerProperty product_ID;
    private final StringProperty productName;
    private final IntegerProperty productInventory;
    private final DoubleProperty productPrice;
    private final IntegerProperty productMin;
    private final IntegerProperty productMax;
    public Product() {
        product_ID = new SimpleIntegerProperty();
        productName = new SimpleStringProperty();;
        productInventory = new SimpleIntegerProperty();
        productPrice = new SimpleDoubleProperty();
        productMin = new SimpleIntegerProperty();
        productMax = new SimpleIntegerProperty();
    }
    public IntegerProperty productIDProperty() {
        return product_ID;
    }
    public StringProperty productNameProperty() {
        return productName;
    }
    public DoubleProperty productPriceProperty() {
        return productPrice;
    }
    public IntegerProperty productInvProperty() {
        return productInventory;
    }
    public int getProduct_ID() {
        return product_ID.get();
    }

    public void setProduct_ID(int product_ID) {
        this.product_ID.set(product_ID);
    }

    public String getProductName() {
        return productName.get();
    }

    public void setProductName(String productName) {
        this.productName.set(productName);
    }

    public int getProductInventory() {
        return productInventory.get();
    }

    public void setProductInventory(int productInventory) {
        this.productInventory.set(productInventory);
    }

    public double getProductPrice() {
        return productPrice.get();
    }

    public void setProductPrice(double productPrice) {
        this.productPrice.set(productPrice);
    }
    public int getProductPartMin() {
        return this.productMin.get();
    }
    public void setProductPartMin(int productMin) {
        this.productMin.set(productMin);
    }
    public int getProductPartMax() {
        return this.productMax.get();
    }
    public void setProductPartMax(int productMax) {
        this.productMax.set(productMax);
    }
    public void setCurrentProductParts(ObservableList<Part> productParts) {
        this.productParts = productParts;
    }
    public ObservableList getCurrentProductParts() {
        return productParts;
    }
    public static String isProductInputValid(String productName, int productMin, int productMax, 
            int productInventory, double productPrice, ObservableList<Part> productParts , 
            String productInputError) {
        double costOfAllParts = 0.00;
        for (int i = 0; i < productParts.size(); i++) {
            costOfAllParts = costOfAllParts + productParts.get(i).getPrice();
        }
        if (productName.equals("")) {
            productInputError = "Name field cannot be left blank.";
        }
        if (productInventory < 1) {
            productInputError = "The inventory must be greater than 0.";
        }
        if (productPrice < 1) {
            productInputError = "The price cannot be less than $0";
        }
        if (productMin > productMax) {
            productInputError = "The inventory minimum must be less than the inventory maximum.";
        }
        if (costOfAllParts > productPrice) {
            productInputError = "The total cost of parts cannot be less than the product price.";
        }
        if (productInventory < productMin || productInventory > productMax) {
            productInputError = "Part inventory must be between minimum and maximum values.";
        }
        if (productParts.size() < 1) {
            productInputError = "Product must contain at least 1 part to continue.";
        }
        return productInputError;
    }
 
    
}
