package redditmemepuller.repositories;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import redditmemepuller.models.MemeModel;

@Repository
public interface MemesMongoRepository extends MongoRepository<MemeModel, String> {
}
