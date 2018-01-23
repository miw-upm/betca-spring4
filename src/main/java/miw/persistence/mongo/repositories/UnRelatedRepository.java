package miw.persistence.mongo.repositories;

import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import miw.persistence.mongo.documents.UnRelatedDocument;

public interface UnRelatedRepository extends MongoRepository<UnRelatedDocument, String> {
 // Consulta: por Nombre de Método
    UnRelatedDocument findByNickIgnoreCase(String nick);

    List<UnRelatedDocument> findFirst3ByNickStartingWith(String prefix);

    List<UnRelatedDocument> findByNickOrLargeOrderByLogerDesc(String nick, String large);

    List<UnRelatedDocument> findByIntegerGreaterThan(int integer, Pageable pageable);

    List<UnRelatedDocument> findByNickIn(Collection<String> values);

    @Transactional
    int deleteByNick(String nick);
    
    UnRelatedDocument findByNick(String nick);


}
