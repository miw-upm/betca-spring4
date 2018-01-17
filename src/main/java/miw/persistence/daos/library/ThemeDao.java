package miw.persistence.daos.library;

import org.springframework.data.jpa.repository.JpaRepository;

import miw.persistence.entities.library.Theme;

public interface ThemeDao extends JpaRepository<Theme, Integer> {
}
