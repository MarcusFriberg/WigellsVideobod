package org.edusystems.controller;
// Imports
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.edusystems.Main;
import org.edusystems.entities.Rental;
import org.edusystems.view.OrderView;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

/*
* Class OrderViewController - A class to provide functionality to the OrderView.
* @author: Marcus Friberg
* @author: marcus.friberg@edu.edugrade.se
 */
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

    /*
    * Method getAllRentals - A method fetch all Rentals (rows) in the Rental table and return an observablelist of Rental entitys.
    * @author: Marcus Friberg
    * @author: marcus.friberg@edu.edugrade.se
    * @version: 1.0
     */
    public ObservableList<Rental> getAllRentals() {
        // Set up the EntityManager and a transaction that is currently pointing nowhere
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        // Set up a list currently pointing nowhere that will point to the result of the query later and be returned
        // by this method as a FXCollections.observableArrayList
        List<Rental> rentalList = null;

        // Try get a transaction and start it, sending a TypedQuery fetching all rows in the rental table as Rental-entity's.
        // Store it in rentalList and commit the transaction.
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            TypedQuery<Rental> allRentalsQuery = entityManager.createQuery("from Rental", Rental.class);
            rentalList = allRentalsQuery.getResultList();
            transaction.commit();

        } catch (Exception e) {
            // If there was an arror and the transaction is not null, rollback the transaction.
            // Then printStackTrace for the exception.
            if(transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            // Close the EntityManager
            entityManager.close();
        }
        // Return the observable list of Rental entity's.
        return FXCollections.observableArrayList(rentalList);
    }
}
