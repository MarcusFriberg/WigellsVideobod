package org.edusystems.view;
// Imports
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.edusystems.controller.ViewController;

public class MainView {
    private Stage primaryStage;
    private FrameView frameView;
    private HomeView homeView;
    private OrderView orderView;
    private MovieView movieView;
    private CustomerView customerView;
    private StaffView staffView;
    ViewController viewController;
    VBox content;

    public MainView(Stage primaryStage, ViewController viewController) {
        this.primaryStage = primaryStage;
        this.viewController = viewController;
        frameView = new FrameView(viewController);
        homeView = new HomeView();
        orderView = new OrderView();
        movieView = new MovieView();
        customerView = new CustomerView();
        staffView = new StaffView();

        present("homeView");
    }

    public void present(String view) {
        switch (view) {
            case "homeView" :
                content = homeView.getContent();
                break;
            case "orderView" :
                content = orderView.getContent();
                break;
            case "movieView" :
                content = movieView.getContent();
                break;
            case "customerView" :
                content = customerView.getContent();
                break;
            case "staffView" :
                content = staffView.getContent();
            default:
                System.out.println("Call to none existing view");
        }
        content.setPadding(new Insets(50,50,50,50));
        VBox mainVBox = frameView.getFrame(content, view);
        // Make a new Scene containing the stackPane and set the size as the backgrounds size
        Scene scene = new Scene(mainVBox, 1280, 768);
        // Set this scene as the scene of primaryStage
        primaryStage.setScene(scene);
        // Set the heigt of the meny VBOX to the primaryStage height minus the topHbar height
        // Show the stage
        primaryStage.show();
    }

}
