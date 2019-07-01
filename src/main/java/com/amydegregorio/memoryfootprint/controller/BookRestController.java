package com.amydegregorio.memoryfootprint.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import com.amydegregorio.memoryfootprint.dto.BookDto;
import com.amydegregorio.memoryfootprint.model.Book;
import com.amydegregorio.memoryfootprint.repository.BookRepository;

@RestController
@RequestMapping("/book")
public class BookRestController {
   private final Logger logger = LoggerFactory.getLogger(this.getClass());
   
   @Autowired
   private BookRepository bookRepository;
   
   @RequestMapping(value = "/{id}", method = RequestMethod.GET)
   public BookDto getById(@PathVariable @NotNull Long id, HttpServletResponse response) {
      // SpringBoot 1.x
      //Book book = bookRepository.findOne(id);
      // SpringBoot 2.x
      Optional<Book> bookOptional = bookRepository.findById(id);
      BookDto bookDto = null;
      // SpringBoot 1.x
      //if (book != null) {
      // SpringBoot 2.x
      if (bookOptional.isPresent()) {
         Book book = bookOptional.get();
         logger.info("Found Book: " + book.toString());
         bookDto = new BookDto(book.getId(), book.getIsbn(), book.getTitle(), book.getAuthor());
      } else {
         bookDto = new BookDto();
         response.setStatus(HttpServletResponse.SC_NO_CONTENT);
      }
      return bookDto;
   }
   
   @PostMapping
   @ResponseStatus(HttpStatus.CREATED)
   public BookDto createBook(@Valid @RequestBody BookDto bookDto) {
      Book book = new Book(bookDto.getIsbn(), bookDto.getTitle(), bookDto.getAuthor());
      book = bookRepository.save(book);
      bookDto.setId(book.getId());
      return bookDto;
   }
   
   @PutMapping
   public BookDto updateBook(@Valid @RequestBody BookDto bookDto) {
      // SpringBoot 1.x
      //Book book = bookRepository.findOne(bookDto.getId());
      // SpringBoot 2.x
      Optional<Book> bookOptional = bookRepository.findById(bookDto.getId());
      Book book = bookOptional.orElse(new Book());
      
      book.setAuthor(bookDto.getAuthor());
      book.setTitle(bookDto.getTitle());
      book.setIsbn(bookDto.getIsbn());
      bookRepository.save(book);
      return bookDto;
   }
   
   @DeleteMapping("/{id}")
   public void deleteBook(@PathVariable @NotNull Long id, HttpServletResponse response) {
      // SpringBoot 1.x
      //bookRepository.delete(id);
      // SpringBoot 2.x
      bookRepository.deleteById(id);
      response.setStatus(HttpServletResponse.SC_OK);
   }
   
   @GetMapping("/title/{title}")
   public List<BookDto> findByTitle(@PathVariable @NotNull String title, HttpServletResponse response) {
      List<Book> books = bookRepository.findByTitleContainingIgnoreCase(title);
      List<BookDto> bookDtos = null;
      if (books != null) {
         bookDtos = books.stream()
                  .map(book -> new BookDto(book.getId(), book.getIsbn(), book.getTitle(), book.getAuthor()))
                  .collect(Collectors.toList());
         response.setStatus(HttpServletResponse.SC_OK);
      } else {
         bookDtos = new ArrayList<BookDto>();
         response.setStatus(HttpServletResponse.SC_NO_CONTENT);
      }
      
      return bookDtos;
   }
   
   @GetMapping("/author/{author}")
   public List<BookDto> findByAuthor(@PathVariable @NotNull String author, HttpServletResponse response) {
      List<Book> books = bookRepository.findByAuthorContainingIgnoreCase(author);
      List<BookDto> bookDtos = null;
      if (books != null) {
         bookDtos = books.stream()
                  .map(book -> new BookDto(book.getId(), book.getIsbn(), book.getTitle(), book.getAuthor()))
                  .collect(Collectors.toList());
         response.setStatus(HttpServletResponse.SC_OK);
      } else {
         bookDtos = new ArrayList<BookDto>();
         response.setStatus(HttpServletResponse.SC_NO_CONTENT);
      }
      
      return bookDtos;
   }
   
   @GetMapping("/isbn/{isbn}")
   public BookDto getByIsbn(@PathVariable @NotNull String isbn, HttpServletResponse response) {
      Book book = bookRepository.findByIsbn(isbn);
      BookDto bookDto = null;
      if (book != null) {
         logger.info("Found Book: " + book.toString());
         bookDto = new BookDto(book.getId(), book.getIsbn(), book.getTitle(), book.getAuthor());
      } else {
         bookDto = new BookDto();
         response.setStatus(HttpServletResponse.SC_NO_CONTENT);
      }
      return bookDto;
   }
}
