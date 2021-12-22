package org.edusystems.view;
// Imports
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;


public class CustomerView {

    // Constructor
    public CustomerView() {

    }

    /*
     * Method getContent
     * A method to return content of the HomeView to the caller.
     * @author: Marcus Friberg
     * @author: marcus.friberg@edu.edugrade.se
     * @version: 1.0
     */
    public VBox getContent() {
        VBox content = new VBox(); // Must remain
        Label label = new Label("Customer management");
        label.setStyle("-fx-text-fill: #FFFFFF; -fx-font-size: 18");

        content.getChildren().add(label); // Replace label with real content
        return content;
    }


}
