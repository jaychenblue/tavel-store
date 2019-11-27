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

import packageTavel.model.Order;

@SessionScoped @ManagedBean(name="orderCtrl")
public class OrderController {
  @PersistenceContext(unitName="tavelApp")
  private EntityManager em;
  @Resource UserTransaction ut;

  /**
   * Read the list of all the books from the database.
   * 
   * @return a list with all the Order instance found in the database.
   */
  public List<Order> getOrders() {
    return Order.retrieveAll(em);
  }

  /**
   * Update the reference object by setting its property values to match the one
   * existing in the database for the specific instance, identified by the
   * primary key value.
   * 
   * @param order
   *          the reference to the Order instance to be "loaded" from database.
   */
  public void refreshObject(Order order) {
    Order foundOrder = Order.retrieve(em, order.getOrderID());
    order.setCustomerName(foundOrder.getCustomerName());
    order.setCartFlag(foundOrder.getCartFlag());
  }

  /**
   * Create and persist a new Order instance.
   */
  public String add(String customerName, int cartFlag) {
    try {
      Order.add(em, ut, customerName, cartFlag);
      // Clear the form after creating the Order record
      FacesContext facesContext = FacesContext.getCurrentInstance();
      facesContext.getExternalContext().getRequestMap().remove( "order");
    } catch ( Exception e) {
      e.printStackTrace();
    }
    return "create";
  }

  /**
   * Update an Order instance.
   */
  public String update(int id, String customerName, int cartFlag) {
    try {
      Order.update(em, ut, id, customerName, cartFlag);
    } catch ( Exception e) {
      e.printStackTrace();
    }
    return "update";
  }

  /**
   * Delete an Order entry from database.
   */
  public String destroy(int id) {
    try {
      Order.destroy( em, ut, id);
    } catch ( Exception e) {
      e.printStackTrace();
    }
    return "delete";
  }

  /**
   * Create test data (rows) in the <code>books</code> table
   * 
   * @return a string representing the view name to display after finishing the
   *         execution of this method.
   * 
   * @throws NotSupportedException
   * @throws SystemException
   * @throws IllegalStateException
   * @throws SecurityException
   * @throws HeuristicMixedException
   * @throws HeuristicRollbackException
   * @throws RollbackException
   *
  public String createTestData() {
    try {
      Order.createTestData( em, ut);
    } catch ( Exception e) {
      e.printStackTrace();
    }
    return "index";
  }*/
}
