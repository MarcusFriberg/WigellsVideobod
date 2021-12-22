package org.edusystems.controller;

import javafx.stage.Stage;
import org.edusystems.view.CustomerView;
import org.edusystems.view.FrameView;
import org.edusystems.view.HomeView;
import org.edusystems.view.MainView;

public class ViewController {
    Stage primaryStage;
    MainView mainView;
    FrameView frameView;
    HomeView homeView;
    CustomerView customerView;

    public ViewController(Stage primaryStage) {
        this.primaryStage = primaryStage;
        mainView = new MainView(primaryStage);
        frameView = new FrameView();
        homeView = new HomeView();
        customerView = new CustomerView();
    }

    public void showView(String view) {
        switch (view) {
            case "homeView" :
                mainView.present("homeView");
                break;
            case "customerView" :
                mainView.present("customerView");
                break;
            default:
                System.out.println("Viewrequest not valid");
        }
    }
}
