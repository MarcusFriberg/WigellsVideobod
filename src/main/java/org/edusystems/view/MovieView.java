package org.edusystems.view;
// Imports
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import org.edusystems.controller.MovieViewController;

public class MovieView {
    // Variables
    private MainView mainView;
    private MovieViewController movieViewController;

    // Constructor
    public MovieView(MainView mainView) {
        this.mainView = mainView;
        movieViewController = new MovieViewController(this, mainView.getViewController());
    }

    /*
     * Method getContent
     * A method to return content of the HomeView to the caller.
     * @author: Marcus Friberg
     * @author: marcus.friberg@edu.edugrade.se
     * @version: 1.0
     */
    public VBox getContent() {
        VBox content = new VBox();
        Label label = new Label("MovieView is here :)");
        label.setStyle("-fx-text-fill: #FFFFFF; -fx-font-size: 18");
        content.getChildren().add(label);
        return content;
    }
}
