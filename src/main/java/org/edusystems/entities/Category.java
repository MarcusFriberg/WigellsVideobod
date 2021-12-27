package org.edusystems.entities;

// Imports
import javax.persistence.*;
import java.sql.Timestamp;

/*
 * Class Category
 * Class that can be used to create category-objects from, with entites from Hibernate.
 * @author: Matilda Wintence
 * @author: matilda.wintence@edu.edugrade.se
 * @version: 1.0.
 */
@Entity
@Table(name = "category")
public class Category {
    @Id
    @Column(name = "category_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int categoryId;

    @Column(name = "name")
    private String name;

    @Column(name = "last_update")
    private Timestamp lastUpdate;

    public Category() {
    }

    //Constructor
    public Category(String name) {
        this.name = name;
    }

    //Setters
    public void setName(String name) {
        this.name = name;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public String getName() {
        return name;
    }

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }
}




