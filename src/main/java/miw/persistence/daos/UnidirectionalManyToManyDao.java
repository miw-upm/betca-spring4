package miw.persistence.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import miw.persistence.entities.UnidirectionalManyToManyEntity;

public interface UnidirectionalManyToManyDao extends JpaRepository<UnidirectionalManyToManyEntity, Integer> {
}
