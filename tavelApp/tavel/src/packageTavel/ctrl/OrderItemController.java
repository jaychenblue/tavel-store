package packageTavel.ctrl;

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

@SessionScoped @ManagedBean(name="OrderItemCtrl")
public class OrderItemController {
  @PersistenceContext(unitName="tavelApp")
  private EntityManager em;
  @Resource UserTransaction ut;

  /**
   * Read the list of all the books from the database.
   * 
   * @return a list with all the OrderItem instance found in the database.
   */
  public List<OrderItem> getOrderItems() {
    return OrderItem.retrieveAll(em);
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
  }

  /**
   * Create and persist a new OrderItem instance.
   */
  public String add(int itemID, int orderId, int q, String size, String firstName, String lastName) {
    try {
      OrderItem.add(em, ut, itemID, orderId, q, size, firstName, lastName);
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
  public String update(int id, int itemID, int orderId, int q, String size, String firstName, String lastName) {
    try {
      OrderItem.update(em, ut, id, itemID, orderId, q, size, firstName, lastName);
    } catch ( Exception e) {
      e.printStackTrace();
    }
    return "update";
  }

  /**
   * Delete an OrderItem entry from database.
   */
  public String destroy(int id) {
    try {
      OrderItem.destroy( em, ut, id);
    } catch ( Exception e) {
      e.printStackTrace();
    }
    return "delete";
  }
}