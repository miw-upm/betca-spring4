package miw.persistence.daos.library;

import org.springframework.data.jpa.repository.JpaRepository;

import miw.persistence.entities.library.Book;

public interface BookDao extends JpaRepository<Book, Integer> {
}
