package packageTavel.model;

import java.text.DecimalFormat;
import java.util.List;
import java.math.BigDecimal;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.Query;
import javax.persistence.Table;
import javax.persistence.TypedQuery;
import javax.transaction.UserTransaction;

@Entity @Table( name="Item")
@RequestScoped @ManagedBean( name="Item")
public class Item {
  @Id private int itemID;
  private String title;
  private BigDecimal price;

  /**
   * Default constructor, required for entity classes
   */
  public Item() {}

  /**
   * Constructor
   */
  public Item(int itemID, String title, BigDecimal price) {
    this.setItemID(itemID);
    this.setTitle(title);
    this.setPrice(price);
  }

  /**
   * Getters and setters
   */
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public int getItemID() {
    return itemID;
  }

  public void setItemID(int itemID) {
    this.itemID = itemID;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.setPrice(price);
  }

  /**
   * Create a human readable serialization.
   */
  public String toString() {
    return "{itemID: '" + this.itemID + "', title:'" + this.title + ", price: " + this.price + "'}";
  }

  /**
   * Retrieve all item records from the items table.
   * 
   * @param em
   *          reference to the entity manager
   * @return the list of all Item records
   */
  public static List<Item> retrieveAll(EntityManager em) {
    TypedQuery<Item> query = em.createQuery("SELECT b FROM Item b", Item.class);
    List<Item> items = query.getResultList();
    System.out.println("Item.retrieveAll: " + items.size()
        + " items were loaded from DB.");
    return items;
  }

  /**
   * Retrieve a item record from the items table.
   * 
   * @param em
   *          reference to the entity manager
   * @param itemID
   *          the item's ID
   * @return the item with the given ID
   */
  public static Item retrieve(EntityManager em, int itemID) {
    Item item = em.find(Item.class, itemID);
    if (item != null) {
      System.out.println("Item.retrieve: loaded item " + item);
    }
    return item;
  }
}