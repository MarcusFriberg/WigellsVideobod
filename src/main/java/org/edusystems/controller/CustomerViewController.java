package org.edusystems.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import org.edusystems.Main;
import org.edusystems.entities.*;
import org.edusystems.view.CustomerView;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
/*
 * Class CustomerViewController.
 * This class holds all the functions/methods for the CustomerView.
 * @author: Linda Djurström
 * @author: Marcus Friberg
 * @author: Albin Wallström
 * @author: linda.djurstrom@edu.edugrade.se
 * @author: marcus.friberg@edu.edugrade.se
 * @author: albin.wallstrom@edu.edugrade.se
 * @version: 1.0.
 */
public class CustomerViewController {
    // Variables
    private CustomerView customerView;
    private ViewController viewController;
    private EntityManagerFactory ENTITY_MANAGER_FACTORY;

    //Constructor
    public CustomerViewController(CustomerView customerView, ViewController viewController){
        this.customerView = customerView;
        this.viewController = viewController;
        ENTITY_MANAGER_FACTORY = Main.ENTITY_MANAGER_FACTORY;
    }

    /*
     * Method searchCustomer
     * Method that searches in the database and fetches the objects that relates to the user's entry in the SearchField.
     * Params: TextFieldSearchField.
     * Returns: ObservableList.
     * @author: Linda Djurström
     * @author: linda.djurstrom@edu.edugrade.se
     * @version: 1.0
     */
    public ObservableList<TextFieldResult> searchCustomer(TextField textFieldSearchField) {
        //Crates the observableList data, used later in the code.
        final ObservableList<TextFieldResult> data = FXCollections.observableArrayList();
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        //An arraylist used later to store customerID's.
        List<Short> customerIDList = new ArrayList<>(1);
        //Arraylist's used later to store objects.
        List<Customer> customersList = new ArrayList<>();
        List<Address> addressList = new ArrayList<>();
        List<City> cityList = new ArrayList<>();
        List<Country> countryList = new ArrayList<>();

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            //NativeQuery to the database, stored in Query.
            Query query = entityManager.createNativeQuery("SELECT customer_id FROM (((customer INNER JOIN address " +
                    "ON customer.address_id = address.address_id) INNER JOIN city ON address.city_id = city.city_id) " +
                    "INNER JOIN country ON city.country_id = country.country_id) " +
                    "WHERE first_name LIKE " + "'%" + textFieldSearchField.getText() + "%'" + "OR " +
                    "last_name LIKE  " + "'%" + textFieldSearchField.getText() + "%'" + "OR " +
                    "email LIKE  " + "'%" + textFieldSearchField.getText() + "%'" + "OR " +
                    "address LIKE  " + "'%" + textFieldSearchField.getText() + "%'" + "OR " +
                    "address2 LIKE  " + "'%" + textFieldSearchField.getText() + "%'" + "OR " +
                    "district LIKE  " + "'%" + textFieldSearchField.getText() + "%'" + "OR " +
                    "postal_code LIKE  " + "'%" + textFieldSearchField.getText() + "%'" + "OR " +
                    "phone LIKE  " + "'%" + textFieldSearchField.getText() + "%'" + "OR " +
                    "city LIKE  " + "'%" + textFieldSearchField.getText() + "%'" + "OR " +
                    "country LIKE  " + "'%" + textFieldSearchField.getText() + "%'" + "OR " +
                    "country LIKE  " + "'%" + textFieldSearchField.getText() + "%'");

            //The query stores all the customerID's that matches the users search in the SearchField in the customerIDList.
            customerIDList = query.getResultList();

            //If there are hits in the search it continues in the if-sats.
            if (!customerIDList.isEmpty()) {
                //The loop continues as many times as the size of the customerIDList.
                for (int i = 0; i < customerIDList.size(); i++) {
                    /*The loop uses entityManager to grab objects via the primaryKey. The primaryKey in customer is customerID.
                    The object is added to the customerList.
                     */
                    customersList.add(entityManager.getReference(Customer.class, customerIDList.get(i)));
                    //Via the customerObject you can fetch the addressID, and grab the addressObject (addressID is primarykey in addresstable)
                    addressList.add(entityManager.getReference(Address.class, customersList.get(i).getAddressId()));
                    //cityID is reachable via the addressObject now stored in addressList.
                    cityList.add(entityManager.getReference(City.class, addressList.get(i).getCity_id()));
                    //countryID is reachable via the cityObject now stored in cityList.
                    countryList.add(entityManager.getReference(Country.class, cityList.get(i).getCountry_id()));
                }
            }

            /*New objects from the TextFieldResultClass is created with all the information from the objects customer, city, country and address.
            The objects are stored in the observableList data.
             */
            for (int i = 0; i < customerIDList.size(); i++) {
                data.add(new TextFieldResult(customersList.get(i).getCustomerId(), customersList.get(i).getFirstName(), customersList.get(i).getLastName(),
                        customersList.get(i).getEmail(), addressList.get(i).getAddress(), addressList.get(i).getAddress2(), addressList.get(i).getPostal_code(),
                        addressList.get(i).getDistrict(), cityList.get(i).getCity(), countryList.get(i).getCountry(), addressList.get(i).getPhone(),
                        customersList.get(i).getStoreId()));
            }
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        //Returns the data-list.
        return data;
    }

    /*
     * Method deleteCustomer
     * Method that deletes the customer via customerID. A infoBanner is shown when a customer can't be deleted.
     * Params: customerID, hBoxInfoBannerCanNotDelete, labelInfoBannerCanNotDelete.
     * @author: Linda Djurström
     * @author: linda.djurstrom@edu.edugrade.se
     * @version: 1.0
     */
    public void deleteCustomer(int customerID, HBox hBoxInfoBannerCanNotDelete, Label labelInfoBannerCanNotDelete) {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            //Query to delete the customer.
            Query query = entityManager.createNativeQuery("DELETE FROM customer WHERE customer_id = " + customerID);
            query.executeUpdate();
            transaction.commit();
            //If a customer can't be deleted the infoBanner with the information to the customer is shown.
        } catch (PersistenceException f) {
            labelInfoBannerCanNotDelete.setText("Denna kund går inte att radera än, se till att kunden \n återlämnat " +
                    "alla filmer och betalat sina avgifter.");
            hBoxInfoBannerCanNotDelete.setVisible(true);
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }
}


