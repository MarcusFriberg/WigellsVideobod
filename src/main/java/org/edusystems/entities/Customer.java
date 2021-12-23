package org.edusystems.entities;
// Imports
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import org.edusystems.controller.CustomerViewController;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customer")
public class Customer {

    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("hibernate");

    @Id
    @Column(name = "customer_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customerId;

    @Column(name = "store_id")
    private int storeId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "address_id")
    private int addressId;

    @Column(name = "active")
    private int active;

    @Column(name = "create_date")
    private Timestamp createDate;

    @Column(name = "last_update")
    private Timestamp lastUpdate;

    public Customer() {
    }

    public Customer(int storeId, String firstName, String lastName, String email, int addressId, int active) {
        this.storeId = storeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.addressId = addressId;
        this.active = active;
    }

    //Setters
    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public void setActive(int active) {
        this.active = active;
    }

    //Getters
    public int getCustomerId() {
        return customerId;
    }

    public int getStoreId() {
        return storeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public int getAddressId() {
        return addressId;
    }

    public int getActive() {
        return active;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }


    public ObservableList<TextFieldResult> searchCustomer(TextField textFieldSearchField) {

        final ObservableList<TextFieldResult> data = FXCollections.observableArrayList();
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        List<Short> customerIDList = new ArrayList<>(1);

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

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
}






