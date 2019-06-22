/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Tony
 */
public class Inventory {

    //private static ObservableList<Product> productInventory = FXCollections.observableArrayList();
    private static ObservableList<Part> parts = FXCollections.observableArrayList();    
    private static ObservableList<Product> products = FXCollections.observableArrayList();    

    private static int countPartId = 0;
    private static int productIDCount = 0;

    public Inventory() {
    }

    public static ObservableList<Part> getPart() {
        return parts;
    }

    public static void addPart(Part part) {
        parts.add(part);
    }

    public static void removePart(Part part) {
        parts.remove(part);
    }

    public static void updatePart(int index, Part part) {
        parts.set(index, part);
    }

    public static int getPartIDCount() {
        countPartId++;
        return countPartId;
    }

    public static int searchPart(String searchTerm) {
        boolean wasPartFound = false;
        int index = 0;
        if (isInteger(searchTerm)) {
            for (int i = 0; i < parts.size(); i++) {
                if (Integer.parseInt(searchTerm) == parts.get(i).getPart_ID()) {
                    index = i;
                    wasPartFound = true;
                }
            }
        } else {
            for (int i = 0; i < parts.size(); i++) {
                if (searchTerm.equals(parts.get(i).getName())) {
                    index = i;
                    wasPartFound = true;
                }
            }
        }
        if (wasPartFound = true) {
            return index;
        } else {
            System.out.println("No parts found.");
            return -1;
        }
    }



    public static int searchProduct(String searchTerm) {
        boolean wasPartFound = false;
        int index = 0;

        if (isInteger(searchTerm)) {
            for (int i = 0; i < products.size(); i++) {
                if (Integer.parseInt(searchTerm) == products.get(i).getProduct_ID()) {
                    index = i;
                    wasPartFound = true;
                }
            }
        } else {
            for (int i = 0; i < products.size(); i++) {
                if (searchTerm.equals(products.get(i).getProductName())) {
                    index = i;
                    wasPartFound = true;
                }
            }
        }
        if (wasPartFound = true) {
            return index;
        } else {
            System.out.println("No products found.");
            return -1;
        }
    }
    
   // public static void updateProduct(int index, Product product) {
       // productInventory.set(index, product);
   // }

    public static boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
   
    public static ObservableList<Product> getProduct() {
        return products;
    }
    public static void addProduct(Product product) {
        products.add(product);
    }

    public static void removeProduct(Product product) {
        products.remove(product);
    }

    public static void updateProduct(int index, Product product) {
        products.set(index, product);
    }

    public static int getProductIDCount() {
        productIDCount++;
        return productIDCount;
    }
}
