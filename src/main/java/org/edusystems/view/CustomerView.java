package org.edusystems.view;
// Imports
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.edusystems.controller.CustomerViewController;
import org.edusystems.entities.TextFieldResult;

/*
 * Class CustomerView
 * This class creates the graphics for the Customerview.
 * It uses a Tableview and lambda-functions that enables the user to search for users and create new, delete and update
 * existing customers.
 * @author: Linda Djurström
 * @author: Marcus Friberg
 * @author: Albin Wallström
 * @author: linda.djurstrom@edu.edugrade.se
 * @author: marcus.friberg@edu.edugrade.se
 * @author: albin.wallstrom@edu.edugrade.se
 * @version: 1.0.
 */
public class CustomerView {
    // Variables
    private CustomerViewController customerViewController;
    CreateCustomerView createCustomerView = new CreateCustomerView(customerViewController);
    private MainView mainView;
    ObservableList<TextFieldResult> data = FXCollections.observableArrayList();
    TextField textFieldSearchField = new TextField();
    TableView tableViewResultArea = new TableView();
    TableColumn storeIDCol = new TableColumn("StoreID");
    TableColumn customerIDCol = new TableColumn("CustomerID");
    TableColumn firstNameCol = new TableColumn("First name");
    TableColumn lastNameCol = new TableColumn("Last name");
    TableColumn emailCol = new TableColumn("Email");
    TableColumn addressCol = new TableColumn("Address");
    TableColumn address2Col = new TableColumn("Address2");
    TableColumn postalCodeCol = new TableColumn("Postal code");
    TableColumn districtCol = new TableColumn("District");
    TableColumn cityCol = new TableColumn("City");
    TableColumn countryCol = new TableColumn("Country");
    TableColumn phoneCol = new TableColumn("Phone");
    Button bSearch = new Button("Search");
    Button bUpdate = new Button("Update");
    Button bDelete = new Button("Delete");
    Button bCreate = new Button("Create customer");
    Label labelInfoBannerCanNotDelete = new Label("This customer can't be deleted, make sure that the customer \n has returned " +
            "all the rentals and payed all the fees.");
    HBox hBoxInfoBannerCanNotDelete = new HBox();
    HBox hBoxSearchField = new HBox();
    HBox hBoxSearchSettings = new HBox();
    HBox hBoxResultsField = new HBox();
    HBox hBoxChange = new HBox();
    VBox vBoxSearch = new VBox();
    VBox content = new VBox();
    boolean graphicsCreated = false;

    // Constructor
    public CustomerView(MainView mainView) {
        this.mainView = mainView;
        customerViewController = new CustomerViewController(this, mainView.getViewController());
    }

    public void createGraphics() {
        //Setting graphics for components created earlier. Adding children to Hbox/vbox.
        textFieldSearchField.setPromptText("Search...");
        textFieldSearchField.setMinWidth(600);
        textFieldSearchField.setMinHeight(50);
        textFieldSearchField.setStyle("-fx-border-color: #b42be9; -fx-border-width: 3");
        tableViewResultArea.setMinHeight(400);
        tableViewResultArea.setMinWidth(900);
        tableViewResultArea.setEditable(false);
        labelInfoBannerCanNotDelete.setStyle("-fx-text-fill: #ff0000");
        bSearch.setMinHeight(44);
        tableViewResultArea.setStyle("-fx-border-color: #b42be9");
        storeIDCol.setStyle("-fx-border-color: #b42be9; ");
        customerIDCol.setStyle("-fx-border-color: #b42be9; ");
        firstNameCol.setStyle("-fx-border-color: #b42be9; ");
        lastNameCol.setStyle("-fx-border-color: #b42be9; ");
        emailCol.setStyle("-fx-border-color: #b42be9; ");
        addressCol.setStyle("-fx-border-color: #b42be9; ");
        address2Col.setStyle("-fx-border-color: #b42be9; ");
        postalCodeCol.setStyle("-fx-border-color: #b42be9; ");
        districtCol.setStyle("-fx-border-color: #b42be9; ");
        cityCol.setStyle("-fx-border-color: #b42be9; ");
        countryCol.setStyle("-fx-border-color: #b42be9; ");
        phoneCol.setStyle("-fx-border-color: #b42be9; ");
        hBoxSearchField.getChildren().addAll(textFieldSearchField, bSearch);
        hBoxSearchField.setAlignment(Pos.BASELINE_CENTER);
        hBoxSearchField.setPadding(new Insets(0, 0, 5, 50));
        hBoxSearchSettings.setAlignment(Pos.BASELINE_CENTER);
        hBoxSearchSettings.setPadding(new Insets(0, 0, 0, 50));
        hBoxResultsField.getChildren().addAll(tableViewResultArea);
        hBoxResultsField.setAlignment(Pos.BASELINE_CENTER);
        hBoxResultsField.setPadding(new Insets(5, 0, 5, 50));
        hBoxChange.getChildren().addAll(bCreate, bUpdate, bDelete);
        hBoxChange.setAlignment(Pos.BASELINE_RIGHT);
        hBoxChange.setPadding(new Insets(5, 0, 5, 0));
        hBoxInfoBannerCanNotDelete.getChildren().addAll(labelInfoBannerCanNotDelete);
        hBoxInfoBannerCanNotDelete.setVisible(false);
        hBoxInfoBannerCanNotDelete.setPadding(new Insets(0, 0, 0, 50));
        vBoxSearch.getChildren().addAll(hBoxSearchField, hBoxSearchSettings, hBoxResultsField, hBoxChange, hBoxInfoBannerCanNotDelete);
        //Adding the columns to the tableview.
        tableViewResultArea.getColumns().addAll(storeIDCol, customerIDCol, firstNameCol, lastNameCol, emailCol, addressCol, address2Col,
                postalCodeCol, districtCol, cityCol, countryCol, phoneCol);

        /*Setting the value the different cells should contain from the class TextFieldResult. Using PropertyValueFactory.
        The column is linked to the information in object, by the variable-name in the class TextFieldResult.
         */
        storeIDCol.setCellValueFactory(
                new PropertyValueFactory<TextFieldResult, Integer>("storeID")
        );
        customerIDCol.setCellValueFactory(
                new PropertyValueFactory<TextFieldResult, Integer>("customerID")
        );
        firstNameCol.setCellValueFactory(
                new PropertyValueFactory<TextFieldResult, String>("firstName")
        );
        lastNameCol.setCellValueFactory(
                new PropertyValueFactory<TextFieldResult, String>("lastName")
        );
        emailCol.setCellValueFactory(
                new PropertyValueFactory<TextFieldResult, String>("email")
        );
        addressCol.setCellValueFactory(
                new PropertyValueFactory<TextFieldResult, Integer>("address")
        );
        address2Col.setCellValueFactory(
                new PropertyValueFactory<TextFieldResult, String>("address2")
        );
        postalCodeCol.setCellValueFactory(
                new PropertyValueFactory<TextFieldResult, String>("postalCode")
        );
        districtCol.setCellValueFactory(
                new PropertyValueFactory<TextFieldResult, String>("district")
        );
        cityCol.setCellValueFactory(
                new PropertyValueFactory<TextFieldResult, String>("city")
        );
        countryCol.setCellValueFactory(
                new PropertyValueFactory<TextFieldResult, String>("country")
        );
        phoneCol.setCellValueFactory(
                new PropertyValueFactory<TextFieldResult, String>("phone")
        );
        content.getChildren().addAll(vBoxSearch);
        graphicsCreated = true;
    }


    /*
     * Method getContent
     * A method to return content of the HomeView to the caller.
     * @author: Marcus Friberg
     * @author: Linda Djurström
     * @author: marcus.friberg@edu.edugrade.se
     * @author: linda.djurstrom@edu.edugrade.se
     * @version: 1.0
     */
    public VBox getContent() {
        if(!graphicsCreated) {
            createGraphics();
        }

        //Function for click on searchbutton.
        bSearch.setOnAction(event -> {
            //Text in hBoxInfoBannerCanNotDelete turns unvisible again.
            hBoxInfoBannerCanNotDelete.setVisible(false);
            /*Takes the text from the searchfield and uses it as an parameter to a function in customerViewController. Returns
            /*an observable list and stores in data.*/
            data = customerViewController.searchCustomer(textFieldSearchField);
            //Fills the tableview with the data-list, fetched from the method-call above.
            tableViewResultArea.setItems(data);
        });

        //Function for click on any key.
        textFieldSearchField.setOnKeyTyped(event -> {
            //Text in hBoxInfoBannerCanNotDelete turns unvisible again.
            hBoxInfoBannerCanNotDelete.setVisible(false);
            /*Takes the text from the searchfield and uses it as an parameter to a function in customerViewController. Returns
            /*an observable list and stores in data.*/
            data = customerViewController.searchCustomer(textFieldSearchField);
            //Fills the tableview with the data-list, fetched from the method-call above.
            tableViewResultArea.setItems(data);
        });

        //Function for click on delete.
        bDelete.setOnAction(event -> {
            //Text in hBoxInfoBannerCanNotDelete turns unvisible again.
            hBoxInfoBannerCanNotDelete.setVisible(false);

            //Grabs the index-value from the tableview-object clicked on.
            int customerIndex = tableViewResultArea.getSelectionModel().getSelectedIndex();
            //If there is no value choosen at info-banner shows on the screen. The label turns visible.
            if(customerIndex < 0)
            {
                labelInfoBannerCanNotDelete.setText("You need to chose a customer to delete first.");
                hBoxInfoBannerCanNotDelete.setVisible(true);
            }

            /*If there is an index, the object stored in the data-list is fetched by the customerIndex-value and the customerID
            is also fetched in its turn by the object itself.
             */
            int customerID = data.get(customerIndex).getCustomerID();
            //A method-call to a function in the customerviewController. CustomerID and the hBox+ label is sent in as parameters to the function.
            customerViewController.deleteCustomer(customerID, hBoxInfoBannerCanNotDelete, labelInfoBannerCanNotDelete);
            //The table updates itself, and removes the deletedcustomer from the view by a method-call to the customerViewControllers function.
            data = customerViewController.searchCustomer(textFieldSearchField);
            tableViewResultArea.setItems(data);
        });


        //
        bUpdate.setOnAction(event -> {
            hBoxInfoBannerCanNotDelete.setVisible(false);
            int customerIndex = tableViewResultArea.getSelectionModel().getSelectedIndex();
            if(customerIndex < 0)
            {
                labelInfoBannerCanNotDelete.setText("You need to choose a customer to update first.");
                hBoxInfoBannerCanNotDelete.setVisible(true);
            }

            /*If there is an index, the object stored in the data-list is fetched by the customerIndex-value and the customerID
            is also fetched in its turn by the object itself.
             */
            int customerID = data.get(customerIndex).getCustomerID();


            createCustomerView.update(customerID);
            data = customerViewController.searchCustomer(textFieldSearchField);
            tableViewResultArea.setItems(data);
        } );

        //
        bCreate.setOnAction(event -> {
            //Text in hBoxInfoBannerCanNotDelete turns unvisible again.
            hBoxInfoBannerCanNotDelete.setVisible(false);
            //Creates a new view/window for the user to create new customers.
            createCustomerView.create();
            data = customerViewController.searchCustomer(textFieldSearchField);
            tableViewResultArea.setItems(data);
        });

        //A call to the method in the same class. Enables the tableview to be pre-filled when the user enters the customerview.
        showAllCustomers();
        //Returns all the content in this method when the method is called upon from Mainview.
        return content;
    }

    /*
     * Method showAllCustomers
     * Method that makes it possible to have the searchfield pre-filled with information when the customer enters the customerview,
     * by calling upon it.
     * @author: Marcus Friberg
     * @author: marcus.friberg@edu.edugrade.se
     * @version: 1.0
     */
    public void showAllCustomers() {
        data = customerViewController.searchCustomer(textFieldSearchField);
        tableViewResultArea.setItems(data);
    }
}