package ru.projects.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.projects.entities.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Book getFirstById(Long id);
}
