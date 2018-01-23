package miw.persistence.mongo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import miw.persistence.mongo.documents.AnyDocument;

public interface AnyRepository extends MongoRepository<AnyDocument, String> {
}
