package org.edusystems.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TextField;
import org.edusystems.entities.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.edusystems.Main.ENTITY_MANAGER_FACTORY;
/*
 * Class createCustomerViewController.
 * This class holds all the functions/methods for the createCustomerView.
 * @author: Linda Djurström
 * @author: linda.djurstrom@edu.edugrade.se
 * @version: 1.0.
 */
public class CreateCustomerViewController {
    public static EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("hibernate");
    //Constructor
    CustomerViewController customerViewController;
    public CreateCustomerViewController(CustomerViewController customerViewController) {
        this.customerViewController = customerViewController;
    }

    public Customer getCustomer(int customerID) {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        Customer customer = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
                    customer = entityManager.getReference(Customer.class, Short.parseShort(String.valueOf(customerID)));
            } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return customer;
    }


    public static Address getAddress(Short addressID) {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        Address address = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            address = entityManager.getReference(Address.class, addressID);
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return address;
    }

    public static City getCity(Short cityID) {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        City city = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            city = entityManager.getReference(City.class, cityID);
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return city;
    }

    public static Country getCountry(Short countryID) {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        Country country = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            country = entityManager.getReference(Country.class, countryID);
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return country;
    }

    /*
     * Method searchCountryID
     * Method that searches in the database and fetches the objects that relates to the user's entry in the countryTextfield.
     * Params: TextField countryName.
     * Returns: countryID if found, else -1.
     * @author: Linda Djurström
     * @author: linda.djurstrom@edu.edugrade.se
     * @version: 1.0
     */
    public Short searchCountryID(TextField countryName) {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        //Array-list used later.
        List<Short> country_List = new ArrayList<>(1);
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            //Query to the database, searches for countryID where country matches the value in the textField countryName (the user-entered information).
            Query query = entityManager.createNativeQuery("SELECT country_id FROM country WHERE country = " + "'" + countryName.getText() + "'");
            //Stores the countryID in an arraylist.
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
        //If no country is a match, -1 is returned.
        if (country_List.isEmpty()) {
            return -1;
        } else {
            //Else, the first objects countryID is returned.
            return country_List.get(0);
        }
    }

    /*
     * Method createCountry.
     * Method that creates a country
     * Params: TextField countryName.
     * @author: Linda Djurström
     * @author: linda.djurstrom@edu.edugrade.se
     * @version: 1.0
     */
    public void createCountry(TextField countryName) {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            //Creates a new countryObject.
            Country country = new Country();
            //Sets the name of the country from the inParameter.
            country.setCountry(countryName.getText());
            //Sets the lastUpdateTime.
            country.setLastUpdate(new Timestamp(System.currentTimeMillis()));
            //The object is inserted to the database.
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

    /*
     * Method searchCityId
     * Method that searches in the database and fetches the objects that relates to the user's entry in the cityTextField, if it
     * exists. Taking into account if the city exists in the country the users has specified (if not, it's not a hit). Multiple
     * countries can have cities with the same name.
     * Params: TextField cityName, countryID
     * Returns: cityID if found, else -1.
     * @author: Linda Djurström
     * @author: linda.djurstrom@edu.edugrade.se
     * @version: 1.0
     */
    public Short searchCityID(TextField cityName, Short countryID) {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        //Array-list used later.
        List<Short> city_List = new ArrayList<>(1);
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            /*Query to the database, searches for cityID where city matches the value in the textField
             cityName (the user-entered information) and the countryID matches the country the user has chosen.
             */
            Query query = entityManager.createNativeQuery("SELECT city_id FROM city WHERE city = " + "'" + cityName.getText() + "'" + " AND country_id = " + "'" + countryID.toString() + "'");
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
            //If no city is a match, -1 is returned.
            return -1;
        } else {
            //Else, the first objects cityID is returned.
            return city_List.get(0);
        }
    }

    /*
     * Method createCity.
     * Method that creates a city.
     * Params: TextField cityName.
     * @author: Linda Djurström
     * @author: linda.djurstrom@edu.edugrade.se
     * @version: 1.0
     */
    public void createCity(TextField cityName, Short countryID) {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            //Creates a new countryObject.
            City city = new City();
            //Sets the name of the country from the inParameter.
            city.setCity(cityName.getText());
            city.setCountry_id(countryID);
            //Sets the lastUpdateTime.
            city.setLastUpdate(new Timestamp(System.currentTimeMillis()));
            //The object is inserted to the database.
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

    /*
     * Method addAddress
     * Method that creates an address.
     * Params: TextFieldAddress, TextFieldAddress2, TextFieldPostalCode, TextFieldDistrict, TextFieldPhone, CityID.
     * @author: Linda Djurström
     * @author: linda.djurstrom@edu.edugrade.se
     * @version: 1.0
     */
    public void addAddress(TextField textFieldAddress, TextField textFieldAddress2, TextField textFieldPostalCode, TextField textFieldDistrict, TextField textFieldPhone, Short cityID) {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            //Adds a new address-object.
            Address address = new Address();
            //Set values for the object with the parameters the user entered.
            address.setAddress(textFieldAddress.getText());
            address.setAddress2(textFieldAddress2.getText());
            address.setPostal_code(textFieldPostalCode.getText());
            address.setDistrict(textFieldDistrict.getText());
            address.setPhone(textFieldPhone.getText());
            //Set value to the CityID from the value sent in to the method.
            address.setCity_id(cityID);
            address.setLast_update(new Timestamp(System.currentTimeMillis()));
            //Adds the address-object to the database.
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

    /*
     * Method searchAddressID.
     * Method that returns addressID from the last_updated address in database.
     * Returns: addressID.
     * @author: Linda Djurström
     * @author: linda.djurstrom@edu.edugrade.se
     * @version: 1.0
     */
    public int searchAddressID() {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        //Arraylist used later in method.
        List<Short> address_idList = new ArrayList<>(1);
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            //Query that gets the addressID from the last updated address in database.
            Query query = entityManager.createNativeQuery("SELECT address_id FROM address ORDER BY last_update DESC LIMIT 1");
            //Stores the queryResult in the addressList.
            address_idList = query.getResultList();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        //Returns the first object in the list (the list only contains one object), returns the intValue.
        return address_idList.get(0).intValue();
    }

    /*
     * Method addCustomer
     * Method that creates a customer.
     * Params: textFieldStoreID, textFieldFirstName, textFieldLastName, textFieldEmail, addressID.
     * @author: Linda Djurström
     * @author: linda.djurstrom@edu.edugrade.se
     * @version: 1.0
     */
    public void addCustomer(TextField textFieldStoreID, TextField textFieldFirstName, TextField textFieldLastName, TextField textFieldEmail, int addressID) {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            //Creates a new customer-object.
            Customer customer = new Customer();
            //Sets the value for the customer-object from the user-entered data.
            customer.setStoreId(Short.parseShort(textFieldStoreID.getText()));
            customer.setFirstName(textFieldFirstName.getText());
            customer.setLastName(textFieldLastName.getText());
            customer.setEmail(textFieldEmail.getText());
            customer.setAddressId(Short.parseShort(String.valueOf(addressID)));
            //Customer is set as active.
            customer.setActive(1);
            //Set createDate and lastUpdate.
            customer.setCreateDate(new Timestamp(System.currentTimeMillis()));
            customer.setLastUpdate((new Timestamp(System.currentTimeMillis())));
            //Sends the customerobject to the database.
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
}
