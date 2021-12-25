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

public class CustomerViewController {
    // Variables
    private CustomerView customerView;
    private ViewController viewController;
    private EntityManagerFactory ENTITY_MANAGER_FACTORY;
    // En observable list av Customer

    public CustomerViewController(CustomerView customerView, ViewController viewController){
        this.customerView = customerView;
        this.viewController = viewController;
        ENTITY_MANAGER_FACTORY = Main.ENTITY_MANAGER_FACTORY;
        // Anrop till en method1 som hämtar alla customers i databasen och lagrar som en observable list of customers
    }

    // Metod1 för att hämta alla customers i tabellen customers och skapar objekt(entity's) i en "obervable list1"

    // Metod2 för att filtrera "observable list1" och returnera dom objekt som matchar sökningen

    // Beskrivning
    public ObservableList<TextFieldResult> searchCustomer(TextField textFieldSearchField) {
        // Vad händer här?
        final ObservableList<TextFieldResult> data = FXCollections.observableArrayList();
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        List<Short> customerIDList = new ArrayList<>(1);

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            // Vad händer här?
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
            customerIDList = query.getResultList();

            List<Customer> customersList = new ArrayList<>();
            List<Address> addressList = new ArrayList<>();
            List<City> cityList = new ArrayList<>();
            List<Country> countryList = new ArrayList<>();
            //List<Store> storeList = new ArrayList<>(1);

            // Beskrivning
            if (!customerIDList.isEmpty()) {
                for (int i = 0; i < customerIDList.size(); i++) {
                    customersList.add(entityManager.getReference(Customer.class, customerIDList.get(i).intValue()));
                    addressList.add(entityManager.getReference(Address.class, customersList.get(i).getAddressId()));
                    cityList.add(entityManager.getReference(City.class, addressList.get(i).getCity_id()));
                    countryList.add(entityManager.getReference(Country.class, cityList.get(i).getCountry_id()));
                    //storeList.add(entityManager.getReference(Customer.class, customersList.get(i).getStore_id()));
                }
            }

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

        return data;
    }

    public void deleteCustomer(int customerID, HBox hBoxInfoBannerCanNotDelete, Label labelInfoBannerCanNotDelete) {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            Query query = entityManager.createNativeQuery("DELETE FROM customer WHERE customer_id = " + customerID);
            query.executeUpdate();
            transaction.commit();
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

    public Short searchCountryID(TextField country) {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        List<Short> country_List = new ArrayList<>(1);
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            Query query = entityManager.createNativeQuery("SELECT country_id FROM country WHERE country = " + "'" + country.getText() + "'");
            country_List = query.getResultList();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        if (country_List.isEmpty()) {
            return -1;
        } else {
            return country_List.get(0);
        }
    }

    public void createCountry(TextField countryName) {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            Country country = new Country();
            country.setCountry(countryName.getText());
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            country.setLastUpdate(timestamp);
            entityManager.persist(country);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

    public Short searchCityID(TextField cityName) {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        List<Short> city_List = new ArrayList<>(1);
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            Query query = entityManager.createNativeQuery("SELECT city_id FROM city WHERE city = " + "'" + cityName.getText() + "'");
            city_List = query.getResultList();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        if (city_List.isEmpty()) {
            return -1;
        } else {
            return city_List.get(0);
        }
    }

    public void createCity(TextField cityName, int countryID) {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            City city = new City();
            city.setCity(cityName.getText());
            city.setCountry_id(countryID);
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            city.setLastUpdate(timestamp);
            entityManager.persist(city);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

    public void addAddress(TextField textFieldAddress, TextField textFieldAddress2, TextField textFieldPostalCode, TextField textFieldDistrict, TextField textFieldPhone, int cityID) {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            Address address = new Address();
            address.setAddress(textFieldAddress.getText());
            address.setAddress2(textFieldAddress2.getText());
            address.setPostal_code(textFieldPostalCode.getText());
            address.setDistrict(textFieldDistrict.getText());
            address.setCity_id(cityID);
            address.setPhone(textFieldPhone.getText());
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            address.setLast_update(timestamp);
            entityManager.persist(address);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

    public int searchAddressID() {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        List<Short> address_idList = new ArrayList<>(1);
        short addressID;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            Query query = entityManager.createNativeQuery("SELECT address_id FROM address ORDER BY last_update DESC LIMIT 1");

            address_idList = query.getResultList();
            addressID = address_idList.get(0);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return address_idList.get(0).intValue();
    }

    public void addCustomer(TextField textFieldStoreID, TextField textFieldFirstName, TextField textFieldLastName, TextField textFieldEmail, int addressID) {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            Customer customer = new Customer();
            customer.setStoreId(Integer.parseInt(textFieldStoreID.getText()));
            customer.setFirstName(textFieldFirstName.getText());
            customer.setLastName(textFieldLastName.getText());
            customer.setEmail(textFieldEmail.getText());
            customer.setAddressId(1);
            customer.setActive(1);
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            customer.setCreateDate(timestamp);
            customer.setLastUpdate((timestamp));
            customer.setAddressId(addressID);
            entityManager.persist(customer);
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

    public ObservableList<TextFieldResult> searchFromCustomer(TextField textFieldSearchField) {
        ObservableList<TextFieldResult> data = FXCollections.observableArrayList();
       data = this.searchCustomer(textFieldSearchField);
       return data;
    }

    public void deleteFromCustomer(int customerID, HBox hBoxInfoBannerCanNotDelete, Label labelInfoBannerCanNotDelete) {
        this.deleteCustomer(customerID, hBoxInfoBannerCanNotDelete, labelInfoBannerCanNotDelete);
    }

}


