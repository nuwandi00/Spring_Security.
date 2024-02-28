package com.security.security.demo;

import com.security.security.book.Book;
import com.security.security.book.BookRequest;
import com.security.security.book.BookService;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {
    private final BookService bookService;

    public AdminController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/view")
    @PreAuthorize("hasAuthority('admin:read')")
    public List<Book> get() {
        return bookService.findAll();
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('admin:create')")
    public void createBook(@RequestBody BookRequest request) {
        bookService.save(request);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAuthority('admin:update')")
    public void updateBook(@PathVariable Integer id, @RequestBody BookRequest request) {
        bookService.update(id, request);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('admin:delete')")
    public void deleteBook(@PathVariable Integer id) {
        bookService.delete(id);
    }
}
