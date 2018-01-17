package miw.persistence.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import miw.persistence.entities.AnyEntity;

public interface AnyDao extends JpaRepository<AnyEntity, Integer> {
}
