package org.edusystems.controller;
// Imports
import javafx.stage.Stage;
import org.edusystems.view.MainView;

public class ViewController {
    Stage primaryStage;
    MainView mainView;

    // Constructor
    public ViewController(Stage primaryStage) {
        this.primaryStage = primaryStage;
        mainView = new MainView(primaryStage, this);
        showView("homeView");
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
            default:
                System.out.println("Viewrequest not valid");
        }
    }
}
