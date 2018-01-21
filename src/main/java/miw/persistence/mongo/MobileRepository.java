package miw.persistence.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface MobileRepository extends MongoRepository<Mobile, String> {

}
