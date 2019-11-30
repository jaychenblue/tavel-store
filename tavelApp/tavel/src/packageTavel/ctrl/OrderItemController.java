package packageTavel.ctrl;

import java.text.DecimalFormat;
import java.util.List;

import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import packageTavel.model.OrderItem;

@SessionScoped @ManagedBean(name="orderItemCtrl")
public class OrderItemController {
  @PersistenceContext(unitName="tavelApp")
  private EntityManager em;
  @Resource UserTransaction ut;

  /**
   * Read the list of all the orderItems from the database.
   * 
   * @return a list with all the OrderItem instance found in the database.
   */
  public List<OrderItem> getOrderItems() {
    return OrderItem.retrieveAll(em);
  }

  /**
   * Read the list of all the items in the cart
   * 
   * @return a list with all the OrderItem instance found in the database.
   */
  public List<OrderItem> getOrderItemsInCart(int idToLookFor) {
    return OrderItem.getCart(em, idToLookFor);
  }

  /**
   * Update the reference object by setting its property values to match the one
   * existing in the database for the specific instance, identified by the
   * primary key value.
   * 
   * @param orderItem
   *          the reference to the OrderItem instance to be "loaded" from database.
   */
  public void refreshObject(OrderItem orderItem) {
    OrderItem foundOrderItem = OrderItem.retrieve(em, orderItem.getID());
    orderItem.setItemId(foundOrderItem.getItemId());
    orderItem.setOrderId(foundOrderItem.getOrderId());
    orderItem.setQuantity(foundOrderItem.getQuantity());
    orderItem.setSize(foundOrderItem.getSize());
    orderItem.setFirstName(foundOrderItem.getFirstName());
    orderItem.setLastName(foundOrderItem.getLastName());
    orderItem.setPrice(foundOrderItem.getPrice());
  }

  /**
   * Create and persist a new OrderItem instance.
   */
  public String add(int itemID, int orderId, int q, String size, String firstName, String lastName, double p) {
    try {
      OrderItem.add(em, ut, itemID, orderId, q, size, firstName, lastName, p);
      // Clear the form after creating the OrderItem record
      FacesContext facesContext = FacesContext.getCurrentInstance();
      facesContext.getExternalContext().getRequestMap().remove( "orderItem");
    } catch ( Exception e) {
      e.printStackTrace();
    }
    return "create";
  }

  /**
   * Update an OrderItem instance.
   */
  public void update(int id, int itemID, int orderId, int q, String size, String firstName, String lastName, double p) {
    try {
      OrderItem.update(em, ut, id, itemID, orderId, q, size, firstName, lastName, p);
    } catch ( Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Delete an OrderItem entry from database.
   */
  public void destroy(int id) {
    try {
      System.out.println("Destroying orderItem with id: " + id);
      OrderItem.destroy( em, ut, id);
    } catch ( Exception e) {
      e.printStackTrace();
    }
  }

  public double getTotal(int id) {
    DecimalFormat df = new DecimalFormat("#.##");
    return Double.parseDouble(df.format(OrderItem.getTotal(em, id)));
  }

  public Long countItems(int id) {
    return OrderItem.countItems(em, id);
  }
}
