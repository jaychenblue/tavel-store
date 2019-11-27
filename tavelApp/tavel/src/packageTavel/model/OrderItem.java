package packageTavel.model;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.Query;
import javax.persistence.Table;
import javax.persistence.TypedQuery;
import javax.transaction.UserTransaction;

@Entity @Table( name="OrderItem")
@RequestScoped @ManagedBean( name="OrderItem")
public class OrderItem {
  @Id 
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private int itemID;
  private int orderID;
  private int quantity;
  private String size;
  private String firstName;
  private String lastName;

  /**
   * Default constructor, required for entity classes
   */
  public OrderItem() {}

  /**
   * Constructors
   */
  public OrderItem(int id, int itemId, int orderId, int q, String size, String firstName, String lastName) {
    this.setId(id);
    this.setItemId(itemId);
    this.setOrderId(orderId);
    this.setQuantity(q);
    this.setSize(size);
    this.setFirstName(firstName);
    this.setLastName(lastName);
  }

  public OrderItem(int itemId, int orderId, int q, String size, String firstName, String lastName) {
    this.setItemId(itemId);
    this.setOrderId(orderId);
    this.setQuantity(q);
    this.setSize(size);
    this.setFirstName(firstName);
    this.setLastName(lastName);
  }

  /**
   * Getters and setters
   */
  public int getID() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getOrderId() {
    return orderID;
  }

  public void setOrderId(int orderId) {
    this.orderID = orderId;
  }

  public int getItemId() {
    return itemID;
  }

  public void setItemId(int itemId) {
    this.itemID = itemId;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int q) {
    this.quantity = q;
  }

  public String getSize() {
    return size;
  }

  public void setSize(String size) {
    this.size = size;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  /**
   * Create a human readable serialization.
   */
  public String toString() {
    return "{ OrderItemId: '" + this.id + "', OrderId:'" + this.orderID + ", Quantity: " + this.quantity + "'}";
  }

  /**
   * Retrieve all orderItem records from the orderItems table.
   */
  public static List<OrderItem> retrieveAll(EntityManager em) {
    TypedQuery<OrderItem> query = em.createQuery("SELECT b FROM OrderItem b", OrderItem.class);
    List<OrderItem> orderItems = query.getResultList();
    System.out.println("OrderItem.retrieveAll: " + orderItems.size()
        + " orderItems were loaded from DB.");
    return orderItems;
  }

  /**
   * Retrieve a orderItem record from the orderItems table.
   */
  public static OrderItem retrieve(EntityManager em, int orderItemID) {
    OrderItem orderItem = em.find(OrderItem.class, orderItemID);
    if (orderItem != null) {
      System.out.println("OrderItem.retrieve: loaded orderItem " + orderItem);
    }
    return orderItem;
  }

  /**
   * Create an OrderItem instance.
   * @throws Exception
   */
  public static void add(EntityManager em, UserTransaction ut, int orderID, int itemID, int quantity,
      String size, String firstName, String lastName) throws Exception {
    ut.begin();
    OrderItem orderItem = new OrderItem(orderID, itemID, quantity, size, firstName, lastName);
    em.persist(orderItem);
    ut.commit();
    System.out.println("OrderItem.add: the orderItem " + orderItem + " was saved.");
  }

  /**
   * Update a OrderItem instance
   * @throws Exception
   */
  public static void update(EntityManager em, UserTransaction ut, int id, int orderID, int itemID, int quantity,
  String size, String firstName, String lastName) throws Exception {
    ut.begin();
    OrderItem orderItem = em.find(OrderItem.class, id);

    if (orderID != orderItem.getOrderId()) {
      orderItem.setOrderId(orderID);
    }
    if (itemID != orderItem.getItemId()) {
      orderItem.setItemId(itemID);
    }
    if (quantity != orderItem.getQuantity()) {
      orderItem.setQuantity(quantity);
    }
    if (size != null && !size.equals(orderItem.size)) {
      orderItem.setSize(size);
    }
    if (firstName != null && !firstName.equals(orderItem.firstName)) {
      orderItem.setFirstName(firstName);
    }
    if (lastName != null && !lastName.equals(orderItem.lastName)) {
      orderItem.setLastName(lastName);
    }

    ut.commit();
    System.out.println("OrderItem.update: the orderItem " + orderItem + " was updated.");
  }

  /**
   * Delete a OrderItem instance
   * @throws Exception
   */
  public static void destroy(EntityManager em, UserTransaction ut, int orderItemID)
      throws Exception {
    ut.begin();
    OrderItem orderItem = em.find(OrderItem.class, orderItemID);
    em.remove(orderItem);
    ut.commit();
    System.out.println( "OrderItem.destroy: the orderItem " + orderItem + " was deleted.");
  }
}