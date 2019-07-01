package com.amydegregorio.memoryfootprint.dto;

public class BookDto {
   private Long id;
   private String isbn;
   private String title;
   private String author;
   
   /**
    * Gets id.
    * @return the id
    */
   public Long getId() {
      return id;
   }
   
   /**
    * Sets id.
    * @param id the id to set
    */
   public void setId(Long id) {
      this.id = id;
   }
   
   /**
    * Gets isbn.
    * @return the isbn
    */
   public String getIsbn() {
      return isbn;
   }
   
   /**
    * Sets isbn.
    * @param isbn the isbn to set
    */
   public void setIsbn(String isbn) {
      this.isbn = isbn;
   }
   
   /**
    * Gets title.
    * @return the title
    */
   public String getTitle() {
      return title;
   }
   
   /**
    * Sets title.
    * @param title the title to set
    */
   public void setTitle(String title) {
      this.title = title;
   }
   
   /**
    * Gets author.
    * @return the author
    */
   public String getAuthor() {
      return author;
   }
   
   /**
    * Sets author.
    * @param author the author to set
    */
   public void setAuthor(String author) {
      this.author = author;
   }

   /* (non-Javadoc)
    * @see java.lang.Object#toString()
    */
   @Override
   public String toString() {
      return "BookDto [id=" + id + ", isbn=" + isbn + ", title=" + title + ", author=" + author + "]";
   }

   /**
    * @param id
    * @param isbn
    * @param title
    * @param author
    */
   public BookDto(Long id, String isbn, String title, String author) {
      super();
      this.id = id;
      this.isbn = isbn;
      this.title = title;
      this.author = author;
   }
   
   public BookDto() {
      
   }
}
