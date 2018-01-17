package miw.persistence.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import miw.persistence.entities.UnidirectionalOneToManyEntity;

public interface UnidirectionalOneToManyDao extends JpaRepository<UnidirectionalOneToManyEntity, Integer> {
}
