package com.sci.papyrus.service;

import com.sci.papyrus.common.DuplicateResourceException;
import com.sci.papyrus.dto.BookDTO;
import com.sci.papyrus.dto.UpdateBookDTO;
import com.sci.papyrus.entity.Book;
import com.sci.papyrus.repository.BookRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository repository;

    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    public BookDTO create(BookDTO form) {

        if (repository.existsByIsbn(form.getIsbn())) {
            throw new DuplicateResourceException("Book with isbn " + form.getIsbn() + " already exists");
        }
        Book book = Book.builder()
                .isbn(form.getIsbn())
                .title(form.getTitle())
                .author(form.getAuthor())
                .build();

        Book saved = repository.save(book);
        return BookDTO
                .builder()
                .id(saved.getId())
                .author(saved.getAuthor())
                .isbn(saved.getIsbn())
                .title(saved.getTitle())
                .build();
    }

    public List<BookDTO> getAll() {
        return repository
                .findAll()
                .stream()
                .map(book ->
                        BookDTO
                                .builder()
                                .id(book.getId())
                                .author(book.getAuthor())
                                .isbn(book.getIsbn())
                                .title(book.getTitle())
                                .build()
                )
                .toList();
    }

    public  void deleteBook(Long id) {
        repository.deleteById(id);
    }

    public Book updateById(Long id, UpdateBookDTO updateBookDTO) throws ChangeSetPersister.NotFoundException {
        Book bookToUpdate = repository.findById(id)
                .orElseThrow(ChangeSetPersister.NotFoundException::new);
        bookToUpdate.setIsbn(updateBookDTO.getIsbn());
        bookToUpdate.setTitle(updateBookDTO.getTitle());
        bookToUpdate.setAuthor(updateBookDTO.getAuthor());
        return repository.save(bookToUpdate);
    }

}
