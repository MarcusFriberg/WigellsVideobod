package org.edusystems.view;

public class Slask {

/*

   Button bUpdate1 = new Button("Update");





        bUpdate2.setOnAction(event -> {
            hBoxInfoBannerCanNotDelete.setVisible(false);
            int customerCopy = tableViewResultArea.getSelectionModel().getSelectedIndex();
            if (customerCopy < 0) {
                labelInfoBannerCanNotDelete.setText("Du måste välja en kund att uppdatera först.");
                hBoxInfoBannerCanNotDelete.setVisible(true);
            }
            int customerID = data.get(customerCopy).getCustomerID();
            updateCustomer(customerID, textFieldStoreID, textFieldFirstName, textFieldLastName, textFieldEmail,
                    textFieldAddress, textFieldAddress2, textFieldPostalCode, textFieldDistrict, textFieldCity, textFieldCountry,
                    textFieldPhone, labelAddCustomer, bCreate, bUpdate1);
        });

        bCreate.setOnAction(event -> {
            hBoxInfoBannerCanNotDelete.setVisible(false);

    private void updateCustomer(int customerID, TextField textFieldStoreID, TextField textFieldFirstName, TextField textFieldLastName,
                                TextField textFieldEmail, TextField textFieldAddress, TextField textFieldAddress2, TextField textFieldPostalCode,
                                TextField textFieldDistrict, TextField textFieldCity, TextField textFieldCountry, TextField textFieldPhone, Label labelAddCustomer,
                                Button bCreate, Button bUpdate1) {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            Customer customer = entityManager.getReference(Customer.class, customerID);
            Address address = (entityManager.getReference(Address.class, customer.getAddress_id()));
            City city = entityManager.getReference(City.class, address.getCity_id());
            Country country = entityManager.getReference(Country.class, city.getCountry_id());
            transaction.commit();

            labelAddCustomer.setText("Uppdatera kund");
            bCreate.setText("Skapa kopia");
            bUpdate1.setVisible(true);
            textFieldStoreID.setText(String.valueOf(customer.getStore_id()));
            textFieldStoreID.setEditable(true);
            textFieldFirstName.setText(customer.getFirst_name());
            textFieldLastName.setText(customer.getLast_name());
            textFieldEmail.setText(customer.getEmail());
            textFieldAddress.setText(address.getAddress());
            textFieldAddress2.setText(address.getAddress2());
            textFieldPostalCode.setText(address.getPostal_code());
            textFieldDistrict.setText(address.getDistrict());
            textFieldCity.setText(city.getCity());
            textFieldCountry.setText(country.getCountry());
            textFieldPhone.setText(address.getPhone());

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }










    }*/
}
