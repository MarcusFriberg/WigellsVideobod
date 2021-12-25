package org.edusystems.controller;

import org.edusystems.Main;
import org.edusystems.view.HomeView;

import javax.persistence.EntityManagerFactory;

public class HomeViewController {
    // Variables
    private HomeView homeView;
    private ViewController viewController;
    private EntityManagerFactory ENTITY_MANAGER_FACTORY;

    public HomeViewController(HomeView homeView, ViewController viewController) {
        this.homeView = homeView;
        this.viewController = viewController;
        ENTITY_MANAGER_FACTORY = Main.ENTITY_MANAGER_FACTORY;
    }
}
