package org.edusystems.view;

import javafx.beans.Observable;
import javafx.beans.value.ObservableStringValue;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainView {
    private Stage primaryStage;
    private FrameView frameView;
    private CustomerView customerView;
    private HomeView homeView;
    VBox content;

    public MainView(Stage primaryStage) {
        this.primaryStage = primaryStage;
        frameView = new FrameView();
        customerView = new CustomerView();
        homeView = new HomeView();
        present("homeView");
    }

    public void present(String view) {
        switch (view) {
            case "homeView" :
                content = homeView.getContent();
                break;
            case "customerView" :
                content = customerView.getContent();
            default:
                System.out.println("Call to none existing view");
        }
        VBox mainVBox = frameView.getFrame(content);

        // Make a new Scene containing the stackPane and set the size as the backgrounds size
        Scene scene = new Scene(mainVBox, 1280, 768);
        // Set this scene as the scene of primaryStage
        primaryStage.setScene(scene);
        // Set the heigt of the meny VBOX to the primaryStage height minus the topHbar height
        // Show the stage
        primaryStage.show();
    }

}
