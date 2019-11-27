package pl.ctrl;

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

import pl.model.Book;

@SessionScoped @ManagedBean( name="bookCtrl")
public class BookController {
  @PersistenceContext( unitName="MinimalApp")
  private EntityManager em;
  @Resource UserTransaction ut;

  /**
   * Read the list of all the books from the database.
   * 
   * @return a list with all the Book instance found in the database.
   */
  public List<Book> getBooks() {
    return Book.retrieveAll( em);
  }

  /**
   * Update the reference object by setting its property values to match the one
   * existing in the database for the specific instance, identified by the
   * primary key value.
   * 
   * @param book
   *          the reference to the Book instance to be "loaded" from database.
   */
  public void refreshObject( Book book) {
    Book foundBook = Book.retrieve( em, book.getIsbn());
    book.setTitle( foundBook.getTitle());
    book.setYear( foundBook.getYear());
  }

  /**
   * Create and persist a new Book instance.
   * 
   * @param isbn
   *          the isbn of the book to create
   * @param title
   *          the title of the book to create
   * @param year
   *          the year of the book to create
   * @return a string representing the view name to display after finishing the
   *         execution of this method.
   */
  public String add( String isbn, String title, int year) {
    try {
      Book.add( em, ut, isbn, title, year);
      // Clear the form after creating the Book record
      FacesContext facesContext = FacesContext.getCurrentInstance();
      facesContext.getExternalContext().getRequestMap().remove( "book");
    } catch ( Exception e) {
      e.printStackTrace();
    }
    return "create";
  }

  /**
   * Update a Book instance.
   * 
   * @param isbn
   *          the isbn of the book to update (the book will be identified in the
   *          database by using this value)
   * @param title
   *          the new value for the title property
   * @param year
   *          the new value for the year property
   * @return a string representing the view name to display after finishing the
   *         execution of this method.
   */
  public String update( String isbn, String title, int year) {
    try {
      Book.update( em, ut, isbn, title, year);
    } catch ( Exception e) {
      e.printStackTrace();
    }
    return "update";
  }

  /**
   * Delete a Book entry from database.
   * 
   * @param isbn
   *          the isbn of the book to delete - used to uniquely identify the
   *          book entry.
   * @return a string representing the view name to display after finishing the
   *         execution of this method.
   */
  public String destroy( String isbn) {
    try {
      Book.destroy( em, ut, isbn);
    } catch ( Exception e) {
      e.printStackTrace();
    }
    return "delete";
  }

  /**
   * Clear/delete all entries from the <code>books</code> table
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
   */
  public String clearData() {
    try {
      Book.clearData( em, ut);
    } catch ( Exception e) {
      e.printStackTrace();
    }
    return "index";
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
   */
  public String createTestData() {
    try {
      Book.createTestData( em, ut);
    } catch ( Exception e) {
      e.printStackTrace();
    }
    return "index";
  }
}
