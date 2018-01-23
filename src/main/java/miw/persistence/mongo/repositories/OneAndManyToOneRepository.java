package miw.persistence.mongo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import miw.persistence.mongo.documents.OneAndManyToOneDocument;

public interface OneAndManyToOneRepository extends MongoRepository<OneAndManyToOneDocument, String> {
}
