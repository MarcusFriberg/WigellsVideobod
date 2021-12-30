package org.edusystems.controller;
// Imports
import javafx.stage.Stage;
import org.edusystems.entities.Staff;
import org.edusystems.view.MainView;

public class ViewController {
    private Stage primaryStage;
    private MainView mainView;
    private Staff userLoggedIn;


    // Constructor
    public ViewController(Stage primaryStage) {
        this.primaryStage = primaryStage;
        mainView = new MainView(primaryStage, this);
        showView("loginView");
    }
    // showView Method
    public void showView(String view) {
        switch (view) {
            case "homeView" :
                mainView.present("homeView");
                break;
            case "orderView" :
                mainView.present("orderView");
                break;
            case "movieView" :
                mainView.present("movieView");
                break;
            case "customerView" :
                mainView.present("customerView");
                break;
            case "staffView" :
                mainView.present("staffView");
                break;
            case "loginView" :
                mainView.login();
                break;
            default:
                System.out.println("Viewrequest not valid");
        }
    }

    // Setters
    public void setUserLoggedIn(Staff userLoggedIn) {
        this.userLoggedIn = userLoggedIn;
    }

    // Getters
    public String getUserLoggedInFullName() {
        return userLoggedIn.getFirstName() + " " + userLoggedIn.getLastName();
    }
    public Staff getUserLoggedIn() {
        return userLoggedIn;
    }
}
