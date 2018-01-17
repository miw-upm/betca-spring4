package miw.persistence.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import miw.persistence.entities.UnidirectionalOneToOneJoinColumnEntity;

public interface UnidirectionalOneToOneJoinColumnDao extends JpaRepository<UnidirectionalOneToOneJoinColumnEntity, Integer> {
}
