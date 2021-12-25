package org.edusystems.controller;

import org.edusystems.Main;
import org.edusystems.view.MovieView;

import javax.persistence.EntityManagerFactory;
import javax.swing.text.View;

public class MovieViewController {
    // Variables
    private MovieView movieView;
    private ViewController viewController;
    private EntityManagerFactory ENTITY_MANAGER_FACTORY;

    public MovieViewController(MovieView movieView, ViewController viewController) {
        this.movieView = movieView;
        this.viewController = viewController;
        ENTITY_MANAGER_FACTORY = Main.ENTITY_MANAGER_FACTORY;
    }
}
