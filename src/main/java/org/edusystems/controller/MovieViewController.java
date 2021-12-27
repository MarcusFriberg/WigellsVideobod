package org.edusystems.controller;

// Imports
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.edusystems.Main;
import org.edusystems.entities.Inventory;
import org.edusystems.view.MovieView;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

/*
 * Class MovieViewController - A class to provide functionality to the MovieView.
 * @author: Matilda Wintence
 * @author: matilda.wintence@edu.edugrade.se
 * @version: 1.0
 */
public class MovieViewController {
    // Variables
    private MovieView movieView;
    private ViewController viewController;
    private EntityManagerFactory ENTITY_MANAGER_FACTORY;

    public MovieViewController(MovieView movieView, ViewController viewController) {
        this.movieView = movieView;
        this.viewController = viewController;
        ENTITY_MANAGER_FACTORY = Main.ENTITY_MANAGER_FACTORY;

        /*
         * Method getAllInventory - A method fetch all Inventory (rows) in the Inventory table and return an observablelist of Inventory entities.
         * @author: Matilda Wintence
         * @author: matilda.wintence@edu.edugrade.se
         * @version: 1.0
         */
    }
        public ObservableList<Inventory> getAllInventory () {
            // Set up the EntityManager and a transaction that is currently pointing nowhere
            EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
            EntityTransaction transaction = null;
            // Set up a list currently pointing nowhere that will point to the result of the query later and be returned
            // by this method as a FXCollections.observableArrayList
            List<Inventory> inventoryList = null;

            // Try to get a transaction and start it, sending a TypedQuery fetching all rows in the inventory table as Inventory-entities.
            // Store it in inventoryList and commit the transaction.
            try {
                transaction = entityManager.getTransaction();
                transaction.begin();
                TypedQuery<Inventory> allInventoryQuery = entityManager.createQuery("from Inventory", Inventory.class);
                inventoryList = allInventoryQuery.getResultList();
                transaction.commit();

            } catch (Exception e) {
                // If there was an error and the transaction is not null, rollback the transaction.
                // Then printStackTrace for the exception.
                if (transaction != null) {
                    transaction.rollback();
                }
                e.printStackTrace();
            } finally {
                // Close the EntityManager
                entityManager.close();
            }
            // Return the observable list of Rental entity's.
            return FXCollections.observableArrayList(inventoryList);
        }
    }
