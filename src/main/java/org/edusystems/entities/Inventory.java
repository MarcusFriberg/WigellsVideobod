package org.edusystems.entities;
// Imports
import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;

@Entity
@Table(name = "inventory")
public class Inventory {
    @Id
    @Column(name = "inventory_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int inventoryId;

    @ManyToOne
    @JoinColumn(name = "film_id")
    private Film inventoryFilm;

    @ManyToOne
    @JoinColumn(name ="store_id")
    private Store inventoryStore;

    @Column(name = "last_update")
    private Timestamp lastUpdate;

    public Inventory(Film inventoryFilm, Store inventoryStore) {
        this.inventoryFilm = inventoryFilm;
        this.inventoryStore = inventoryStore;
    }

    public Inventory() {
    }

    //Setters
    public void setInventoryFilm(Film inventoryFilm) {
        this.inventoryFilm = inventoryFilm;
    }

    public void setInventoryStore(Store inventoryStore) {
        this.inventoryStore = inventoryStore;
    }

    //Getters
    public int getInventoryId() {
        return inventoryId;
    }
    public Timestamp getLastUpdate() {
        return lastUpdate;
    }
    public Film getInventoryFilm() {
        return inventoryFilm;
    }
    public Store getInventoryStore() {
        return inventoryStore;
    }
    public String getInventoryFilmTitle() {
        return inventoryFilm.getTitle();
    }
    public String getInventoryFilmReleaseYear() {
        Date date = inventoryFilm.getReleaseYear();
        String dateString = date.toString();
        String dateToReturn = dateString.substring(0,4);
        return dateToReturn;
    }
}
