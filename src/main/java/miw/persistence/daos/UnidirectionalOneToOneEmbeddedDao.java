package miw.persistence.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import miw.persistence.entities.UnidirectionalOneToOneEmbeddedEntity;

public interface UnidirectionalOneToOneEmbeddedDao extends JpaRepository<UnidirectionalOneToOneEmbeddedEntity, Integer> {

}
