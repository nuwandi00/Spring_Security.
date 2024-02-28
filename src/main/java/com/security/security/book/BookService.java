package com.security.security.book;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository repository;

    public void save(BookRequest request) {
        var book = Book.builder()
                .id(request.getId())
                .author(request.getAuthor())
                .isbn(request.getIsbn())
                .build();
        repository.save(book);
    }

    public List<Book> findAll() {
        return repository.findAll();
    }

    @Transactional
    public void update(Integer id, BookRequest request) {
        Optional<Book> optionalBook = repository.findById(id);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            book.setAuthor(request.getAuthor());
            book.setIsbn(request.getIsbn());
            repository.save(book);
        } else {
            throw new IllegalArgumentException("Book with ID " + id + " not found");
        }
    }

    @Transactional
    public void delete(Integer id) {
        Optional<Book> optionalBook = repository.findById(id);
        if (optionalBook.isPresent()) {
            repository.delete(optionalBook.get());
        } else {
            throw new IllegalArgumentException("Book with ID " + id + " not found");
        }
    }
}

