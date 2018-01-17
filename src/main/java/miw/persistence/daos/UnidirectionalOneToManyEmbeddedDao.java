package miw.persistence.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import miw.persistence.entities.UnidirectionalOneToManyEmbeddedEntity;

public interface UnidirectionalOneToManyEmbeddedDao extends JpaRepository<UnidirectionalOneToManyEmbeddedEntity, Integer>{

}
