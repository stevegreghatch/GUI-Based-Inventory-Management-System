package App;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * @author Steven Hatch
 */

/**
 * Inventory class is the root class for the Part (including subclasses InHouse and Outsourced) and Product classes
 */
public class Inventory {

    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    /**
     * @param newPart the new part to add to allParts observable list
     */
    public static void addPart(Part newPart) {
        allParts.add(newPart);
    }

    /**
     * @param newProduct the new product to add to allProducts observable list
     */
    public static void addProduct(Product newProduct) {
        allProducts.add(newProduct);
    }

    /**
     * @param partID the user input id used to search product ids in allParts
     * @return matching part to searched id
     */
    public static Part lookupPart(int partID) {
        for (Part part : allParts) {
            if (part.getId() == partID) {
                return part;
            }
        }
        return null;
    }

    /**
     * @param productID the user input id used to search product ids in allProducts
     * @return matching product to searched id
     */
    public static Product lookupProduct(int productID) {
        for (Product product : allProducts) {
            if ((product.getId()) == (productID)) {
                return product;
            }
        }
        return null;
    }

    /**
     * @param partName the user input string used to search for part name containing the string in allParts
     * @return observable list with found parts
     */
    public static ObservableList<Part> lookupPart(String partName) {
        ObservableList<Part> foundPartList = FXCollections.observableArrayList();
        for (Part part : allParts) {
            if ((part.getName()).contains(partName)) {
                foundPartList.add(part);
            }
        }
        return foundPartList;
    }

    /**
     * @param productName the user input string used to search for product name containing the string in allProducts
     * @return observable list with found products
     */
    public static ObservableList<Product> lookupProduct(String productName) {
        ObservableList<Product> foundProductList = FXCollections.observableArrayList();
        for (Product product : allProducts) {
            if ((product.getName()).contains(productName)) {
                foundProductList.add(product);
            }
        }
        return foundProductList;
    }

    /**
     * @param index the index at which to replace the part in allParts
     * @param selectedPart the new part that will replace the previous part
     */
    public static void updatePart(int index, Part selectedPart) {
        allParts.set(index,selectedPart);
    }

    /**
     * @param index the index at which to replace the product in allProducts
     * @param newProduct the new product that will replace the previous product
     */
    public static void updateProduct(int index, Product newProduct) {
        allProducts.set(index,newProduct);
    }

    /**
     * @param selectedPart the part to delete
     */
    public static boolean deletePart(Part selectedPart) {
        allParts.remove(selectedPart);
        return true;
    }

    /**
     * @param selectedProduct the product to delete
     */
    public static boolean deleteProduct(Product selectedProduct) {
        allProducts.remove(selectedProduct);
        return true;
    }

    /**
     * @return observable list of all parts
     */
    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    /**
     * @return observable list of all products
     */
    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }
}

