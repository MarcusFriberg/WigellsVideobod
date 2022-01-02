package org.edusystems.view;

// Imports
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import org.edusystems.controller.HomeViewController;

/*
 * Class HomeView
 * A class to create a scene for the homeView of the application
 * where the user can get an overview of the current state of the system.
 * @param: Constructor receives the primaryStage as a parameter.
 * @author: Marcus Friberg
 * @author: marcus.friberg@edu.edugrad.se
 * @author: Matilda Wintence
 * @author: matilda.wintence@edu.edugrad.se
 * @author: Martin Andersson
 * @author: martin.andersson@edu.edugrad.se
 * @version: 1.0
 */
public class HomeView {
    // Variables
    private MainView mainView;
    private HomeViewController homeViewController;

    // Constructor
    public HomeView(MainView mainView) {
        this.mainView = mainView;
        homeViewController = new HomeViewController(this, mainView.getViewController());
    }

    /*
     * Method getContent
     * A method to return content of the HomeView to the caller.
     * @author: Marcus Friberg
     * @author: marcus.friberg@edu.edugrade.se
     * @version: 1.0
     */
    public VBox getContent() {
        Label label = new Label("Popular movies");
        label.setPadding(new Insets(0,0,50,0));
        label.setStyle("-fx-text-fill: #FFFFFF; -fx-font-size: 52");

        Label labelMovie1 = new Label("Greyhound ft. Tomas Wigell");
        labelMovie1.setStyle("-fx-text-fill: #FFFFFF; -fx-font-size: 12");
        labelMovie1.setPadding(new Insets(0,87,5,0));

        Label labelMovie2 = new Label("Mr & Mrs Smith ft. Linnea & Tobias Landén");
        labelMovie2.setStyle("-fx-text-fill: #FFFFFF; -fx-font-size: 12");
        labelMovie2.setPadding(new Insets(0,45,5,0));

        Label labelMovie3 = new Label("Terminator ft. Tomas Wigell");
        labelMovie3.setStyle("-fx-text-fill: #FFFFFF; -fx-font-size: 12");
        labelMovie3.setPadding(new Insets(0,0,5,44));


        Image movie1 = new Image("/appGraphics/greyhound.png");
        Image movie2 = new Image("/appGraphics/mrmrssmith.png");
        Image movie3 = new Image("/appGraphics/terminator.png");

        ImageView image1 = new ImageView(movie1);
        ImageView image2 = new ImageView(movie2);
        ImageView image3 = new ImageView(movie3);

        image1.setFitHeight(325);
        image1.setFitWidth(225);
        image2.setFitHeight(325);
        image2.setFitWidth(225);
        image3.setFitHeight(325);
        image3.setFitWidth(225);

        HBox imageHBox = new HBox();
        imageHBox.setSpacing(50);
        imageHBox.setAlignment(Pos.CENTER);
        imageHBox.setPadding(new Insets(5,0,5,0));
        imageHBox.getChildren().addAll(image1, image2, image3);

        HBox labelHBox = new HBox();
        labelHBox.setAlignment(Pos.CENTER);
        labelHBox.getChildren().addAll(label, labelMovie1, labelMovie2, labelMovie3);

        imageHBox.setStyle("");



        VBox vBox = new VBox();
        vBox.getChildren().addAll(label, labelHBox, imageHBox);






        return vBox;
    }
}