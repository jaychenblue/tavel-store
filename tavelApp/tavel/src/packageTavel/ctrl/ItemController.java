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

import packageTavel.model.Item;

@SessionScoped @ManagedBean(name="itemCtrl")
public class ItemController {
  @PersistenceContext(unitName="tavelApp")
  private EntityManager em;
  @Resource UserTransaction ut;

  /**
   * Read the list of all the books from the database.
   * 
   * @return a list with all the Item instance found in the database.
   */
  public List<Item> getItems() {
    return Item.retrieveAll(em);
  }

  /**
   * Get a single Item instance.
   */
  public Item getItem(int id) {
    return Item.retrieve(em, id);
  }

  /**
   * Update the reference object by setting its property values to match the one
   * existing in the database for the specific instance, identified by the
   * primary key value.
   * 
   * @param item
   *          the reference to the Item instance to be "loaded" from database.
   */
  public void refreshObject(Item item) {
    Item foundItem = Item.retrieve(em, item.getItemID());
    item.setTitle(foundItem.getTitle());
    item.setPrice(foundItem.getPrice());
  }
}
