package org.edusystems.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.edusystems.Main;
import org.edusystems.entities.Customer;
import org.edusystems.entities.Inventory;
import org.edusystems.entities.Rental;
import org.edusystems.entities.Staff;
import org.edusystems.view.CreateOrderView;
import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public class CreateOrderViewController {
    // Variables
    private CreateOrderView createOrderView;
    private EntityManagerFactory ENTITY_MANAGER_FACTORY;
    private ViewController viewController;
    private Staff userLogedIn;

    public CreateOrderViewController(CreateOrderView createOrderView) {
        this.createOrderView = createOrderView;
        this.ENTITY_MANAGER_FACTORY = Main.ENTITY_MANAGER_FACTORY;
        this.viewController = createOrderView.getOrderViewController().getViewController();
        this.userLogedIn = viewController.getUserLoggedIn();

    }

    /*
     * Method getMatchingCustomers - A method fetch all Customers (rows) in the Customer table that matches the search and return an observableList of Customer entitys.
     * @author: Marcus Friberg
     * @author: marcus.friberg@edu.edugrade.se
     * @version: 1.0
     */
    public ObservableList<Customer> getMatchingCustomers(String searchText) {
        // Set up the EntityManager and a transaction that is currently pointing nowhere
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        // Set up a list currently pointing nowhere that will contain entities matching the searchText and be returned
        // by this method as a FXCollections.observableArrayList
        List<Customer> customerEntityList = FXCollections.observableArrayList();
        // This list will store the customerId's matching the searchText
        List<Short> matchingCustomerId;

        // Try get a transaction and start it, sending a native query fetching all customerId's matching the searchText.
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            Query matchingCustomers = entityManager.createNativeQuery("SELECT customer_id FROM customer WHERE " +
                    "first_name LIKE '%" + searchText + "%' OR last_name LIKE '%" + searchText + "%'");
            // Store the resultList in matchingCustomerId
            matchingCustomerId = matchingCustomers.getResultList();
            // If matchingCustomerID is not empty
            if(!matchingCustomerId.isEmpty()) {
                // Get the Entity for each customerID and add it to the customerEntityList
                for(int i = 0; i < matchingCustomerId.size(); i++) {
                    customerEntityList.add(entityManager.find(Customer.class, matchingCustomerId.get(i)));
                }
            }
            // Commit the transaction
            transaction.commit();

        } catch (Exception e) {
            // If there was an error and the transaction is not null, rollback the transaction.
            // Then printStackTrace for the exception.
            if(transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            // Close the EntityManager
            entityManager.close();
        }
        // Return the observable list of Customer entity's.
        return FXCollections.observableArrayList(customerEntityList);
    }

    /*
     * Method getMatchingInventories - A method fetch all Inventories (rows) in the Inventory table where the joined
     * filmTitle matches the search and return an observableList of inventory entities.
     * @author: Marcus Friberg
     * @author: marcus.friberg@edu.edugrade.se
     * @version: 1.0
     */
    public ObservableList<Inventory> getMatchingInventories(String searchText) {
        // Set up the EntityManager and a transaction that is currently pointing nowhere
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        // Set up a list currently pointing nowhere that will contain entities matching the searchText and be returned
        // by this method as a FXCollections.observableArrayList
        List<Inventory> inventoryEntityList = FXCollections.observableArrayList();
        // This list will store the customerId's matching the searchText
        List<Short> matchingInventoryId;

        // Try get a transaction and start it, sending a native query fetching all customerId's matching the searchText.
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            Query matchingInventories = entityManager.createNativeQuery("SELECT inventory_id FROM (inventory INNER JOIN film " +
                    "ON inventory.film_id = film.film_id) WHERE title LIKE '%" + searchText + "%' OR release_year LIKE '%" + searchText + "%'");
            // Store the resultList in matchingCustomerId
            matchingInventoryId = matchingInventories.getResultList();
            // If matchingCustomerID is not empty
            if(!matchingInventoryId.isEmpty()) {
                // Get the Entity for each customerID and add it to the customerEntityList
                for(int i = 0; i < matchingInventoryId.size(); i++) {
                    inventoryEntityList.add(entityManager.find(Inventory.class, matchingInventoryId.get(i)));
                }
            }
            // Commit the transaction
            transaction.commit();

        } catch (Exception e) {
            // If there was an error and the transaction is not null, rollback the transaction.
            // Then printStackTrace for the exception.
            if(transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            // Close the EntityManager
            entityManager.close();
        }
        // Return the observable list of Customer entity's.
        return FXCollections.observableArrayList(inventoryEntityList);
    }

    public Boolean createRental(Short customerID, int inventoryID) {
        // Set up the EntityManager and a transaction that is currently pointing nowhere
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        Date date = new Date(System.currentTimeMillis());

        try {
            // Try to get a transaction from the EntityManager
            transaction = entityManager.getTransaction();
            // Begin the transaction
            transaction.begin();
            // Make a new Rental-entity
            Rental rental = new Rental();
            // Set rentalDate = now
            rental.setRentalDate(new Date(System.currentTimeMillis()));
            // Set customer to the provided customer
            rental.setCustomer(entityManager.find(Customer.class, customerID));
            // Set inventory to the provided inventory
            rental.setInventoryItem(entityManager.find(Inventory.class, inventoryID));
            // Set staff to the staff logged in
            rental.setStaff(entityManager.find(Staff.class, userLogedIn.getStaffId()));
            // Set timestamp to now
            rental.setLastUpdate(new Timestamp(date.getTime()));

            // Persist the new rental
            entityManager.persist(rental);
            // Commit the transaction
            transaction.commit();

            // Add the new rental to the observable list
            createOrderView.getOrderView().getRentalsObservableList().add(rental);

        } catch (Exception e) {
            // If there was an error and the transaction is not null, rollback the transaction.
            // Then printStackTrace for the exception.
            if(transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            // Return false because something failed.
            return false;
        } finally {
            // Close the EntityManager
            entityManager.close();
        }
        // Here the rental has been created, return true.
        return true;
    }

}
