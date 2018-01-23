package miw.persistence.mongo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import miw.persistence.mongo.documents.OneToOneEmbeddedDocument;

public interface OneToOneEmbeddedRepository extends MongoRepository<OneToOneEmbeddedDocument, String> {
}
