package org.edusystems.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.stage.Stage;
import org.edusystems.controller.CreateCustomerViewController;
import org.edusystems.controller.CustomerViewController;
import org.edusystems.entities.Address;
import org.edusystems.entities.City;
import org.edusystems.entities.Country;
import org.edusystems.entities.Customer;

/*
 * Class CreateCustomerView
 * This class creates the graphics for the CreateCustomerView-Window.
 * It uses Textfields, labels and lambda-functions.
 * @author: Linda Djurström
 * @author: linda.djurstrom@edu.edugrade.se
 * @version: 1.0.
 */
public class CreateCustomerView {
    //Variabels
    CreateCustomerViewController createCustomerViewController;
    CustomerViewController customerViewController;
    TextField textFieldStoreID = new TextField();
    TextField textFieldFirstName = new TextField();
    TextField textFieldLastName = new TextField();
    TextField textFieldEmail = new TextField();
    TextField textFieldAddress = new TextField();
    TextField textFieldAddress2 = new TextField();
    TextField textFieldPostalCode = new TextField();
    TextField textFieldDistrict = new TextField();
    TextField textFieldCity = new TextField();
    TextField textFieldCountry = new TextField();
    TextField textFieldPhone = new TextField();
    Label labelStoreID = new Label("StoreID");
    Label labelFirstName = new Label("First name");
    Label labelLastName = new Label("Last name");
    Label labelEmail = new Label("Email");
    Label labelAddress = new Label("Address");
    Label labelAddress2 = new Label("Address 2");
    Label labelPostalCode = new Label("Postal code");
    Label labelDistrict = new Label("Disctrict");
    Label labelCity = new Label("City");
    Label labelCountry = new Label("Country");
    Label labelPhone = new Label("Phone");
    Button bCreateUpdate = new Button("Create");
    HBox hBoxStoreID = new HBox();
    HBox hBoxFirstName = new HBox();
    HBox hBoxLastName = new HBox();
    HBox hBoxEmail = new HBox();
    HBox hBoxAddress = new HBox();
    HBox hBoxAddress2 = new HBox();
    HBox hBoxPostalCode = new HBox();
    HBox hBoxDistrict = new HBox();
    HBox hBoxCity = new HBox();
    HBox hBoxCountry = new HBox();
    HBox hBoxPhone = new HBox();
    HBox hBoxButtonsCreate = new HBox();
    HBox hBoxAddCustomer = new HBox();
    VBox vBoxCreateCustomer = new VBox();
    BorderPane borderPane = new BorderPane();
    Stage stage = new Stage();
    Scene scene = new Scene(borderPane, 400, 450);
    boolean graphicsCreated = false;

    //Constructor
    public CreateCustomerView(CustomerViewController customerViewController) {
        this.customerViewController = customerViewController;
        createCustomerViewController = new CreateCustomerViewController(customerViewController);
    }

    /*
     * Method update
     * Graphics for updatewindow.
     * @params: customerID.
     * @author: Linda Djurström
     * @author: linda.djurstrom@edu.edugrade.se
     * @version: 1.0
     */
    public void update(int customerID) {
        stage.setTitle("Update customer");
        bCreateUpdate.setText("Update");
        if(!graphicsCreated) {
            createView();
        }
        Customer customer = createCustomerViewController.getCustomer(customerID);
        Address address = createCustomerViewController.getAddress(customer.getAddressId());
        City city = createCustomerViewController.getCity(address.getCity_id());
        Country country = createCustomerViewController.getCountry(city.getCountry_id());
        textFieldStoreID.setText(customer.getStoreId().toString());
        textFieldFirstName.setText(customer.getFirstName());
        textFieldLastName.setText(customer.getLastName());
        textFieldEmail.setText(customer.getEmail());
        textFieldAddress.setText(address.getAddress());
        textFieldAddress2.setText(address.getAddress2());
        textFieldPostalCode.setText(address.getPostal_code());
        textFieldDistrict.setText(address.getDistrict());
        textFieldCity.setText(city.getCity());
        textFieldCountry.setText(country.getCountry());
        textFieldPhone.setText(address.getPhone());

        borderPane.setCenter(vBoxCreateCustomer);
        stage.setScene(scene);
        stage.show();

        bCreateUpdate.setOnAction(event -> {
            createCustomerViewController.update(customer, address, textFieldStoreID, textFieldFirstName, textFieldLastName, textFieldEmail,
                    textFieldAddress, textFieldAddress2, textFieldPostalCode, textFieldDistrict, textFieldCity, textFieldCountry, textFieldPhone);
            clearTextFields();
        });

        stage.setOnCloseRequest(event -> {
            clearTextFields();
        });
    }

    /*
     * Method create
     * Graphics for createWindow.
     * @author: Linda Djurström
     * @author: linda.djurstrom@edu.edugrade.se
     * @version: 1.0
     */
    public void create() {
        stage.setTitle("Create customer");
        bCreateUpdate.setText("Create");
        if(!graphicsCreated) {
            createView();
        }
        borderPane.setCenter(vBoxCreateCustomer);
        stage.setScene(scene);
        stage.show();

        //A lambda function for the button bCreate.
        bCreateUpdate.setOnAction(event -> {
            createCustomerViewController.create(textFieldStoreID, textFieldFirstName, textFieldLastName, textFieldEmail,
                    textFieldAddress, textFieldAddress2, textFieldPostalCode, textFieldDistrict, textFieldCity, textFieldCountry, textFieldPhone);
            //Clears all the textfields.
            clearTextFields();
        });
    }

    /*
     * Method clearTextFields
     * Clears all textfields.
     * @author: Linda Djurström
     * @author: linda.djurstrom@edu.edugrade.se
     * @version: 1.0
     */
    public void clearTextFields() {
        //Clears all the textfields.
        textFieldAddress.clear();
        textFieldAddress2.clear();
        textFieldPostalCode.clear();
        textFieldDistrict.clear();
        textFieldCity.clear();
        textFieldCountry.clear();
        textFieldPhone.clear();
        textFieldFirstName.clear();
        textFieldLastName.clear();
        textFieldEmail.clear();
    }

    /*
     * Method createView
     * Creates the graphics for the window and initiates it.
     * @params: takes a string-value that determines if the window should create a new customer or update an already exsisting customer.
     * @author: Linda Djurström
     * @author: linda.djurstrom@edu.edugrade.se
     * @version: 1.0
     */
    public void createView() {
        //Editing the variables, adding children and sets the stage.
        textFieldStoreID.setText("1");
        textFieldStoreID.setFont(Font.font(null, FontPosture.ITALIC, 15));
        textFieldStoreID.setEditable(false);
        labelStoreID.setMinWidth(90);
        labelFirstName.setMinWidth(90);
        labelLastName.setMinWidth(90);
        labelEmail.setMinWidth(90);
        labelAddress.setMinWidth(90);
        labelAddress2.setMinWidth(90);
        labelPostalCode.setMinWidth(90);
        labelDistrict.setMinWidth(90);
        labelCity.setMinWidth(90);
        labelCountry.setMinWidth(90);
        labelPhone.setMinWidth(90);
        hBoxAddCustomer.setAlignment(Pos.BASELINE_CENTER);
        hBoxAddCustomer.setPadding(new Insets(0, 0, 0, 0));
        hBoxStoreID.getChildren().addAll(labelStoreID, textFieldStoreID);
        hBoxStoreID.setAlignment(Pos.BASELINE_CENTER);
        hBoxFirstName.getChildren().addAll(labelFirstName, textFieldFirstName);
        hBoxFirstName.setAlignment(Pos.BASELINE_CENTER);
        hBoxLastName.getChildren().addAll(labelLastName, textFieldLastName);
        hBoxLastName.setAlignment(Pos.BASELINE_CENTER);
        hBoxEmail.getChildren().addAll(labelEmail, textFieldEmail);
        hBoxEmail.setAlignment(Pos.BASELINE_CENTER);
        hBoxAddress.getChildren().addAll(labelAddress, textFieldAddress);
        hBoxAddress.setAlignment(Pos.BASELINE_CENTER);
        hBoxAddress2.getChildren().addAll(labelAddress2, textFieldAddress2);
        hBoxAddress2.setAlignment(Pos.BASELINE_CENTER);
        hBoxPostalCode.getChildren().addAll(labelPostalCode, textFieldPostalCode);
        hBoxPostalCode.setAlignment(Pos.BASELINE_CENTER);
        hBoxDistrict.getChildren().addAll(labelDistrict, textFieldDistrict);
        hBoxDistrict.setAlignment(Pos.BASELINE_CENTER);
        hBoxCity.getChildren().addAll(labelCity, textFieldCity);
        hBoxCity.setAlignment(Pos.BASELINE_CENTER);
        hBoxCountry.getChildren().addAll(labelCountry, textFieldCountry);
        hBoxCountry.setAlignment(Pos.BASELINE_CENTER);
        hBoxPhone.getChildren().addAll(labelPhone, textFieldPhone);
        hBoxPhone.setAlignment(Pos.BASELINE_CENTER);
        hBoxButtonsCreate.getChildren().addAll(bCreateUpdate);
        hBoxButtonsCreate.setAlignment(Pos.BASELINE_RIGHT);
        hBoxButtonsCreate.setPadding(new Insets(0, 60, 0, 0));
        textFieldStoreID.setText("1");
        textFieldStoreID.setFont(Font.font(null, FontPosture.ITALIC, 15));
        textFieldStoreID.setEditable(false);
        labelStoreID.setMinWidth(90);
        labelFirstName.setMinWidth(90);
        labelLastName.setMinWidth(90);
        labelEmail.setMinWidth(90);
        labelAddress.setMinWidth(90);
        labelAddress2.setMinWidth(90);
        labelPostalCode.setMinWidth(90);
        labelDistrict.setMinWidth(90);
        labelCity.setMinWidth(90);
        labelCountry.setMinWidth(90);
        labelPhone.setMinWidth(90);
        vBoxCreateCustomer.getChildren().addAll(hBoxAddCustomer, hBoxStoreID, hBoxFirstName, hBoxLastName, hBoxEmail,
                hBoxAddress, hBoxAddress2, hBoxPostalCode, hBoxDistrict, hBoxCity, hBoxCountry, hBoxPhone, hBoxButtonsCreate);
        vBoxCreateCustomer.setPadding(new Insets(50,0 , 0, 0));
        graphicsCreated = true;
    }
}
