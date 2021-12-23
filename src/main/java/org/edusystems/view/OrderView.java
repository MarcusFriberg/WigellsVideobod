package org.edusystems.view;
// Imports
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.edusystems.entities.Rental;

import javax.persistence.criteria.CriteriaBuilder;

public class OrderView {

    // Constructor
    public OrderView() {

    }

    /*
     * Method getContent
     * A method to return content of the HomeView to the caller.
     * @author: Marcus Friberg
     * @author: marcus.friberg@edu.edugrade.se
     * @version: 1.0
     */
    public VBox getContent() {
        // Add a page title
        Label pageTitle = new Label("Order management");
        pageTitle.setStyle("-fx-text-fill: #FFFFFF; -fx-font-size: 18");

        // Button - Add Order
        Label addOrderLabel = new Label("Add Order");
        addOrderLabel.setPadding(new Insets(-5,0,0,0));
        addOrderLabel.setStyle("-fx-text-fill: #AAAAAA; -fx-font-size: 8;");
        Image addOrderImage = new Image("/appGraphics/order-add.png");
        Image addOrderMouseOverImage = new Image("/appGraphics/order-add-mouseover.png");
        ImageView addOrderImageView = new ImageView(addOrderImage);
        VBox addOrderVBox = new VBox();
        addOrderVBox.getChildren().addAll(addOrderImageView, addOrderLabel);
        addOrderVBox.setSpacing(10);
        // Button - Add Order Events
        addOrderVBox.setOnMouseEntered(event -> {
            addOrderImageView.setImage(addOrderMouseOverImage);
            addOrderLabel.setStyle("-fx-text-fill: #DC77FA; -fx-font-size: 8");
            addOrderVBox.setStyle("-fx-cursor: hand");
        });
        addOrderVBox.setOnMouseExited(event -> {
            addOrderImageView.setImage(addOrderImage);
            addOrderLabel.setStyle("-fx-text-fill: #AAAAAA; -fx-font-size: 8");
        });
        addOrderVBox.setOnMouseClicked(event -> {
            // Code to open window to add new order
        });

        // HBox to hold buttons above the table
        HBox topButtonRow = new HBox();
        topButtonRow.getChildren().add(addOrderVBox);
        topButtonRow.setPadding(new Insets(20, 0, 10, 0));

        // Table to show all orders
        TableView<Rental> ordersTable = new TableView<Rental>();

        // VBox to hold all the content on the Order page
        VBox content = new VBox();
        content.getChildren().addAll(pageTitle, topButtonRow);
        return content;
    }
}
