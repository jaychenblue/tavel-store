package pl.model;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.Query;
import javax.persistence.Table;
import javax.persistence.TypedQuery;
import javax.transaction.UserTransaction;

@Entity @Table( name="books")
@RequestScoped @ManagedBean( name="book")
public class Book {
  @Id private String isbn;
  private String title;
  private int year;

  /**
   * Default constructor, required for entity classes
   */
  public Book() {}

  /**
   * Constructor
   */
  public Book( String isbn, String title, int year) {
    this.setIsbn( isbn);
    this.setTitle( title);
    this.setYear( year);
  }

  /**
   * Getters and setters
   */
  public String getTitle() {
    return title;
  }

  public void setTitle( String title) {
    this.title = title;
  }

  public String getIsbn() {
    return isbn;
  }

  public void setIsbn( String isbn) {
    this.isbn = isbn;
  }

  public int getYear() {
    return year;
  }

  public void setYear( int year) {
    this.year = year;
  }

  /**
   * Create a human readable serialization.
   */
  public String toString() {
    return "{ isbn: '" + this.isbn + "', title:'" + this.title + "', year: '"
        + this.year + "'}";
  }

  /**
   * Retrieve all book records from the books table.
   * 
   * @param em
   *          reference to the entity manager
   * @return the list of all Book records
   */
  public static List<Book> retrieveAll( EntityManager em) {
    TypedQuery<Book> query = em.createQuery( "SELECT b FROM Book b", Book.class);
    List<Book> books = query.getResultList();
    System.out.println( "Book.retrieveAll: " + books.size()
        + " books were loaded from DB.");
    return books;
  }

  /**
   * Retrieve a book record from the books table.
   * 
   * @param em
   *          reference to the entity manager
   * @param isbn
   *          the book's ISBN
   * @return the book with the given ISBN
   */
  public static Book retrieve( EntityManager em, String isbn) {
    Book book = em.find( Book.class, isbn);
    if ( book != null) {
      System.out.println( "Book.retrieve: loaded book " + book);
    }
    return book;
  }

  /**
   * Create a Book instance.
   * 
   * @param em
   *          reference to the entity manager
   * @param ut
   *          reference to the user transaction
   * @param isbn
   *          the ISBN of the book to create
   * @param title
   *          the title of the book to create
   * @param year
   *          the year of the book to create
   * @throws Exception
   */
  public static void add( EntityManager em, UserTransaction ut, String isbn,
      String title, int year) throws Exception {
    ut.begin();
    Book book = new Book( isbn, title, year);
    em.persist( book);
    ut.commit();
    System.out.println( "Book.add: the book " + book + " was saved.");
  }

  /**
   * Update a Book instance
   * 
   * @param em
   *          reference to the entity manager
   * @param ut
   *          reference to the user transaction
   * @param isbn
   *          the ISBN of the book to update
   * @param title
   *          the title of the book to update
   * @param year
   *          the year of the book to update
   * @throws Exception
   */
  public static void update( EntityManager em, UserTransaction ut, String isbn,
      String title, int year) throws Exception {
    ut.begin();
    Book book = em.find( Book.class, isbn);
    if ( title != null && !title.equals( book.title)) {
      book.setTitle( title);
    }
    if ( year != book.year) {
      book.setYear( year);
    }
    ut.commit();
    System.out.println( "Book.update: the book " + book + " was updated.");
  }

  /**
   * Delete a Book instance
   * 
   * @param em
   *          reference to the entity manager
   * @param ut
   *          reference to the user transaction
   * @param isbn
   *          the ISBN of the book to delete
   * @throws Exception
   */
  public static void destroy( EntityManager em, UserTransaction ut, String isbn)
      throws Exception {
    ut.begin();
    Book book = em.find( Book.class, isbn);
    em.remove( book);
    ut.commit();
    System.out.println( "Book.destroy: the book " + book + " was deleted.");
  }

  /**
   * Clear all entries from the <code>books</code> table
   * 
   * @param em
   *          reference to the entity manager
   * @param ut
   *          reference to the user transaction
   * @throws Exception
   */
  public static void clearData( EntityManager em, UserTransaction ut)
      throws Exception {
    ut.begin();
    Query deleteQuery = em.createQuery( "DELETE FROM Book");
    deleteQuery.executeUpdate();
    ut.commit();
  }

  /**
   * Create test data (rows) in the <code>books</code> table
   * 
   * @param em
   *          reference to the entity manager
   * @param ut
   *          reference to the user transaction
   * @throws Exception
   */
  public static void createTestData( EntityManager em, UserTransaction ut)
      throws Exception {
    Book book = null;
    // clear existing books, so no primary key duplicate conflicts appear
    Book.clearData( em, ut);
    ut.begin();
    book = new Book( "006251587X", "Weaving the Web", 2000);
    em.persist( book);
    book = new Book( "0465026567", "Gödel, Escher, Bach", 1999);
    em.persist( book);
    book = new Book( "0465030793", "I Am A Strange Loop", 2008);
    em.persist( book);
    ut.commit();
  }
}