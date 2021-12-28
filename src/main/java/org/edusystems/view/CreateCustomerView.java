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
    Button bCreate = new Button("Create");
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

    public void update(int customerID) {
        if(!graphicsCreated) {
            createView();
        }
        borderPane.setCenter(vBoxCreateCustomer);
        stage.setScene(scene);
        stage.show();
    }

    public void create() {
        if(!graphicsCreated) {
            createView();
        }
        borderPane.setCenter(vBoxCreateCustomer);
        stage.setScene(scene);
        stage.show();

        //A lambda function for the button bCreate.
        bCreate.setOnAction(event -> {
            /*Takes the Textfield the user has filled in for country, sends it in to a method in createCustomerViewController,
             stores the returned value in a short-variabel.
             */
            Short countryID = createCustomerViewController.searchCountryID(textFieldCountry);
            /*If there is no country-match in the method above the return value is -1, and it enters the if-sats.
            A new method in createCustomerViewController is called upon, the method creates a new country with the value from
            textFieldCountry.
             */
            if(countryID < 0) {
                createCustomerViewController.createCountry(textFieldCountry);
                //When a new country is created a new attempt to grab the new countryID is made.
                countryID = createCustomerViewController.searchCountryID(textFieldCountry);
            }
            /*When the country is made it uses the countryID and textFieldCity(that the user has added) in a new method to search
            if the city already exist in the database.
             */
            Short cityID = createCustomerViewController.searchCityID(textFieldCity, countryID);
            //If it doesnt exist the return value is -1 and we enter the if-sats down under.
            if(cityID < 0) {
                /*Calls upon a method to create a new city in createCustomerViewController. The countryID required for the table city is
                sent in, from the Short-variable cityID.
                 */
                createCustomerViewController.createCity(textFieldCity, countryID);
                //Grabs the newly made cityID.
                cityID = createCustomerViewController.searchCityID(textFieldCity,countryID);
            }

            //Calls upon a method to create a new address with the cityID-value grabbed above.
            createCustomerViewController.addAddress(textFieldAddress, textFieldAddress2, textFieldPostalCode, textFieldDistrict, textFieldPhone, cityID);
            //Grabs the addressID from the newly created address.
            int addressID = createCustomerViewController.searchAddressID();

            //Creates a new customer with the values the user has added in the textfields and the newly created addressID.
            createCustomerViewController.addCustomer(textFieldStoreID, textFieldFirstName, textFieldLastName, textFieldEmail, addressID);

            //Clears all the textfields.
            clearTextFields();
        });
    }

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
        hBoxButtonsCreate.getChildren().addAll(bCreate);
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
