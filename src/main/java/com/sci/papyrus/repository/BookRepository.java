package com.sci.papyrus.repository;

import com.sci.papyrus.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book,Long> {

   boolean existsByIsbn(String isbn);
}
