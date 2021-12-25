package org.edusystems.controller;
// Imports
import javafx.scene.control.Alert;
import org.edusystems.Main;
import org.edusystems.entities.Staff;
import org.edusystems.view.LoginView;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

public class LoginViewController {
    //Variables
    private LoginView loginView;
    private ViewController viewController;
    private EntityManagerFactory ENTITY_MANAGER_FACTORY;
    private Staff userLoggedIn;

    // Constructor
    public LoginViewController(LoginView loginView, ViewController viewController) {
        this.loginView = loginView;
        this.viewController = viewController;
        this.ENTITY_MANAGER_FACTORY = Main.ENTITY_MANAGER_FACTORY;
    }

    public String userLogin(String user, String password) {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            userLoggedIn = (Staff) entityManager.createQuery("from Staff as staff where staff.username = ?1")
                    .setParameter(1, user)
                    .getSingleResult();

            if(userLoggedIn.getPassword() == null || userLoggedIn.getPassword().equals(password)) {
                viewController.setUserLoggedIn(userLoggedIn);
                viewController.showView("homeView");
            } else {
                loginView.alertLabel.setText("Wrong username or password");
                loginView.alertLabel.setVisible(true);
            }


        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }


        return "Hej";
    }


}
