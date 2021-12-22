package org.edusystems.view;
// Imports
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import org.edusystems.controller.ViewController;

public class FrameView {
    ViewController viewController;

    // Constructor
    public FrameView(ViewController viewController) {
        this.viewController = viewController;
    }

    public VBox getFrame(VBox content, String selectedView) {
        // Top HBox containing logo
        Image topLogoImage = new Image("/appGraphics/edusystems.png");
        ImageView topLogoImageView = new ImageView(topLogoImage);
        HBox topHBox = new HBox();
        topHBox.getChildren().add(topLogoImageView);
        topHBox.setPrefHeight(100);
        topHBox.setStyle("-fx-background-color: #000000");

        // Menu option - Home
        Label homeLabel = new Label("Home");
        homeLabel.setPadding(new Insets(-10,0,0,0));
        homeLabel.setStyle("-fx-text-fill: #AAAAAA");
        Image homeImage = new Image("/appGraphics/home.png");
        Image homeMouseOverImage = new Image("/appGraphics/home-mouseover.png");
        Image homeSelectedImage = new Image("/appGraphics/home-selected.png");
        ImageView homeImageView = new ImageView(homeImage);
        homeImageView.setScaleX(0.5);
        homeImageView.setScaleY(0.5);
        VBox homeVBox = new VBox();
        homeVBox.setAlignment(Pos.CENTER);
        homeVBox.getChildren().addAll(homeImageView,homeLabel);
        if(!selectedView.equals("homeView")) {
            homeVBox.setOnMouseEntered(event -> {
                homeImageView.setImage(homeMouseOverImage);
                homeLabel.setStyle("-fx-text-fill: #DC77FA");
                homeVBox.setStyle("-fx-cursor: hand");
            });
            homeVBox.setOnMouseExited(event -> {
                homeImageView.setImage(homeImage);
                homeLabel.setStyle("-fx-text-fill: #AAAAAA");
            });
            homeVBox.setOnMouseClicked(event -> {
                viewController.showView("homeView");
            });
        } else {
            homeImageView.setImage((homeSelectedImage));
        }

        // Menu option - Order
        Label orderLabel = new Label("Order");
        orderLabel.setPadding(new Insets(-10,0,0,0));
        orderLabel.setStyle("-fx-text-fill: #AAAAAA");
        Image orderImage = new Image("/appGraphics/order.png");
        Image orderMouseOverImage = new Image("/appGraphics/order-mouseover.png");
        Image orderSelectedImage = new Image("/appGraphics/order-selected.png");
        ImageView orderImageView = new ImageView(orderImage);
        orderImageView.setScaleX(0.5);
        orderImageView.setScaleY(0.5);
        VBox orderVBox = new VBox();
        orderVBox.setAlignment(Pos.CENTER);
        orderVBox.getChildren().addAll(orderImageView,orderLabel);
        if(!selectedView.equals("orderView")) {
            orderVBox.setOnMouseEntered(event -> {
                orderImageView.setImage(orderMouseOverImage);
                orderLabel.setStyle("-fx-text-fill: #DC77FA");
                orderVBox.setStyle("-fx-cursor: hand");
            });
            orderVBox.setOnMouseExited(event -> {
                orderImageView.setImage(orderImage);
                orderLabel.setStyle("-fx-text-fill: #AAAAAA");
            });
            orderVBox.setOnMouseClicked(event -> {
                viewController.showView("orderView");
            });
        } else {
                orderImageView.setImage((orderSelectedImage));
        }

        // Menu option - Movies
        Label movieLabel = new Label("Movies");
        movieLabel.setPadding(new Insets(-10,0,0,0));
        movieLabel.setStyle("-fx-text-fill: #AAAAAA");
        Image movieImage = new Image("/appGraphics/movies.png");
        Image movieMouseOverImage = new Image("/appGraphics/movies-mouseover.png");
        Image movieSelectedImage = new Image("/appGraphics/movies-selected.png");
        ImageView movieImageView = new ImageView(movieImage);
        movieImageView.setScaleX(0.5);
        movieImageView.setScaleY(0.5);
        VBox movieVBox = new VBox();
        movieVBox.setAlignment(Pos.CENTER);
        movieVBox.getChildren().addAll(movieImageView,movieLabel);
        if(!selectedView.equals("movieView")) {
            movieVBox.setOnMouseEntered(event -> {
                movieImageView.setImage(movieMouseOverImage);
                movieLabel.setStyle("-fx-text-fill: #DC77FA");
                movieVBox.setStyle("-fx-cursor: hand");
            });
            movieVBox.setOnMouseExited(event -> {
                movieImageView.setImage(movieImage);
                movieLabel.setStyle("-fx-text-fill: #AAAAAA");
            });
            movieVBox.setOnMouseClicked(event -> {
                viewController.showView("movieView");
            });
        } else {
            movieImageView.setImage((movieSelectedImage));
        }

        // Menu option - Customer
        Label customerLabel = new Label("Customer");
        customerLabel.setPadding(new Insets(-10,0,0,0));
        customerLabel.setStyle("-fx-text-fill: #AAAAAA");
        Image customerImage = new Image("/appGraphics/customer.png");
        Image customerMouseOverImage = new Image("/appGraphics/customer-mouseover.png");
        Image customerSelectedImage = new Image("/appGraphics/customer-selected.png");
        ImageView customerImageView = new ImageView(customerImage);
        customerImageView.setScaleX(0.5);
        customerImageView.setScaleY(0.5);
        VBox customerVBox = new VBox();
        customerVBox.setAlignment(Pos.CENTER);
        customerVBox.getChildren().addAll(customerImageView,customerLabel);
        if(!selectedView.equals("customerView")) {
            customerVBox.setOnMouseEntered(event -> {
                customerImageView.setImage(customerMouseOverImage);
                customerLabel.setStyle("-fx-text-fill: #DC77FA");
                // implement code to change mouse cursor to finger
                customerVBox.setStyle("-fx-cursor: hand");

            });
            customerVBox.setOnMouseExited(event -> {
                customerImageView.setImage(customerImage);
                customerLabel.setStyle("-fx-text-fill: #AAAAAA");
            });
            customerVBox.setOnMouseClicked(event -> {
                viewController.showView("customerView");
            });
        } else {
            customerImageView.setImage((customerSelectedImage));
        }

        // Menu option - Staff
        Label staffLabel = new Label("Staff");
        staffLabel.setPadding(new Insets(-10,0,0,0));
        staffLabel.setStyle("-fx-text-fill: #AAAAAA");
        Image staffImage = new Image("/appGraphics/staff.png");
        Image staffMouseOverImage = new Image("/appGraphics/staff-mouseover.png");
        Image staffSelectedImage = new Image("/appGraphics/staff-selected.png");
        ImageView staffImageView = new ImageView(staffImage);
        staffImageView.setScaleX(0.5);
        staffImageView.setScaleY(0.5);
        VBox staffVBox = new VBox();
        staffVBox.setAlignment(Pos.CENTER);
        staffVBox.getChildren().addAll(staffImageView,staffLabel);
        if(!selectedView.equals("staffView")) {
            staffVBox.setOnMouseEntered(event -> {
                staffImageView.setImage(staffMouseOverImage);
                staffLabel.setStyle("-fx-text-fill: #DC77FA");
                // implement code to change mouse cursor to finger
                staffVBox.setStyle("-fx-cursor: hand");
            });
            staffVBox.setOnMouseExited(event -> {
                staffImageView.setImage(staffImage);
                staffLabel.setStyle("-fx-text-fill: #AAAAAA");
            });
            staffVBox.setOnMouseClicked(event -> {
                viewController.showView("staffView");
            });
        } else {
            staffImageView.setImage((staffSelectedImage));
        }
        VBox menuVBox = new VBox();
        menuVBox.getChildren().addAll(homeVBox, orderVBox, movieVBox, customerVBox, staffVBox);
        menuVBox.setStyle("-fx-background-color: #000000");
        menuVBox.setPrefHeight(668);
        Pane contentPane = new Pane();
        contentPane.setStyle("-fx-background-color: #333231");
        contentPane.setMinWidth(1180);
        contentPane.setMinHeight(668);
        contentPane.getChildren().add(content);
        HBox menuAndContentHBox = new HBox();
        menuAndContentHBox.getChildren().addAll(menuVBox, contentPane);
        VBox mainVBox = new VBox();
        mainVBox.getChildren().addAll(topHBox, menuAndContentHBox);

        return mainVBox;
    }
}
