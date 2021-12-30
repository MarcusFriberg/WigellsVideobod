package org.edusystems.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.edusystems.Main;
import org.edusystems.entities.Customer;
import org.edusystems.entities.Rental;
import org.edusystems.view.CreateOrderView;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class CreateOrderViewController {
    // Variables
    private CreateOrderView createOrderView;
    private EntityManagerFactory ENTITY_MANAGER_FACTORY;

    public CreateOrderViewController(CreateOrderView createOrderView) {
        this.createOrderView = createOrderView;
        this.ENTITY_MANAGER_FACTORY = Main.ENTITY_MANAGER_FACTORY;

    }

    /*
     * Method getAllCustomers - A method fetch all Customers (rows) in the Customer table and return an observablelist of Customer entitys.
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

}
