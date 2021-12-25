package org.edusystems.controller;

import org.edusystems.Main;
import org.edusystems.view.OrderView;

import javax.persistence.EntityManagerFactory;

public class OrderViewController {
    // Variables
    private OrderView orderView;
    private ViewController viewController;
    private EntityManagerFactory ENTITY_MANAGER_FACTORY;

    public OrderViewController(OrderView orderView, ViewController viewController) {
        this.orderView = orderView;
        this.viewController = viewController;
        ENTITY_MANAGER_FACTORY = Main.ENTITY_MANAGER_FACTORY;
    }
}
