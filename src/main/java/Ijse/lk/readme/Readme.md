......................................Assignment HQL..........................................

1. Query<Book> query = session.createQuery("FROM Book WHERE publication_year > :year", Book.class);
   query.setParameter("year", 2010);

        List<Book> books = query.getResultList();
        for (Book book : books) {
            System.out.println(book);
        }

2. Query<Book> query = session.createQuery("FROM Book WHERE autor.name = :authorName", Book.class);
   query.setParameter("authorName", "shalom"); // Replace "YourAuthorName" with the actual author's name

        List<Book> books = query.getResultList();

        // Update the price of books by increasing it by 10%
        for (Book book : books) {
            double currentPrice = book.getPrice();
            double newPrice = currentPrice * 1.1; // Increase the price by 10%
            book.setPrice(newPrice);
            session.update(book);
            System.out.println();
        }
3. /*Implement a method to delete an author and cascade the deletion to all associated books
   using appropriate cascade options.*/

// Create a query to delete books associated with a specific author

          Query deleteBooksQuery = session.createQuery("delete from Book b where b.autor.id = :id");
          deleteBooksQuery.setParameter("id", "A001"); // Set the author ID here
          int deletedBooksCount = deleteBooksQuery.executeUpdate();
          System.out.println("Number of books deleted: " + deletedBooksCount);
  
  
          Query deleteAuthorQuery = session.createQuery("delete from Autor where id = :id");
          deleteAuthorQuery.setParameter("id", "A006"); // Set the author ID here
          int deletedAuthorCount = deleteAuthorQuery.executeUpdate();
          System.out.println("Number of authors deleted: " + deletedAuthorCount);

4. // HQL query to find the average price of all books
   Query<Double> query = session.createQuery("SELECT AVG(b.price) FROM Book b", Double.class);

          Double averagePrice = query.uniqueResult();
          System.out.println("Average Price of Books: " + averagePrice);

5.  // HQL query to retrieve all authors along with the count of books they have written
    Query<Object[]> query = session.createQuery("SELECT a, COUNT(b) FROM Autor a JOIN a.bookList b GROUP BY a.id", Object[].class);

          List<Object[]> result = query.getResultList();

          for (Object[] row : result) {
              Autor author = (Autor) row[0];
              Long bookCount = (Long) row[1];

              System.out.println("Author: " + author.getName() + ", Book Count: " + bookCount);
          }
6.  String countryName = "YourCountryName"; // Replace with the actual country name

          // HQL query with named parameter to retrieve books written by authors from a specific country
          Query<Book> query = session.createQuery("FROM Book WHERE autor.country = :country", Book.class);
          query.setParameter("country", countryName);

          List<Book> books = query.getResultList();

          for (Book book : books) {
              System.out.println("Book Title: " + book.getTitle() + ", Author: " + book.getAutor().getName());
          }
7.    @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      private String id;
      //both class must be defined as
      class book{
      @ManyToOne
      @JoinColumn(name = "autor_id")
      private Autor autor;
      }
      class autor{
      @OneToMany(mappedBy = "autor",cascade = CascadeType.ALL,orphanRemoval = true)
      private List<Book> bookList;
      }


8.        Query<Autor> query = session.createQuery(
                  "SELECT a FROM Autor a JOIN a.bookList b GROUP BY a.id " +
                          "HAVING COUNT(b) > (SELECT AVG(bookCount) FROM (SELECT COUNT(b2) as bookCount FROM Autor a2 JOIN a2.bookList b2 GROUP BY a2.id) AS subquery)",
                  Autor.class
          );

          List<Autor> authors = query.getResultList();

          for (Autor author : authors) {
              System.out.println("Autor: " + author.getName() + ", Number of Books: " + author.getBookList().size());
          }
10.//No idea
   ..................................................................

