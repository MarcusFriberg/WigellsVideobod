package org.edusystems.entities;
// Imports
import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "inventory")
public class Inventory {
    @Id
    @Column(name = "inventory_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int inventoryId;

    @Column(name = "film_id")
    private int filmId;

    @Column(name = "store_id")
    private int storeId;

    @Column(name = "last_update")
    private Date lastUpdate;

    public Inventory(int filmId, int storeId) {
        this.filmId = filmId;
        this.storeId = storeId;
    }

    public Inventory() {
    }

    //Setters
    public void setFilmId(int filmId) {
        this.filmId = filmId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    //Getters
    public int getInventoryId() {
        return inventoryId;
    }

    public int getFilmId() {
        return filmId;
    }

    public int getStoreId() {
        return storeId;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }
}
