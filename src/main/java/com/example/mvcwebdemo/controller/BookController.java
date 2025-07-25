package com.example.mvcwebdemo.controller;

import com.example.mvcwebdemo.dao.BookRepository;
import com.example.mvcwebdemo.entity.Book;
import com.example.mvcwebdemo.service.BookService;
import com.example.mvcwebdemo.validator.BookValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private BookRepository bookRepository;

    private final BookValidator bookValidator = new BookValidator();

    // Create a new book

    @PostMapping("/addBook")
    public String addBook(@Valid @RequestBody Book book, BindingResult result) {
        bookValidator.validate(book, result);

        if (result.hasErrors()) {
            return "Validation failed: " + result.getAllErrors();
        }
        bookRepository.save(book);
        return "Book added successfully";
    }

    // Get all books
    @GetMapping("/getBooks")
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    // Get a book by ID
    @GetMapping("/getBook/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Optional<Book> book = bookService.getBookById(id);
        return book.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Update a book
    @PutMapping("/updateBook/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book bookDetails) {
        Book updatedBook = bookService.updateBook(id, bookDetails);
        return ResponseEntity.ok(updatedBook);
    }

    // Delete a book
    @DeleteMapping("/deleteBook/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
}
