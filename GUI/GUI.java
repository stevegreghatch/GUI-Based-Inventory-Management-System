package GUI;

import App.*;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.WindowEvent;
import javax.swing.JOptionPane;

/**
 * @author Steven Hatch
 */

//Javadoc comments are in 'Javadoc Export' folder

/**
 * GUI class is the main view for the user to interact with the application
 *
 * FUTURE ENHANCEMENT: buttonModifyPart - Selected Part Check for Modify Part Button: -
 * prevents null pointer exception if no parts have been created or if no parts are selected
 * displays error message to user
 *
 * FUTURE ENHANCEMENT: buttonModifyProduct - Selected Product Check for Modify Product Button:
 * prevents null pointer exception if no products have been created or if no products are selected
 * displays error message to user
 *
 */
public class GUI extends Application {

    /**
     * scenes listed at top to allow scene switches through the application
     */
    Scene sceneMain, sceneAddPart, sceneModifyPart, sceneAddProduct, sceneModifyProduct;

    /**
     * @param args program execution
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * contains all forms (main, add part, modify part, add product, and modify product)
     *
     * LOGICAL ERROR CORRECTION: buttonAddPart - nested while loop added to ensure that multiple parts were not being created with the same part id
     * if part with same id is found, i is increased to the closest unique int
     *
     * LOGICAL ERROR CORRECTION: buttonAddProduct - nested while loop added to ensure that multiple products were not being created with the same product id
     * if product with same id is found, i is increased to the closest unique int
     *
     * @param primaryStage the stage for main view
     */
    @Override
    public void start(Stage primaryStage) {

        /**
         * sets primary stage and window title
         */
        Stage windowGUI = primaryStage;
        windowGUI.setTitle("QKM2 Task 1");

        /**
         * catches close request and redirects user to main form if not already there
         * if user is at main form, window then closes
         */
        windowGUI.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                if (windowGUI.getScene() != sceneMain) {
                    event.consume();
                    windowGUI.setScene(sceneMain);
                } else {
                    System.out.println("Thank you for reviewing my submission!");
                    windowGUI.setOnCloseRequest(e -> windowGUI.close());
                }
            }});

        /**
         * table initialization
         */

        /**
         * allParts table
         */
        TableView<Part> allParts = new TableView<>();
        TableColumn<Part, Integer> allPartsIdCol = new TableColumn<>("Part ID");
            allPartsIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn<Part, String> allPartsNameCol = new TableColumn<>("Part Name");
            allPartsNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<Part, Integer> allPartsStockCol = new TableColumn<>("Inventory Level");
            allPartsStockCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        TableColumn<Part, Double> allPartsPriceCol = new TableColumn<>("Price/ Cost per Unit");
            allPartsPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        allParts.getColumns().add(allPartsIdCol);
        allParts.getColumns().add(allPartsNameCol);
        allParts.getColumns().add(allPartsStockCol);
        allParts.getColumns().add(allPartsPriceCol);

        allParts.setItems(Inventory.getAllParts());

        /**
         * allProducts table
         */
        TableView<Product> allProducts = new TableView<>();
        TableColumn<Product, Integer> allProductsIdCol = new TableColumn<>("Product ID");
            allProductsIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn<Product, String> allProductsNameCol = new TableColumn<>("Product Name");
            allProductsNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<Product, Integer> allProductsStockCol = new TableColumn<>("Inventory Level");
            allProductsStockCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        TableColumn<Product, Double> allProductsPriceCol = new TableColumn<>("Price/ Cost per Unit");
            allProductsPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        TableColumn<Product, Double> allProductsMinCol = new TableColumn<>("Min");
            allProductsMinCol.setCellValueFactory(new PropertyValueFactory<>("min"));
        TableColumn<Product, Double> allProductsMaxCol = new TableColumn<>("Max");
            allProductsMaxCol.setCellValueFactory(new PropertyValueFactory<>("max"));

        allProducts.getColumns().add(allProductsIdCol);
        allProducts.getColumns().add(allProductsNameCol);
        allProducts.getColumns().add(allProductsStockCol);
        allProducts.getColumns().add(allProductsPriceCol);

        allProducts.setItems(Inventory.getAllProducts());

        /**
         * allPartsAddProduct table - utilized in upper right of add product form
         */
        TableView<Part> allPartsAddProduct = new TableView<>();
        TableColumn<Part, Integer> allPartsAddProductIdCol = new TableColumn<>("Part ID");
            allPartsAddProductIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn<Part, String> allPartsAddProductNameCol = new TableColumn<>("Part Name");
            allPartsAddProductNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<Part, Integer> allPartsAddProductStockCol = new TableColumn<>("Inventory Level");
            allPartsAddProductStockCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        TableColumn<Part, Double> allPartsAddProductPriceCol = new TableColumn<>("Price/ Cost per Unit");
            allPartsAddProductPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        allPartsAddProduct.getColumns().add(allPartsAddProductIdCol);
        allPartsAddProduct.getColumns().add(allPartsAddProductNameCol);
        allPartsAddProduct.getColumns().add(allPartsAddProductStockCol);
        allPartsAddProduct.getColumns().add(allPartsAddProductPriceCol);

        allPartsAddProduct.setItems(Inventory.getAllParts());

        /**
         * associatedParts table - utilized in lower right of add product form
         */
        TableView<Part> associatedParts = new TableView<>();
        TableColumn<Part, Integer> associatedPartsIdCol = new TableColumn<>("Part ID");
            associatedPartsIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn<Part, String> associatedPartsNameCol = new TableColumn<>("Part Name");
            associatedPartsNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<Part, Integer> associatedPartsStockCol = new TableColumn<>("Inventory Level");
            associatedPartsStockCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        TableColumn<Part, Double> associatedPartsPriceCol = new TableColumn<>("Price/ Cost per Unit");
            associatedPartsPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        associatedParts.getColumns().add(associatedPartsIdCol);
        associatedParts.getColumns().add(associatedPartsNameCol);
        associatedParts.getColumns().add(associatedPartsStockCol);
        associatedParts.getColumns().add(associatedPartsPriceCol);

        /**
         * allPartsModifyProduct table - utilized in upper right of modify product form
         */
        TableView<Part> allPartsModifyProduct = new TableView<>();
        TableColumn<Part, Integer> allPartsModifyProductIdCol = new TableColumn<>("Part ID");
            allPartsModifyProductIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn<Part, String> allPartsModifyProductNameCol = new TableColumn<>("Part Name");
            allPartsModifyProductNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<Part, Integer> allPartsModifyProductStockCol = new TableColumn<>("Inventory Level");
            allPartsModifyProductStockCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        TableColumn<Part, Double> allPartsModifyProductPriceCol = new TableColumn<>("Price/ Cost per Unit");
            allPartsModifyProductPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        allPartsModifyProduct.getColumns().add(allPartsModifyProductIdCol);
        allPartsModifyProduct.getColumns().add(allPartsModifyProductNameCol);
        allPartsModifyProduct.getColumns().add(allPartsModifyProductStockCol);
        allPartsModifyProduct.getColumns().add(allPartsModifyProductPriceCol);

        allPartsModifyProduct.setItems(Inventory.getAllParts());

        /**
         * associatedPartsModifyProduct table - utilized in lower right of modify product form
         */
        TableView<Part> associatedPartsModifyProduct = new TableView<>();
        TableColumn<Part, Integer> associatedPartsModifyProductIdCol = new TableColumn<>("Part ID");
            associatedPartsModifyProductIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn<Part, String> associatedPartsModifyProductNameCol = new TableColumn<>("Part Name");
            associatedPartsModifyProductNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<Part, Integer> associatedPartsModifyProductStockCol = new TableColumn<>("Inventory Level");
            associatedPartsModifyProductStockCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        TableColumn<Part, Double> associatedPartsModifyProductPriceCol = new TableColumn<>("Price/ Cost per Unit");
            associatedPartsModifyProductPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        associatedPartsModifyProduct.getColumns().add(associatedPartsModifyProductIdCol);
        associatedPartsModifyProduct.getColumns().add(associatedPartsModifyProductNameCol);
        associatedPartsModifyProduct.getColumns().add(associatedPartsModifyProductStockCol);
        associatedPartsModifyProduct.getColumns().add(associatedPartsModifyProductPriceCol);

        /**
         * text field initialization
         */

        /**
         * add part form text fields
         */
        TextField textFieldIdAddPart = new TextField();
        TextField textFieldNameAddPart = new TextField();
        TextField textFieldStockAddPart = new TextField();
        TextField textFieldPriceCostAddPart = new TextField();
        TextField textFieldMaxAddPart = new TextField();
        TextField textFieldMinAddPart = new TextField();
        TextField textFieldBottomAddPart = new TextField();
        TextField userDisplayedTextAutoGenAddPart = new TextField("Auto Gen - Disabled");

        /**
         * add product form text fields
         */
        TextField textFieldIdAddProduct = new TextField();
        TextField textFieldNameAddProduct = new TextField();
        TextField textFieldStockAddProduct = new TextField();
        TextField textFieldPriceCostAddProduct = new TextField();
        TextField textFieldMaxAddProduct = new TextField();
        TextField textFieldMinAddProduct = new TextField();
        TextField userDisplayedTextAutoGenAddProduct = new TextField("Auto Gen - Disabled");

        /**
         * modify part form text fields
         */
        TextField textFieldIdModifyPart = new TextField();
        TextField textFieldNameModifyPart = new TextField();
        TextField textFieldStockModifyPart = new TextField();
        TextField textFieldPriceCostModifyPart = new TextField();
        TextField textFieldMaxModifyPart = new TextField();
        TextField textFieldMinModifyPart = new TextField();
        TextField textFieldBottomModifyPart = new TextField();
        TextField textFieldIndexToUpdateModifyPart = new TextField();

        /**
         * modify product form text fields
         */
        TextField textFieldIdModifyProduct = new TextField();
        TextField textFieldNameModifyProduct = new TextField();
        TextField textFieldStockModifyProduct = new TextField();
        TextField textFieldPriceCostModifyProduct = new TextField();
        TextField textFieldMaxModifyProduct = new TextField();
        TextField textFieldMinModifyProduct = new TextField();
        TextField textFieldIndexToUpdateModifyProduct = new TextField();

        /**
         * other initializations
         */

        /**
         * label - global to allow for use in main form
         */
        Label labelBottomModifyPart = new Label("Machine ID");

        /**
         * instance product - created to allow access to non-static methods in Product class prior to a product being created
         * global to allow for use in main form
         */
        Product instanceProduct = new Product(1, "Instance Product", 10.00, 5, 1, 10);

        /**
         * radio button - global to allow for use in main form
         * utilized to set selected button before scene switch to set appropriate bottom text field (machineId vs companyName)
         */
        RadioButton radioButtonInHouseModifyPart = new RadioButton("In-House");
        RadioButton radioButtonOutsourcedModifyPart = new RadioButton("Outsourced");

//MAIN FORM ------------------------------------------------------------------------------------------------------------

    /**
    * Main Form
    */
        /**
         * labels
         */
        Label labelWindowTitle = new Label("Inventory Management System");
        Label labelPartPaneTitle = new Label("Parts");
        Label labelProductPaneTitle = new Label("Products");

        /**
         * buttons
         */

        /**
         * buttonAddPart - located in part pane
         * auto-generates part id
         * clears previously entered part data
         * switches scene to add part form
         *
         * LOGICAL ERROR CORRECTION: nested while loop added to ensure that multiple parts were not being created with the same part id
         * if part with same id is found, i is increased to the closest unique int
         */
        Button buttonAddPart = new Button("Add");
            buttonAddPart.setOnAction(e -> {
                textFieldIdAddPart.clear();
                    int i = 1;
                    int autogenPartId = i;
                    while ((textFieldIdAddPart.getText()).isEmpty()) {
                        while (Inventory.lookupPart(autogenPartId) != null)
                        autogenPartId = i++;
                        textFieldIdAddPart.setText(Integer.toString(autogenPartId));
                    }
                textFieldNameAddPart.clear();
                textFieldStockAddPart.clear();
                textFieldPriceCostAddPart.clear();
                textFieldMaxAddPart.clear();
                textFieldMinAddPart.clear();
                textFieldBottomAddPart.clear();
                windowGUI.setScene(sceneAddPart);
            });

        /**
         * hbox initialization included here to allow buttonModifyPart access to hbox's spacing to maintain alignment during InHouse vs OutSourced switch
         */
        HBox hboxBottomModifyPart = new HBox(45, labelBottomModifyPart, textFieldBottomModifyPart);
        hboxBottomModifyPart.setPadding(new Insets(2));

        /**
         * buttonModifyPart - located in part pane
         *
         * gets data from selected part (specific to InHouse vs Outsourced) and sends to Modify Part text fields
         * sets appropriate radio button selection, bottom label text (machine id vs company name), and spacing in modify part form
         * passes index of selected part to text field - later used to update part
         * switches scene to modify part form
         *
         * FUTURE ENHANCEMENT: Selected Part Check for Modify Part Button: below exception prevented - displays error message to user
         *
         * @throws NullPointerException if no parts have been created or if no parts are selected
         */
        Button buttonModifyPart = new Button("Modify");
            buttonModifyPart.setOnAction(e -> {
                if (allParts.getSelectionModel().getSelectedItem() == null) {
                    JOptionPane.showMessageDialog(null, "Unable To Modify - No Parts Selected");
                }
                else if (allParts.getSelectionModel().getSelectedItem() instanceof InHouse) {
                    textFieldIdModifyPart.setText(String.valueOf(allParts.getSelectionModel().getSelectedItem().getId()));
                    textFieldNameModifyPart.setText(allParts.getSelectionModel().getSelectedItem().getName());
                    textFieldStockModifyPart.setText(String.valueOf(allParts.getSelectionModel().getSelectedItem().getStock()));
                    textFieldPriceCostModifyPart.setText(String.valueOf(allParts.getSelectionModel().getSelectedItem().getPrice()));
                    textFieldMaxModifyPart.setText(String.valueOf(allParts.getSelectionModel().getSelectedItem().getMax()));
                    textFieldMinModifyPart.setText(String.valueOf(allParts.getSelectionModel().getSelectedItem().getMin()));
                    textFieldBottomModifyPart.setText(String.valueOf(((InHouse) allParts.getSelectionModel().getSelectedItem()).getMachineId()));
                    radioButtonInHouseModifyPart.setSelected(true);
                    labelBottomModifyPart.setText("Machine ID");
                    hboxBottomModifyPart.setSpacing(45);
                    textFieldIndexToUpdateModifyPart.setText(String.valueOf(allParts.getSelectionModel().getSelectedIndex()));
                    windowGUI.setScene(sceneModifyPart);
                }
                else {
                    textFieldIdModifyPart.setText(String.valueOf(allParts.getSelectionModel().getSelectedItem().getId()));
                    textFieldNameModifyPart.setText(allParts.getSelectionModel().getSelectedItem().getName());
                    textFieldStockModifyPart.setText(String.valueOf(allParts.getSelectionModel().getSelectedItem().getStock()));
                    textFieldPriceCostModifyPart.setText(String.valueOf(allParts.getSelectionModel().getSelectedItem().getPrice()));
                    textFieldMaxModifyPart.setText(String.valueOf(allParts.getSelectionModel().getSelectedItem().getMax()));
                    textFieldMinModifyPart.setText(String.valueOf(allParts.getSelectionModel().getSelectedItem().getMin()));
                    textFieldBottomModifyPart.setText(((Outsourced) allParts.getSelectionModel().getSelectedItem()).getCompanyName());
                    radioButtonOutsourcedModifyPart.setSelected(true);
                    labelBottomModifyPart.setText("Company Name");
                    hboxBottomModifyPart.setSpacing(20);
                    textFieldIndexToUpdateModifyPart.setText(String.valueOf(allParts.getSelectionModel().getSelectedIndex()));
                    windowGUI.setScene(sceneModifyPart);
                }
            });

        /**
         * buttonDeletePart - located in part pane
         * displays error if no parts are selected
         * confirms intent to delete part
         * deletes part upon confirmation
         * displays message if part is not deleted
         */
        Button buttonDeletePart = new Button("Delete");
            buttonDeletePart.setOnAction( e -> {
                Part selectedPart = allParts.getSelectionModel().getSelectedItem();
                    if (selectedPart == null) {
                        JOptionPane.showMessageDialog(null, "Unable To Delete - No Parts Selected");
                    }
                    else {
                        int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure?");
                        if (dialogResult == 0) {
                            Inventory.deletePart(selectedPart);
                        }
                        else {
                            JOptionPane.showMessageDialog(null, "Part Not Deleted");
                        }
                    }
            });

        /**
         * buttonAddProduct - located in product pane
         * auto-generates product id
         * clears previously entered product data including associated parts
         * switches scene to add product form
         *
         * LOGICAL ERROR CORRECTION: nested while loop added to ensure that multiple products were not being created with the same product id
         * if product with same id is found, i is increased to the closest unique int
         */
        Button buttonAddProduct = new Button("Add");
            buttonAddProduct.setOnAction(e -> {
                textFieldIdAddProduct.clear();
                int i = 1;
                int autogenProductId = i;
                while ((textFieldIdAddProduct.getText()).isEmpty()) {
                    while (Inventory.lookupProduct(autogenProductId) != null)
                        autogenProductId = i++;
                    textFieldIdAddProduct.setText(Integer.toString(autogenProductId));
                }
                textFieldNameAddProduct.clear();
                textFieldStockAddProduct.clear();
                textFieldPriceCostAddProduct.clear();
                textFieldMaxAddProduct.clear();
                textFieldMinAddProduct.clear();
                associatedParts.getItems().clear();
                windowGUI.setScene(sceneAddProduct);
            });

        /**
         * buttonModifyProduct - located in product pane
         *
         * gets data from selected product and sends to Modify Product text fields
         * sets product specific associated parts
         * passes index of selected product to text field - later used to update product
         * switches scene to modify product form
         *
         * FUTURE ENHANCEMENT: Selected Product Check for Modify Product Button: below exception prevented - displays error message to user
         *
         * @throws NullPointerException if no products have been created or if no products are selected
         */
        Button buttonModifyProduct = new Button("Modify");
            buttonModifyProduct.setOnAction(e -> {
                if (allProducts.getSelectionModel().getSelectedItem() == null) {
                    JOptionPane.showMessageDialog(null, "Unable To Modify - No Products Selected");
                }
                else {
                    textFieldIdModifyProduct.setText(String.valueOf(allProducts.getSelectionModel().getSelectedItem().getId()));
                    textFieldNameModifyProduct.setText(allProducts.getSelectionModel().getSelectedItem().getName());
                    textFieldStockModifyProduct.setText(String.valueOf(allProducts.getSelectionModel().getSelectedItem().getStock()));
                    textFieldPriceCostModifyProduct.setText(String.valueOf(allProducts.getSelectionModel().getSelectedItem().getPrice()));
                    textFieldMaxModifyProduct.setText(String.valueOf(allProducts.getSelectionModel().getSelectedItem().getMax()));
                    textFieldMinModifyProduct.setText(String.valueOf(allProducts.getSelectionModel().getSelectedItem().getMin()));
                    associatedPartsModifyProduct.setItems(allProducts.getSelectionModel().getSelectedItem().getAllAssociatedParts());
                    textFieldIndexToUpdateModifyProduct.setText(String.valueOf(allProducts.getSelectionModel().getSelectedIndex()));
                    windowGUI.setScene(sceneModifyProduct);
                }
            });

        /**
         * buttonDeleteProduct - located in product pane
         * displays error if no products are selected
         * prevents deletion of product with associated parts and notifies user
         * confirms intent to delete product
         * deletes product upon confirmation
         * displays message if product is not deleted
         */
        Button buttonDeleteProduct = new Button("Delete");
            buttonDeleteProduct.setOnAction(e -> {
                Product selectedProduct = allProducts.getSelectionModel().getSelectedItem();
                if (selectedProduct == null) {
                    JOptionPane.showMessageDialog(null, "Unable To Delete - No Products Selected");
                }
                else {
                    if (((selectedProduct.getAllAssociatedParts()) != null) && (!((selectedProduct.getAllAssociatedParts()).isEmpty()))) {
                        JOptionPane.showMessageDialog(null, "Unable To Delete - Product has Associated Part(s)");
                    }
                    else {
                        int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure?");
                        if (dialogResult == 0) {
                            Inventory.deleteProduct(selectedProduct);
                        }
                        else {
                            JOptionPane.showMessageDialog(null, "Product Not Deleted");
                        }
                    }
                }
            });

        /**
         * buttonExit
         * closes window
         */
        Button buttonExit = new Button("Exit");
            buttonExit.setOnAction(e -> windowGUI.close());

        /**
         * partSearchMain
         * allow for part search by id or name
         * notifies user if no matching parts are found
         * sets found parts to all parts table
         * resets all parts table if search is empty
         *
         * @throws NumberFormatException if integer is parsed from non-digit string
         */
        TextField partSearchMain = new TextField();
        partSearchMain.setPromptText("Search by Part ID or Name");
        partSearchMain.setFocusTraversable(false);
        partSearchMain.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!(partSearchMain.getText()).isEmpty())
                    try {
                        int partId = Integer.parseInt(newValue);
                        Part part = Inventory.lookupPart(partId);
                        ObservableList foundParts = FXCollections.observableArrayList();
                        if (part != null) {
                            foundParts.add(part);
                        }
                        else {
                            JOptionPane.showMessageDialog(null, "No Parts Found With This ID");
                        }
                        allParts.setItems(foundParts);
                    }
                    catch (NumberFormatException numberFormatException) {
                        String partName = newValue;
                        ObservableList<Part> foundPartList = FXCollections.observableArrayList();
                        foundPartList.setAll(FXCollections.observableArrayList(Inventory.lookupPart(partName)));
                        allParts.setItems(foundPartList);
                        if (foundPartList.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "No Parts Found With This Name");
                        }
                    }
            else if ((partSearchMain.getText()).isEmpty()) {
                allParts.setItems(Inventory.getAllParts());
            }
        });

        /**
         * productSearchMain
         * allow for product search by id or name
         * notifies user if no matching product are found
         * sets found products to all products table
         * resets all product table if search is empty
         *
         @throws NumberFormatException if integer is parsed from non-digit string
         */
        TextField productSearchMain = new TextField();
        productSearchMain.setPromptText("Search by Product ID or Name");
        productSearchMain.setFocusTraversable(false);
        productSearchMain.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!(productSearchMain.getText()).isEmpty()) {
                try {
                    int productId = Integer.parseInt(newValue);
                    Product product = Inventory.lookupProduct(productId);
                    ObservableList foundProducts = FXCollections.observableArrayList();
                    if (product != null) {
                        foundProducts.add(product);
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "No Products Found With This ID");
                    }
                    allProducts.setItems(foundProducts);
                } catch (NumberFormatException ex) {
                    String productName = newValue;
                    ObservableList<Product> foundProductList = FXCollections.observableArrayList();
                    foundProductList = FXCollections.observableArrayList(Inventory.lookupProduct(productName));
                    allProducts.setItems(foundProductList);
                    if (foundProductList.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "No Products Found With This Name");
                    }
                }
            }
            else if ((productSearchMain.getText()).isEmpty()) {
                allProducts.setItems(Inventory.getAllProducts());
            }
        });

        /**
         * main form layout
         */

        /**
         * label styling and assignment
         */
        String cssHeaderLabelFormat = "-fx-font-size:16px;" +
                "-fx-font-weight: bold;";
        labelWindowTitle.setStyle(cssHeaderLabelFormat);

        String cssTitleLabelFormat = "-fx-font-size:14px;" +
                "-fx-font-weight: bold;";
        labelPartPaneTitle.setStyle(cssTitleLabelFormat);
        labelProductPaneTitle.setStyle(cssTitleLabelFormat);

        /**
         * hbox window title
         */
        HBox hboxWindowTitle = new HBox(labelWindowTitle);
        hboxWindowTitle.setPadding(new Insets(0,10,0,10));

        /**
         * part and product pane top boxes
         */
        HBox hboxPartPaneTop = new HBox(200, labelPartPaneTitle, partSearchMain);

        HBox hboxProductPaneTop = new HBox(200, labelProductPaneTitle, productSearchMain);

        /**
         * part and product pane bottom boxes
         */
        HBox hboxPartPaneBottom = new HBox(20, buttonAddPart, buttonModifyPart, buttonDeletePart);
        hboxPartPaneBottom.setAlignment(Pos.BOTTOM_RIGHT);

        HBox hboxProductPaneBottom = new HBox(20, buttonAddProduct, buttonModifyProduct, buttonDeleteProduct);
        hboxProductPaneBottom.setAlignment(Pos.BOTTOM_RIGHT);

        /**
         * exit button box
         */
        HBox hboxButtonExit = new HBox(buttonExit);
        hboxButtonExit.setAlignment(Pos.CENTER_RIGHT);
        hboxButtonExit.setPadding(new Insets(10));

        /**
         * border style for container box
         */
        String cssPaneLayoutBorder = "-fx-border-color: black;" +
                "-fx-border-insets: 30;" + "-fx-border-width: 1px;" +
                "-fx-border-style: solid;" + "-fx-border-radius: 10;";

        /**
         * tableview size restrictions
         */
        allParts.setPrefSize(250,200);
        allPartsIdCol.setPrefWidth(60);
        allPartsNameCol.setPrefWidth(80);
        allPartsStockCol.setPrefWidth(100);
        allPartsPriceCol.setPrefWidth(130);

        allProducts.setPrefSize(250,200);
        allProductsIdCol.setPrefWidth(70);
        allProductsNameCol.setPrefWidth(90);
        allProductsStockCol.setPrefWidth(100);
        allProductsPriceCol.setPrefWidth(130);

        /**
         * part and product container boxes
         */
        VBox vboxPartPaneContainer = new VBox(10, hboxPartPaneTop, allParts, hboxPartPaneBottom);
        vboxPartPaneContainer.setStyle(cssPaneLayoutBorder);
        vboxPartPaneContainer.setPadding(new Insets(10));

        VBox vboxProductPaneContainer = new VBox(10, hboxProductPaneTop, allProducts, hboxProductPaneBottom);
        vboxProductPaneContainer.setStyle(cssPaneLayoutBorder);
        vboxProductPaneContainer.setPadding(new Insets(10));

        /**
         * gridpane layout
         */
        GridPane gridLayoutMain = new GridPane();
        gridLayoutMain.add(hboxWindowTitle, 0, 1);
        gridLayoutMain.add(vboxPartPaneContainer, 0, 2);
        gridLayoutMain.add(vboxProductPaneContainer, 1, 2);
        gridLayoutMain.add(hboxButtonExit, 1, 3);
        gridLayoutMain.setPadding(new Insets(30));

        /**
         * scene initialization and assignment
         * display main window
         */
        sceneMain = new Scene(gridLayoutMain);
        windowGUI.setScene(sceneMain);
        windowGUI.show();

//ADD PART FORM---------------------------------------------------------------------------------------------------------

    /**
    * Add Part Form
    */
        /**
         * labels
         */
        Label labelAddPartTitle = new Label("Add Part");
        Label labelIdAddPart = new Label("ID");
        Label labelNameAddPart = new Label("Name");
        Label labelInvAddPart = new Label("Inv");
        Label labelPriceCostAddPart = new Label("Price/Cost");
        Label labelMaxAddPart = new Label("Max");
        Label labelMinAddPart = new Label("Min");
        Label labelBottomAddPart = new Label("Machine ID");

        /**
         * disables displayed id field
         */
        userDisplayedTextAutoGenAddPart.setDisable(true);

        /**
         * hbox initialization included here to allow following radio button's action to change spacing (InHouse vs Outsourced)
         */
        //
        HBox hboxBottomAddPart = new HBox(45, labelBottomAddPart, textFieldBottomAddPart);
        hboxBottomAddPart.setPadding(new Insets(2));

        /**
         * buttons
         */

        /**
         * radioButtonInHouseAddPart
         * updates bottom label to machine id text
         * updates spacing to appropriate value to maintain alignment
         * clears bottom text field
         */
        RadioButton radioButtonInHouseAddPart = new RadioButton("In-House");
        radioButtonInHouseAddPart.setSelected(true);
        radioButtonInHouseAddPart.setOnAction(e -> {
            labelBottomAddPart.setText("Machine ID");
            hboxBottomAddPart.setSpacing(45);
            textFieldBottomAddPart.clear();
        });

        /**
         * radioButtonOutsourcedAddPart
         * updates bottom label to company name text
         * updates spacing to appropriate value to maintain alignment
         * clears bottom text field
         */
        RadioButton radioButtonOutsourcedAddPart = new RadioButton("Outsourced");
        radioButtonOutsourcedAddPart.setOnAction(e -> {
            labelBottomAddPart.setText("Company Name");
            hboxBottomAddPart.setSpacing(20);
            textFieldBottomAddPart.clear();
        });

        /**
         * radio button toggle group
         */
        ToggleGroup radioGroupAddPart = new ToggleGroup();
        radioButtonInHouseAddPart.setToggleGroup(radioGroupAddPart);
        radioButtonOutsourcedAddPart.setToggleGroup(radioGroupAddPart);

        /**
         * buttonSaveAddPart
         * displays errors if min is greater than max, if inv is not between min and max, or if inappropriate user data is entered
         * creates InHouse or Outsourced part depending on radio button selection and adds part to inventory
         * undisplayed field 'textFieldIdAddPart' contains auto-generated value which is parsed during part creation
         * resets scene to main form
         *
         * @throws NumberFormatException if integer is parsed from non-digit string is in inv, price/cost, max, or min fields
         * also throws same exception if integer is parsed from non-digit string is in machine id field or string is parsed from digit string is in company name field)
         */
        Button buttonSaveAddPart = new Button("Save");
        buttonSaveAddPart.setOnAction(e -> {
            try {
                if ((Integer.parseInt(textFieldMinAddPart.getText())) > (Integer.parseInt(textFieldMaxAddPart.getText()))) {
                    JOptionPane.showMessageDialog(null, "Min is greater than Max");
                } else if ((Integer.parseInt(textFieldStockAddPart.getText())) < ((Integer.parseInt(textFieldMinAddPart.getText()))) ||
                        (Integer.parseInt(textFieldStockAddPart.getText())) > ((Integer.parseInt(textFieldMaxAddPart.getText())))) {
                    JOptionPane.showMessageDialog(null, "Inv is not between Min and Max");
                } else if (!(textFieldNameAddPart.getText().matches("[a-zA-Z]+"))) {
                    JOptionPane.showMessageDialog(null, "Invalid Input - Name\nName must contain letters only");
                } else if ((radioButtonInHouseAddPart.isSelected())) {
                    Part newPart;
                    Inventory.addPart(newPart = new InHouse(Integer.parseInt(textFieldIdAddPart.getText()),
                            (textFieldNameAddPart.getText()),
                            (Double.parseDouble(textFieldPriceCostAddPart.getText())),
                            (Integer.parseInt(textFieldStockAddPart.getText())),
                            (Integer.parseInt(textFieldMinAddPart.getText())),
                            (Integer.parseInt(textFieldMaxAddPart.getText()))));
                    ((InHouse) newPart).setMachineId(Integer.parseInt(textFieldBottomAddPart.getText()));
                    windowGUI.setScene(sceneMain);
                } else if ((radioButtonOutsourcedAddPart.isSelected())) {
                    if ((textFieldBottomAddPart.getText()).matches("[a-zA-Z]+")) {
                        Part newPart;
                        Inventory.addPart(newPart = new Outsourced(Integer.parseInt(textFieldIdAddPart.getText()),
                                (textFieldNameAddPart.getText()),
                                (Double.parseDouble(textFieldPriceCostAddPart.getText())),
                                (Integer.parseInt(textFieldStockAddPart.getText())),
                                (Integer.parseInt(textFieldMinAddPart.getText())),
                                (Integer.parseInt(textFieldMaxAddPart.getText()))));
                        ((Outsourced) newPart).setCompanyName(textFieldBottomAddPart.getText());
                        windowGUI.setScene(sceneMain);
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "Invalid Input - Company Name\nCompany Name contain letters only");
                    }
                }
            }
            catch (NumberFormatException numberFormatException) {
                if ((radioButtonInHouseAddPart.isSelected())) {
                    JOptionPane.showMessageDialog(null, "Invalid Input - Please Use Format Below\n" +
                            "Name: letter\nInv: number\nPrice/Cost: number\nMax: number- Min: number\nMachine ID: number");
                }
                else if ((radioButtonOutsourcedAddPart.isSelected())) {
                    JOptionPane.showMessageDialog(null, "Invalid Input - Please Use Format Below\n" +
                            "Name: letter\nInv: number\nPrice/Cost: number\nMax: number - Min: number\nCompany Name: letter");
                }
            }
                });

        /**
         * buttonCancelAddPart
         * resets scene to main form
         */
        Button buttonCancelAddPart = new Button("Cancel");
        buttonCancelAddPart.setOnAction(e -> windowGUI.setScene(sceneMain));

        /**
         * add part form layout
         */

        /**
         * label style assignment
         */
        labelAddPartTitle.setStyle(cssTitleLabelFormat);

        /**
         * hbox top
         */
        HBox hboxAddPartTop = new HBox(80, labelAddPartTitle, radioButtonInHouseAddPart, radioButtonOutsourcedAddPart);
        hboxAddPartTop.setPadding(new Insets(0, 0, 30, 0));

        /**
         * hbox builds for vbox middle
         */
        HBox hboxIDAddPart = new HBox(93, labelIdAddPart, userDisplayedTextAutoGenAddPart);
        hboxIDAddPart.setPadding(new Insets(2));
        HBox hboxNameAddPart = new HBox(73, labelNameAddPart, textFieldNameAddPart);
        hboxNameAddPart.setPadding(new Insets(2));
        HBox hboxInvAddPart = new HBox(89, labelInvAddPart, textFieldStockAddPart);
        hboxInvAddPart.setPadding(new Insets(2));
        HBox hboxPriceCostAddPart = new HBox(52, labelPriceCostAddPart, textFieldPriceCostAddPart);
        hboxPriceCostAddPart.setPadding(new Insets(2));
        HBox hboxMaxAddPart = new HBox(83, labelMaxAddPart, textFieldMaxAddPart);
        HBox hboxMinAddPart = new HBox(20, labelMinAddPart, textFieldMinAddPart);
        HBox hboxMaxMinAddPart = new HBox(hboxMaxAddPart, hboxMinAddPart);
        hboxMaxMinAddPart.setSpacing(20);
        hboxMaxMinAddPart.setPadding(new Insets(2));

        /**
         * vbox middle
         */
        VBox vboxAddPartInput = new VBox(10, hboxIDAddPart, hboxNameAddPart, hboxInvAddPart, hboxPriceCostAddPart, hboxMaxMinAddPart, hboxBottomAddPart);
        vboxAddPartInput.setPadding(new Insets(20));

        /**
         * hbox bottom
         */
        HBox hboxAddPartBottom = new HBox(10, buttonSaveAddPart, buttonCancelAddPart);
        hboxAddPartBottom.setPadding(new Insets(20));
        hboxAddPartBottom.setAlignment(Pos.TOP_RIGHT);

        /**
         * gridpane layout
         */
        GridPane gridLayoutAddPart = new GridPane();
        gridLayoutAddPart.add(hboxAddPartTop, 0, 0);
        gridLayoutAddPart.add(vboxAddPartInput, 0, 1);
        gridLayoutAddPart.add(hboxAddPartBottom, 0, 2);
        gridLayoutAddPart.setPadding(new Insets(50));

        /**
         * scene initialization
         */
        sceneAddPart = new Scene(gridLayoutAddPart, 700, 700);

//MODIFY PART FORM -----------------------------------------------------------------------------------------------------

    /**
    * Modify Part Form
    */
        /**
         * labels
         */
        Label labelModifyPartTitle = new Label("Modify Part");
        Label labelIdModifyPart = new Label("ID");
        Label labelNameModifyPart = new Label("Name");
        Label labelInvModifyPart = new Label("Inv");
        Label labelPriceCostModifyPart = new Label("Price/Cost");
        Label labelMaxModifyPart = new Label("Max");
        Label labelMinModifyPart = new Label("Min");

        /**
         * disables displayed id field
         */
        textFieldIdModifyPart.setDisable(true);

        /**
         * buttons
         */

        /**
         * radioButtonInHouseModifyPart
         * used in modify part save to swap parts
         * sets appropriate text and spacing
         * clears bottom text field if part is Outsourced
         * populates bottom text field with machine id if part is InHouse
         */
        radioButtonInHouseModifyPart.setOnAction(e -> {
            labelBottomModifyPart.setText("Machine ID");
            hboxBottomModifyPart.setSpacing(45);
            if (allParts.getSelectionModel().getSelectedItem() instanceof Outsourced) {
                textFieldBottomModifyPart.clear();
            }
            else if (allParts.getSelectionModel().getSelectedItem() instanceof InHouse) {
                textFieldBottomModifyPart.setText(String.valueOf(((InHouse) allParts.getSelectionModel().getSelectedItem()).getMachineId()));
            }
        });

        /**
         * radioButtonOutsourcedModifyPart
         * used in modify part save to swap parts
         * sets appropriate text and spacing
         * clears bottom text field if part is InHouse
         * populates bottom text field with company name if part is Outsourced
         */
        radioButtonOutsourcedModifyPart.setOnAction(e -> {
            labelBottomModifyPart.setText("Company Name");
            hboxBottomModifyPart.setSpacing(20);
            if (allParts.getSelectionModel().getSelectedItem() instanceof InHouse) {
                textFieldBottomModifyPart.clear();
            }
            else if (allParts.getSelectionModel().getSelectedItem() instanceof Outsourced) {
                textFieldBottomModifyPart.setText(((Outsourced) allParts.getSelectionModel().getSelectedItem()).getCompanyName());
            }
        });

        /**
         * radio button toggle group
         */
        ToggleGroup radioGroupModifyPart = new ToggleGroup();
        radioButtonInHouseModifyPart.setToggleGroup(radioGroupModifyPart);
        radioButtonOutsourcedModifyPart.setToggleGroup(radioGroupModifyPart);

        /**
         * buttonSaveModifyPart
         * displays errors if min is greater than max, if inv is not between min and max, or if inappropriate user data is entered
         * creates InHouse or Outsourced part depending on radio button selection and adds part to inventory
         * updates all parts table at specific index with new product
         * resets scene to main form
         *
         * @throws NumberFormatException if integer is parsed from non-digit string is in inv, price/cost, max, or min fields
         * also throws same exception if integer is parsed from non-digit string is in machine id field or string is parsed from digit string is in company name field)
         */
        Button buttonSaveModifyPart = new Button("Save");
        buttonSaveModifyPart.setOnAction(e -> {
            try {
                if ((Integer.parseInt(textFieldMinModifyPart.getText())) > (Integer.parseInt(textFieldMaxModifyPart.getText()))) {
                    JOptionPane.showMessageDialog(null, "Min is greater than Max");
                } else if ((Integer.parseInt(textFieldStockModifyPart.getText())) < ((Integer.parseInt(textFieldMinModifyPart.getText()))) ||
                        (Integer.parseInt(textFieldStockModifyPart.getText())) > ((Integer.parseInt(textFieldMaxModifyPart.getText())))) {
                    JOptionPane.showMessageDialog(null, "Inv is not between Min and Max");
                } else if (!(textFieldNameModifyPart.getText().matches("[a-zA-Z]+"))) {
                    JOptionPane.showMessageDialog(null, "Invalid Input - Name\nName must contain letters only");
                } else if ((radioButtonInHouseModifyPart.isSelected())) {
                    Part selectedPart = new InHouse(Integer.parseInt(textFieldIdModifyPart.getText()),
                            (textFieldNameModifyPart.getText()),
                            (Double.parseDouble(textFieldPriceCostModifyPart.getText())),
                            (Integer.parseInt(textFieldStockModifyPart.getText())),
                            (Integer.parseInt(textFieldMinModifyPart.getText())),
                            (Integer.parseInt(textFieldMaxModifyPart.getText())));
                    ((InHouse) selectedPart).setMachineId(Integer.parseInt(textFieldBottomModifyPart.getText()));
                    Inventory.updatePart(Integer.parseInt(textFieldIndexToUpdateModifyPart.getText()), selectedPart);
                    windowGUI.setScene(sceneMain);
                } else if ((radioButtonOutsourcedModifyPart.isSelected())) {
                    if ((textFieldBottomModifyPart.getText().matches("[a-zA-Z]+"))) {
                        Part selectedPart = new Outsourced(Integer.parseInt(textFieldIdModifyPart.getText()),
                                (textFieldNameModifyPart.getText()),
                                (Double.parseDouble(textFieldPriceCostModifyPart.getText())),
                                (Integer.parseInt(textFieldStockModifyPart.getText())),
                                (Integer.parseInt(textFieldMinModifyPart.getText())),
                                (Integer.parseInt(textFieldMaxModifyPart.getText())));
                        ((Outsourced) selectedPart).setCompanyName(textFieldBottomModifyPart.getText());
                        Inventory.updatePart(Integer.parseInt(textFieldIndexToUpdateModifyPart.getText()), selectedPart);
                        windowGUI.setScene(sceneMain);
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "Invalid Input - Company Name\nCompany Name contain letters only");
                    }
                }
            }
            catch (NumberFormatException numberFormatException) {
                if ((radioButtonInHouseModifyPart.isSelected())) {
                    JOptionPane.showMessageDialog(null, "Invalid Input - Please Use Format Below\n" +
                            "Name: letter\nInv: number\nPrice/Cost: number\nMax: number- Min: number\nMachine ID: number");
                }
                else if ((radioButtonOutsourcedModifyPart.isSelected())) {
                    JOptionPane.showMessageDialog(null, "Invalid Input - Please Use Format Below\n" +
                            "Name: letter\nInv: number\nPrice/Cost: number\nMax: number - Min: number\nCompany Name: letter");
                }
            }
        });

        /**
         * buttonCancelModifyPart
         * resets scene to main form
         */
        Button buttonCancelModifyPart = new Button("Cancel");
        buttonCancelModifyPart.setOnAction(e -> windowGUI.setScene(sceneMain));

        /**
         * modify part form layout
         */

        /**
         * label style assignment
         */
        labelModifyPartTitle.setStyle(cssTitleLabelFormat);

        /**
         * hbox top
         */
        HBox hboxModifyPartTop = new HBox(80, labelModifyPartTitle, radioButtonInHouseModifyPart, radioButtonOutsourcedModifyPart);
        hboxModifyPartTop.setPadding(new Insets(0, 0, 30, 0));

        /**
         * hbox build for vbox middle
         */
        HBox hboxIDModifyPart = new HBox(93, labelIdModifyPart, textFieldIdModifyPart);
        hboxIDModifyPart.setPadding(new Insets(2));
        HBox hboxNameModifyPart = new HBox(73, labelNameModifyPart, textFieldNameModifyPart);
        hboxNameModifyPart.setPadding(new Insets(2));
        HBox hboxInvModifyPart = new HBox(89, labelInvModifyPart, textFieldStockModifyPart);
        hboxInvModifyPart.setPadding(new Insets(2));
        HBox hboxPriceCostModifyPart = new HBox(52, labelPriceCostModifyPart, textFieldPriceCostModifyPart);
        hboxPriceCostModifyPart.setPadding(new Insets(2));
        HBox hboxMaxModifyPart = new HBox(83, labelMaxModifyPart, textFieldMaxModifyPart);
        HBox hboxMinModifyPart = new HBox(20, labelMinModifyPart, textFieldMinModifyPart);
        HBox hboxMaxMinModifyPart = new HBox(hboxMaxModifyPart, hboxMinModifyPart);
        hboxMaxMinModifyPart.setSpacing(20);
        hboxMaxMinModifyPart.setPadding(new Insets(2));

        /**
         * vbox middle
         */
        VBox vboxModifyPartInput = new VBox(10, hboxIDModifyPart, hboxNameModifyPart, hboxInvModifyPart, hboxPriceCostModifyPart, hboxMaxMinModifyPart, hboxBottomModifyPart);
        vboxModifyPartInput.setPadding(new Insets(20));

        /**
         * hbox bottom
         */
        HBox hboxModifyPartBottom = new HBox(10, buttonSaveModifyPart, buttonCancelModifyPart);
        hboxModifyPartBottom.setPadding(new Insets(20));
        hboxModifyPartBottom.setAlignment(Pos.TOP_RIGHT);

        /**
         * gridpane layout
         */
        GridPane gridLayoutModifyPart = new GridPane();
        gridLayoutModifyPart.add(hboxModifyPartTop, 0, 0);
        gridLayoutModifyPart.add(vboxModifyPartInput, 0, 1);
        gridLayoutModifyPart.add(hboxModifyPartBottom, 0, 2);
        gridLayoutModifyPart.setPadding(new Insets(50));

        /**
         * scene initialization
         */
        sceneModifyPart = new Scene(gridLayoutModifyPart, 700, 700);

//ADD PRODUCT FORM -----------------------------------------------------------------------------------------------------

    /**
    * Add Product Form
    */
        /**
         * labels
         */
        Label labelAddProductTitle = new Label("Add Product");
        Label labelIdAddProduct = new Label("ID");
        Label labelNameAddProduct = new Label("Name");
        Label labelInvAddProduct = new Label("Inv");
        Label labelPriceCostAddProduct = new Label("Price");
        Label labelMaxAddProduct = new Label("Max");
        Label labelMinAddProduct = new Label("Min");

        /**
         * disables displayed id field
         */
        userDisplayedTextAutoGenAddProduct.setDisable(true);

        /**
         * partSearchAddProduct
         * allow for part search by id or name
         * notifies user if no matching parts are found
         * sets found parts to all parts table
         * resets all parts table if search is empty
         *
         @throws NumberFormatException if integer is parsed from non-digit string
         */
        TextField partSearchAddProduct = new TextField();
        partSearchAddProduct.setPromptText("Search by Part ID or Name");
        partSearchAddProduct.setFocusTraversable(false);
        partSearchAddProduct.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!(partSearchAddProduct.getText()).isEmpty())
                try {
                    int partId = Integer.parseInt(newValue);
                    Part part = Inventory.lookupPart(partId);
                    ObservableList foundParts = FXCollections.observableArrayList();
                    if (part != null) {
                        foundParts.add(part);
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "No Parts Found With This ID");
                    }
                    allPartsAddProduct.setItems(foundParts);
                }
                catch (NumberFormatException numberFormatException) {
                    String partName = newValue;
                    ObservableList<Part> foundPartList = FXCollections.observableArrayList();
                    foundPartList = FXCollections.observableArrayList(Inventory.lookupPart(partName));
                    allPartsAddProduct.setItems(foundPartList);
                    if (foundPartList.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "No Parts Found With This Name");
                    }
                }
            else if ((partSearchAddProduct.getText()).isEmpty()) {
                allPartsAddProduct.setItems(Inventory.getAllParts());
            }
        });

        /**
         * buttons
         */

        /**
         * buttonSaveAddProduct
         * displays errors if min is greater than max, if inv is not between min and max, or if inappropriate user data is entered
         * creates product and adds product to all products table
         * undisplayed field 'textFieldIdAddProduct' contains auto-generated value which is parsed during product creation
         * sets associated parts to product
         * resets scene to main form
         *
         * @throws NumberFormatException if integer is parsed from non-digit string is in inv, price/cost, max, or min fields
         */
        Button buttonSaveAddProduct = new Button("Save");
        buttonSaveAddProduct.setOnAction(e -> {
            try {
                if ((Integer.parseInt(textFieldMinAddProduct.getText())) > (Integer.parseInt(textFieldMaxAddProduct.getText()))) {
                    JOptionPane.showMessageDialog(null, "Min is greater than Max");
                } else if ((Integer.parseInt(textFieldStockAddProduct.getText())) < ((Integer.parseInt(textFieldMinAddProduct.getText()))) ||
                        (Integer.parseInt(textFieldStockAddProduct.getText())) > ((Integer.parseInt(textFieldMaxAddProduct.getText())))) {
                    JOptionPane.showMessageDialog(null, "Inv is not between Min and Max");
                } else if (!(textFieldNameAddProduct.getText().matches("[a-zA-Z]+"))) {
                    JOptionPane.showMessageDialog(null, "Invalid Input - Name\nName must contain letters only");
                } else {
                    Product newProduct = new Product(Integer.parseInt(textFieldIdAddProduct.getText()),
                            (textFieldNameAddProduct.getText()),
                            (Double.parseDouble(textFieldPriceCostAddProduct.getText())),
                            (Integer.parseInt(textFieldStockAddProduct.getText())),
                            (Integer.parseInt(textFieldMinAddProduct.getText())),
                            (Integer.parseInt(textFieldMaxAddProduct.getText())));
                    Inventory.addProduct(newProduct);
                    newProduct.setAllAssociatedParts(associatedParts.getItems());
                    windowGUI.setScene(sceneMain);
                }
            }
            catch (NumberFormatException numberFormatException) {
                JOptionPane.showMessageDialog(null, "Invalid Input - Please Use Format Below\n" +
                        "Name: letter\nInv: number\nPrice: number\nMax: number - Min: number");
            }
        });

        /**
         * buttonCancelAddProduct
         * resets scene to main form
         */
        Button buttonCancelAddProduct = new Button("Cancel");
        buttonCancelAddProduct.setOnAction(e -> windowGUI.setScene(sceneMain));

        /**
         * buttonAddAssociatedPartAddProduct
         * gets selected part from all parts table and adds part to associate parts table
         * displays error message of no parts are selected
         */
        Button buttonAddAssociatedPartAddProduct = new Button("Add");
        buttonAddAssociatedPartAddProduct.setOnAction(e -> {
            Part part = allPartsAddProduct.getSelectionModel().getSelectedItem();
            if (part != null) {
                instanceProduct.addAssociatedPart(part);
                associatedParts.setItems(instanceProduct.getAllAssociatedParts());
            }
            else {
                JOptionPane.showMessageDialog(null, "Error - No Parts Selected");
            }
        });

        /**
         * buttonRemovePartAddProduct
         * checks to see if an associated part is selected
         * removes selected part from associated parts table
         * confirms removal
         * notifies user if associated part is not removed
         */
        Button buttonRemovePartAddProduct = new Button("Remove Associated Part");
        buttonRemovePartAddProduct.setOnAction( e -> {
            if (associatedParts.getItems().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Error - No Associated Parts Selected");
            }
            else {
                Part selectedAssociatedPart = associatedParts.getSelectionModel().getSelectedItem();
                int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure?");
                if (dialogResult == 0) {
                    instanceProduct.deleteAssociatedPart(selectedAssociatedPart);
            }
                else {
                    JOptionPane.showMessageDialog(null, "Associated Part Not Removed");
                }
            }
        });

        /**
         * add product form layout
         */

        /**
         * label style assignment
         */
        labelAddProductTitle.setStyle(cssTitleLabelFormat);


        /**
         * hbox top left
         */
        HBox hboxAddPartTopAddProduct = new HBox(labelAddProductTitle);
        hboxAddPartTopAddProduct.setPadding(new Insets(0,0,30,0));

        /**
         * hbox builds for vbox left
         */
        HBox hboxIDAddProduct = new HBox(35, labelIdAddProduct, userDisplayedTextAutoGenAddProduct);
        hboxIDAddProduct.setPadding(new Insets(2));
        HBox hboxNameAddProduct = new HBox(15, labelNameAddProduct, textFieldNameAddProduct);
        hboxNameAddProduct.setPadding(new Insets(2));
        textFieldStockAddProduct.setPrefWidth(90);
        HBox hboxStockAddProduct = new HBox(32, labelInvAddProduct, textFieldStockAddProduct);
        hboxStockAddProduct.setPadding(new Insets(2));
        textFieldPriceCostAddProduct.setPrefWidth(90);
        HBox hboxPriceCostAddProduct = new HBox(22, labelPriceCostAddProduct, textFieldPriceCostAddProduct);
        hboxPriceCostAddProduct.setPadding(new Insets(2));
        textFieldMaxAddProduct.setPrefWidth(90);
        textFieldMinAddProduct.setPrefWidth(90);
        HBox hboxMaxAddProduct = new HBox(25, labelMaxAddProduct, textFieldMaxAddProduct);
        HBox hboxMinAddProduct = new HBox(10, labelMinAddProduct, textFieldMinAddProduct);
        HBox hboxMaxMinAddProduct = new HBox(20, hboxMaxAddProduct, hboxMinAddProduct);
        hboxMaxMinAddProduct.setSpacing(20);
        hboxMaxMinAddProduct.setPadding(new Insets(2));

        /**
         * vbox left
         */
        VBox vboxAddPartInputAddProduct = new VBox(10, hboxIDAddProduct, hboxNameAddProduct, hboxStockAddProduct, hboxPriceCostAddProduct, hboxMaxMinAddProduct);
        vboxAddPartInputAddProduct.setPadding(new Insets(0,0,0,30));

        /**
         * vbox left container
         */
        VBox vboxAddProductLeftSide = new VBox (30, hboxAddPartTopAddProduct, vboxAddPartInputAddProduct);

        /**
         * vbox upper right
         */
        partSearchAddProduct.setPrefWidth(160);
        partSearchAddProduct.setMaxWidth(160);
        partSearchAddProduct.setAlignment(Pos.CENTER_RIGHT);
        VBox vboxPartUpperAddProduct = new VBox(10, partSearchAddProduct, allPartsAddProduct, buttonAddAssociatedPartAddProduct);
        vboxPartUpperAddProduct.setAlignment(Pos.CENTER_RIGHT);
        vboxPartUpperAddProduct.setPrefWidth(375);

        /**
         * vbox lower right
         */
        VBox vboxPartLowerAddProduct = new VBox(10, associatedParts, buttonRemovePartAddProduct);
        vboxPartLowerAddProduct.setAlignment(Pos.CENTER_RIGHT);
        vboxPartLowerAddProduct.setPrefWidth(375);

        /**
         * hbox bottom right
         */
        HBox hboxAddPartBottomAddProduct = new HBox(50, buttonSaveAddProduct, buttonCancelAddProduct);
        hboxAddPartBottomAddProduct.setAlignment(Pos.CENTER_RIGHT);
        hboxAddPartBottomAddProduct.setPadding(new Insets(10,0,0,0));

        /**
         * vbox right container
         */
        VBox vboxAddProductRightSide = new VBox(10, vboxPartUpperAddProduct, vboxPartLowerAddProduct, hboxAddPartBottomAddProduct);
        vboxAddProductRightSide.setPadding(new Insets(0,0,0, 200));

        /**
         * table view size restrictions
         */
        allPartsAddProduct.setPrefSize(250,200);
        allPartsAddProductIdCol.setPrefWidth(60);
        allPartsAddProductNameCol.setPrefWidth(80);
        allPartsAddProductStockCol.setPrefWidth(100);
        allPartsAddProductPriceCol.setPrefWidth(130);

        associatedParts.setPrefSize(250,200);
        associatedPartsIdCol.setPrefWidth(60);
        associatedPartsNameCol.setPrefWidth(80);
        associatedPartsStockCol.setPrefWidth(100);
        associatedPartsPriceCol.setPrefWidth(130);

        /**
         * gridpane layout
         */
        GridPane gridLayoutAddProduct = new GridPane();
        gridLayoutAddProduct.add(vboxAddProductLeftSide, 0, 0);
        gridLayoutAddProduct.add(vboxAddProductRightSide, 1, 0);
        gridLayoutAddProduct.setPadding(new Insets(30));

        /**
         * hbox container, border style, and border assignment
         */
        HBox hboxContainerAddProduct = new HBox(gridLayoutAddProduct);
        String cssContainerLayoutBorder = "-fx-border-color: black;" +
                "-fx-border-insets: 30;" + "-fx-border-width: 1px;" +
                "-fx-border-style: solid;" + "-fx-border-radius: 10;";
        hboxContainerAddProduct.setStyle(cssContainerLayoutBorder);

        /**
         * scene initialization
         */
        sceneAddProduct = new Scene(hboxContainerAddProduct);

//MODIFY PRODUCT FORM---------------------------------------------------------------------------------------------------

    /**
    * Modify Product Form
    */

        /**
         * labels
         */
        Label labelModifyProductTitle = new Label("Modify Product");
        Label labelIdModifyProduct = new Label("ID");
        Label labelNameModifyProduct = new Label("Name");
        Label labelInvModifyProduct = new Label("Inv");
        Label labelPriceCostModifyProduct = new Label("Price");
        Label labelMaxModifyProduct = new Label("Max");
        Label labelMinModifyProduct = new Label("Min");

        /**
         * disables displayed id field
         */
        textFieldIdModifyProduct.setDisable(true);

        /**
         * productSearchModifyProduct
         * allow for part search by id or name
         * notifies user if no matching parts are found
         * sets found parts to all parts table
         * resets all parts table if search is empty
         *
         @throws NumberFormatException if integer is parsed from non-digit string
         */
        TextField productSearchModifyProduct = new TextField();
        productSearchModifyProduct.setPromptText("Search by Part ID or Name");
        productSearchModifyProduct.setFocusTraversable(false);
        productSearchModifyProduct.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!(productSearchModifyProduct.getText()).isEmpty())
                try {
                    int partId = Integer.parseInt(newValue);
                    Part part = Inventory.lookupPart(partId);
                    ObservableList foundParts = FXCollections.observableArrayList();
                    if (part != null) {
                        foundParts.add(part);
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "No Parts Found With This ID");
                    }
                    allPartsModifyProduct.setItems(foundParts);
                }
                catch (NumberFormatException numberFormatException) {
                    String partName = newValue;
                    ObservableList<Part> foundPartList = FXCollections.observableArrayList();
                    foundPartList = FXCollections.observableArrayList(Inventory.lookupPart(partName));
                    allPartsModifyProduct.setItems(foundPartList);
                    if (foundPartList.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "No Parts Found With This Name");
                    }
                }
            else if ((productSearchModifyProduct.getText()).isEmpty()) {
                allPartsModifyProduct.setItems(Inventory.getAllParts());
            }
        });

        /**
         * buttons
         */

        /**
         * buttonSaveModifyProduct
         * displays errors if min is greater than max, if inv is not between min and max, or if inappropriate user data is entered
         * creates product and updates product
         * sets associated parts to product
         * updates all products table by replacing old product with new product at specific index
         * resets scene to main form
         *
         * @throws NumberFormatException if integer is parsed from non-digit string is in inv, price/cost, max, or min fields
         */
        Button buttonSaveModifyProduct = new Button("Save");
        buttonSaveModifyProduct.setOnAction(e -> {
            try {
                if ((Integer.parseInt(textFieldMinModifyProduct.getText())) > (Integer.parseInt(textFieldMaxModifyProduct.getText()))) {
                    JOptionPane.showMessageDialog(null, "Min is greater than Max");
                } else if ((Integer.parseInt(textFieldStockModifyProduct.getText())) < ((Integer.parseInt(textFieldMinModifyProduct.getText()))) ||
                        (Integer.parseInt(textFieldStockModifyProduct.getText())) > ((Integer.parseInt(textFieldMaxModifyProduct.getText())))) {
                    JOptionPane.showMessageDialog(null, "Inv is not between Min and Max");
                } else if (!(textFieldNameModifyProduct.getText().matches("[a-zA-Z]+"))) {
                    JOptionPane.showMessageDialog(null, "Invalid Input - Name\nName must contain letters only");
                } else {
                    Product newProduct = new Product(Integer.parseInt(textFieldIdModifyProduct.getText()),
                            (textFieldNameModifyProduct.getText()),
                            (Double.parseDouble(textFieldPriceCostModifyProduct.getText())),
                            (Integer.parseInt(textFieldStockModifyProduct.getText())),
                            (Integer.parseInt(textFieldMinModifyProduct.getText())),
                            (Integer.parseInt(textFieldMaxModifyProduct.getText())));
                    newProduct.setAllAssociatedParts(associatedPartsModifyProduct.getItems());
                    Inventory.updateProduct(Integer.parseInt(textFieldIndexToUpdateModifyProduct.getText()), newProduct);
                    windowGUI.setScene(sceneMain);
                }
            }
            catch (NumberFormatException numberFormatException) {
                JOptionPane.showMessageDialog(null, "Invalid Input - Please Use Format Below\n" +
                        "Name: letter\nInv: number\nPrice: number\nMax: number - Min: number");
            }
        });

        /**
         * buttonCancelModifyProduct
         * resets scene to main form
         */
        Button buttonCancelModifyProduct = new Button("Cancel");
        buttonCancelModifyProduct.setOnAction(e -> windowGUI.setScene(sceneMain));

        /**
         * buttonAddAssociatedPartModifyProduct
         * gets selected part from all parts table and adds part to associate parts table
         */
        Button buttonAddAssociatedPartModifyProduct = new Button("Add");
        buttonAddAssociatedPartModifyProduct.setOnAction(e -> {
            Product currentProduct = allProducts.getSelectionModel().getSelectedItem();
            Part part = allPartsModifyProduct.getSelectionModel().getSelectedItem();
            if (part != null) {
                currentProduct.addAssociatedPart(part);
                associatedPartsModifyProduct.setItems(currentProduct.getAllAssociatedParts());
            }
            else {
                JOptionPane.showMessageDialog(null, "Error - No Parts Selected");
            }
        });

        /**
         * buttonRemovePartModifyProduct
         * checks to see if an associated part is selected
         * removes selected part from current product and from associated parts table
         * confirms removal
         * notifies user if associated part is not removed
         */
        Button buttonRemovePartModifyProduct = new Button("Remove Associated Part");
        buttonRemovePartModifyProduct.setOnAction( e -> {
            if (associatedParts.getItems().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Error - No Associated Parts Selected");
            }
            else {
                Product currentProduct = allProducts.getSelectionModel().getSelectedItem();
                Part selectedAssociatedPart = associatedPartsModifyProduct.getSelectionModel().getSelectedItem();
                int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure?");
                if (dialogResult == 0) {
                    currentProduct.deleteAssociatedPart(selectedAssociatedPart);
                    associatedPartsModifyProduct.setItems(currentProduct.getAllAssociatedParts());
                }
                else {
                    JOptionPane.showMessageDialog(null, "Associated Part Not Removed");
                }
            }
        });

        /**
         * modify product form layout
         */

        /**
         * label style assignment
         */
        labelModifyProductTitle.setStyle(cssTitleLabelFormat);

        /**
         * hbox top left
         */
        HBox hboxModifyProductTopModifyProduct = new HBox(labelModifyProductTitle);
        hboxModifyProductTopModifyProduct.setPadding(new Insets(0,0,30,0));

        /**
         * hbox build for vbox left
         */
        HBox hboxIDModifyProduct = new HBox(35, labelIdModifyProduct, textFieldIdModifyProduct);
        hboxIDModifyProduct.setPadding(new Insets(2));
        HBox hboxNameModifyProduct = new HBox(15, labelNameModifyProduct, textFieldNameModifyProduct);
        hboxNameModifyProduct.setPadding(new Insets(2));
        textFieldStockModifyProduct.setPrefWidth(90);
        HBox hboxStockModifyProduct = new HBox(32, labelInvModifyProduct, textFieldStockModifyProduct);
        hboxStockModifyProduct.setPadding(new Insets(2));
        textFieldPriceCostModifyProduct.setPrefWidth(90);
        HBox hboxPriceCostModifyProduct = new HBox(22, labelPriceCostModifyProduct, textFieldPriceCostModifyProduct);
        hboxPriceCostModifyProduct.setPadding(new Insets(2));
        textFieldMaxModifyProduct.setPrefWidth(90);
        textFieldMinModifyProduct.setPrefWidth(90);
        HBox hboxMaxModifyProduct = new HBox(25, labelMaxModifyProduct, textFieldMaxModifyProduct);
        HBox hboxMinModifyProduct = new HBox(10, labelMinModifyProduct, textFieldMinModifyProduct);
        HBox hboxMaxMinModifyProduct = new HBox(20, hboxMaxModifyProduct, hboxMinModifyProduct);
        hboxMaxMinModifyProduct.setSpacing(20);
        hboxMaxMinModifyProduct.setPadding(new Insets(2));

        /**
         * vbox left
         */
        VBox vboxAddProductInputModifyProduct = new VBox(10, hboxIDModifyProduct, hboxNameModifyProduct, hboxStockModifyProduct, hboxPriceCostModifyProduct, hboxMaxMinModifyProduct);
        vboxAddProductInputModifyProduct.setPadding(new Insets(0,0,0,30));

        /**
         * vbox left container
         */
        VBox vboxModifyProductLeftSide = new VBox (30, hboxModifyProductTopModifyProduct, vboxAddProductInputModifyProduct);

        /**
         * vbox upper right
         */
        productSearchModifyProduct.setPrefWidth(160);
        productSearchModifyProduct.setMaxWidth(160);
        productSearchModifyProduct.setAlignment(Pos.CENTER_RIGHT);
        VBox vboxPartUpperModifyProduct = new VBox(10, productSearchModifyProduct, allPartsModifyProduct, buttonAddAssociatedPartModifyProduct);
        vboxPartUpperModifyProduct.setAlignment(Pos.CENTER_RIGHT);
        vboxPartUpperModifyProduct.setPrefWidth(375);

        /**
         * vbox lower right
         */
        VBox vboxPartLowerModifyProduct = new VBox(10, associatedPartsModifyProduct, buttonRemovePartModifyProduct);
        vboxPartLowerModifyProduct.setAlignment(Pos.CENTER_RIGHT);
        vboxPartLowerModifyProduct.setPrefWidth(375);

        /**
         * hbox bottom right
         */
        HBox hboxAddPartBottomModifyProduct = new HBox(50, buttonSaveModifyProduct, buttonCancelModifyProduct);
        hboxAddPartBottomModifyProduct.setAlignment(Pos.CENTER_RIGHT);
        hboxAddPartBottomModifyProduct.setPadding(new Insets(10,0,0,0));

        /**
         * vbox right container
         */
        VBox vboxModifyProductRightSide = new VBox(10, vboxPartUpperModifyProduct, vboxPartLowerModifyProduct, hboxAddPartBottomModifyProduct);
        vboxModifyProductRightSide.setPadding(new Insets(0,0,0, 200));

        /**
         * table view size restrictions
         */
        allPartsModifyProduct.setPrefSize(250,200);
        allPartsModifyProductIdCol.setPrefWidth(60);
        allPartsModifyProductNameCol.setPrefWidth(80);
        allPartsModifyProductStockCol.setPrefWidth(100);
        allPartsModifyProductPriceCol.setPrefWidth(130);

        associatedPartsModifyProduct.setPrefSize(250,200);
        associatedPartsModifyProductIdCol.setPrefWidth(60);
        associatedPartsModifyProductNameCol.setPrefWidth(80);
        associatedPartsModifyProductStockCol.setPrefWidth(100);
        associatedPartsModifyProductPriceCol.setPrefWidth(130);

        /**
         * gridpane layout
         */
        GridPane gridLayoutModifyProduct = new GridPane();
        gridLayoutModifyProduct.add(vboxModifyProductLeftSide, 0, 0);
        gridLayoutModifyProduct.add(vboxModifyProductRightSide, 1, 0);
        gridLayoutModifyProduct.setPadding(new Insets(30));

        /**
         * hbox container, border style, and border assignment
         */
        HBox hboxContainerModifyProduct = new HBox(gridLayoutModifyProduct);
        String cssContainerLayoutBorderModifyProduct = "-fx-border-color: black;" +
                "-fx-border-insets: 30;" + "-fx-border-width: 1px;" +
                "-fx-border-style: solid;" + "-fx-border-radius: 10;";
        hboxContainerModifyProduct.setStyle(cssContainerLayoutBorderModifyProduct);

        /**
         * scene initialization
         */
        sceneModifyProduct = new Scene(hboxContainerModifyProduct);
    }
}