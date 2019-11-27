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

@Entity @Table( name="OrderTable")
@RequestScoped @ManagedBean( name="Order")
public class Order {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int orderID;
  
  private String customerName;
  private int cartFlag;

  /**
   * Default constructor, required for entity classes
   */
  public Order() {}

  /**
   * Constructors
   */
  public Order(int orderID, String customerName, int cartFlag) {
    this.setOrderID(orderID);
    this.setCustomerName(customerName);
    this.setCartFlag(cartFlag);
  }

  public Order(String customerName, int cartFlag) {
    this.setCustomerName(customerName);
    this.setCartFlag(cartFlag);
  }

  /**
   * Getters and setters
   */
  public String getCustomerName() {
    return customerName;
  }

  public void setCustomerName(String customerName) {
    this.customerName = customerName;
  }

  public int getOrderID() {
    return orderID;
  }

  public void setOrderID(int orderID) {
    this.orderID = orderID;
  }

  public int getCartFlag() {
    return cartFlag;
  }

  public void setCartFlag(int cartFlag) {
    this.setCartFlag(cartFlag);
  }

  /**
   * Create a human readable serialization.
   */
  public String toString() {
    return "{ orderID: '" + this.orderID + "', customerName:'" + this.customerName + ", cartFlag: " + this.cartFlag + "'}";
  }

  /**
   * Retrieve all order records from the orders table.
   */
  public static List<Order> retrieveAll(EntityManager em) {
    TypedQuery<Order> query = em.createQuery("SELECT b FROM Order b", Order.class);
    List<Order> orders = query.getResultList();
    System.out.println("Order.retrieveAll: " + orders.size()
        + " orders were loaded from DB.");
    return orders;
  }

  /**
   * Retrieve a order record from the orders table.
   */
  public static Order retrieve(EntityManager em, int orderID) {
    Order order = em.find(Order.class, orderID);
    if (order != null) {
      System.out.println("Order.retrieve: loaded order " + order);
    }
    return order;
  }

  /**
   * Create an Order instance.
   * @throws Exception
   */
  public static void add(EntityManager em, UserTransaction ut,
      String customerName, int cartFlag) throws Exception {
    ut.begin();
    Order order = new Order(customerName, cartFlag);
    em.persist(order);
    ut.commit();
    System.out.println("Order.add: the order " + order + " was saved.");
  }

  /**
   * Update an Order instance.
   * @throws Exception
   */
  public static void update(EntityManager em, UserTransaction ut, int orderID,
      String customerName, int cartFlag) throws Exception {
    ut.begin();
    Order order = em.find(Order.class, orderID);
    if (customerName != null && !customerName.equals(order.customerName)) {
      order.setCustomerName(customerName);
    }
    if (cartFlag != order.cartFlag) {
      order.setCartFlag(cartFlag);
    }
    ut.commit();
    System.out.println("Order.update: the order " + order + " was updated.");
  }

  /**
   * Delete an Order instance.
   * @throws Exception
   */
  public static void destroy(EntityManager em, UserTransaction ut, int orderID)
      throws Exception {
    ut.begin();
    Order order = em.find(Order.class, orderID);
    em.remove(order);
    ut.commit();
    System.out.println( "Order.destroy: the order " + order + " was deleted.");
  }

  /**
   * Clear all entries from the <code>orders</code> table
   * @throws Exception
  public static void clearData( EntityManager em, UserTransaction ut)
      throws Exception {
    ut.begin();
    Query deleteQuery = em.createQuery("DELETE FROM Order");
    deleteQuery.executeUpdate();
    ut.commit();
  }

  /**
   * Create test data (rows) in the <code>orders</code> table
   * 
   * @param em
   *          reference to the entity manager
   * @param ut
   *          reference to the user transaction
   * @throws Exception
   
  public static void createTestData( EntityManager em, UserTransaction ut)
      throws Exception {
    Order order = null;
    // clear existing orders, so no primary key duplicate conflicts appear
    Order.clearData( em, ut);
    ut.begin();
    order = new Order( "006251587X", "Weaving the Web", 2000);
    em.persist( order);
    order = new Order( "0465026567", "G�del, Escher, Bach", 1999);
    em.persist( order);
    order = new Order( "0465030793", "I Am A Strange Loop", 2008);
    em.persist( order);
    ut.commit();
  }*/
}