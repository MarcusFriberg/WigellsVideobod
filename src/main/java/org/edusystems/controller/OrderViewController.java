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
import java.sql.Date;
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
    private Date date;

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

        // Try to get a transaction and start it, sending a TypedQuery fetching all rows in the rental table as Rental-entity's.
        // Store it in rentalList and commit the transaction.
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            TypedQuery<Rental> allRentalsQuery = entityManager.createQuery("from Rental", Rental.class);
            rentalList = allRentalsQuery.getResultList();
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
        // Return the observable list of Rental entity's.
        return FXCollections.observableArrayList(rentalList);
    }

    public void deleteRental(Rental selectedRentalToDelete) {
        // Set up the EntityManager and a transaction that is currently pointing nowhere
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        try {
            // Try to get a transaction from the EntityManager
            transaction = entityManager.getTransaction();
            // Begin the transaction
            transaction.begin();
            Rental rentalToDelete = entityManager.find(Rental.class, selectedRentalToDelete.getRentalId());
            // Call remove-method of the EntityManager on the rental-entity passed to the method to remove it
            // from the managed objects.
            entityManager.remove(rentalToDelete);

            // Call flush-method of the EntityManager to write changes to the database.
            entityManager.flush();
            // Commit the changes
            transaction.commit();

        } catch (Exception e) {
            // If there was an arror and the transaction is not null, rollback the transaction.
            // Then printStackTrace for the exception.
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            // Close the EntityManager
            entityManager.close();
        }
    }

    public void returnRental(Rental selectedRentalToReturn) {
        // Set up the EntityManager and a transaction that is currently pointing nowhere
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        try {
            // Try to get a transaction from the EntityManager
            transaction = entityManager.getTransaction();
            // Begin the transaction
            transaction.begin();
            // Add todays date into now
            Date now = new Date(System.currentTimeMillis());
            // Get the object from the database
            Rental rentalToReturn = entityManager.find(Rental.class, selectedRentalToReturn.getRentalId());
            // Call detach-method of the EntityManager on the rental-entity passed to the method to remove it
            // from the managed objects.
            entityManager.detach(rentalToReturn);
            // Modify the object loaded from database
            rentalToReturn.setReturnDate(now);
            // Merge back the object to be managed by hibernate
            entityManager.merge(rentalToReturn);
            // Call flush-method of the EntityManager to write changes to the database.
            entityManager.flush();
            // Commit the changes
            transaction.commit();

            // Remove the passed to the method from rentalsObservableList, change the returnDate and put it back.
            // This is a workaround, the tableview will update if the objects in the Observable list are removed or new
            // objects added. But the objects in the list are not observed, so changing the value of a property will not
            // cause the tableview to reflect the change. Thats why i remove them and put them back exactly the same but
            // with a returndate.
            orderView.getRentalsObservableList().remove(selectedRentalToReturn);
            selectedRentalToReturn.setReturnDate(now);
            orderView.getRentalsObservableList().add(selectedRentalToReturn);

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
    }

}
