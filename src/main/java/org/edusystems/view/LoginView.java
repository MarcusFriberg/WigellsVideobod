package org.edusystems.view;

// Imports
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import org.edusystems.controller.LoginViewController;

/*
 * Class LoginView
 * A class to create a scene for the loginView of the application
 * where the user can login.
 * @param: Constructor receives the primaryStage as a parameter.
 * @author: Marcus Friberg
 * @author: marcus.friberg@edu.edugrad.se
 * @version: 1.0
 */
public class LoginView {
    private LoginViewController loginViewController;
    private MainView mainView;
    public Label alertLabel;

    // Constructor
    public LoginView(MainView mainView) {
        this.mainView = mainView;
        loginViewController = new LoginViewController(this, mainView.getViewController());
    }

    /*
     * Method present
     * A method to present the startView.
     * Creates the layout of the first view that appears when game is started.
     * Presents the created scene to the primaryStage that was provided with
     * the call to the constructor.
     * @author: Marcus Friberg
     * @author: marcus.friberg@edu.edugrade.se
     * @version: 1.0
     */
    public VBox getContent() {
        // First row in View is the image
        Image loginImage = new Image("appGraphics/wigellsvideobodlogocats.png");
        ImageView loginImageView = new ImageView(loginImage);
        HBox imageHBox = new HBox();
        imageHBox.getChildren().add(loginImageView);
        imageHBox.setAlignment(Pos.CENTER);
        imageHBox.setPadding(new Insets(200,0,5,0));
        // Second row in the View is the username input field
        Label userLabel = new Label("username: ");
        TextField userTextField = new TextField();
        userTextField.setPromptText("username");
        HBox userHBox = new HBox();
        userHBox.getChildren().addAll(userLabel,userTextField);
        userHBox.setAlignment(Pos.CENTER);
        userHBox.setPadding(new Insets(5,0,0,0));
        // Third row in the View is the password input field
        Label passwordLabel = new Label("password: ");
        TextField passwordTextField = new PasswordField();
        passwordTextField.setPromptText("password");
        HBox passwordHBox = new HBox();
        passwordHBox.getChildren().addAll(passwordLabel,passwordTextField);
        passwordHBox.setAlignment(Pos.CENTER);
        passwordHBox.setPadding(new Insets(5,0,5,0));
        // Fourth row in the View is the login button
        Button loginButton = new Button("Login");
        loginButton.setAlignment(Pos.CENTER);
        loginButton.setOnAction(event -> {
            loginViewController.userLogin(userTextField.getText(), passwordTextField.getText());
        });
        // Fifth row is an alert message
        alertLabel = new Label("");
        alertLabel.setStyle("-fx-text-fill: #FF0000; -fx-font-size: 18");
        alertLabel.setVisible(false);
        // Last row is the company logo
        Image edusystemsImage = new Image("appGraphics/edusystems.png");
        ImageView edusystemsImageView = new ImageView(edusystemsImage);
        edusystemsImageView.setScaleX(0.5);
        edusystemsImageView.setScaleY(0.5);
        HBox edusystemsHBox = new HBox();
        edusystemsHBox.getChildren().add(edusystemsImageView);
        edusystemsHBox.setAlignment(Pos.CENTER);
        edusystemsHBox.setPadding(new Insets(150,0,5,0));
        // VBox will contain the image, username/password input fields and login button
        VBox vBox = new VBox();
        vBox.getChildren().addAll(imageHBox, userHBox, passwordHBox, loginButton, alertLabel, edusystemsHBox);
        vBox.setAlignment(Pos.CENTER);
        vBox.setStyle("");
        return vBox;
    }
}


