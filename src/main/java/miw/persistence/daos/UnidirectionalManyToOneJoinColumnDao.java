package miw.persistence.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import miw.persistence.entities.UnidirectionalManyToOneJoinColumnEntity;

public interface UnidirectionalManyToOneJoinColumnDao extends JpaRepository<UnidirectionalManyToOneJoinColumnEntity, Integer> {
}
