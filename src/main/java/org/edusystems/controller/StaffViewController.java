package org.edusystems.controller;

import org.edusystems.Main;
import org.edusystems.view.StaffView;
import javax.persistence.EntityManagerFactory;
import javax.swing.text.View;

public class StaffViewController {
    // Variables
    private StaffView staffView;
    private ViewController viewController;
    private EntityManagerFactory ENTITY_MANAGER_FACTORY;

    public StaffViewController(StaffView staffView, ViewController viewController) {
        this.staffView = staffView;
        this.viewController = viewController;
        ENTITY_MANAGER_FACTORY = Main.ENTITY_MANAGER_FACTORY;
    }
 }
