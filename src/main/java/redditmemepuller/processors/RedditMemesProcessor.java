package redditmemepuller.processors;

import com.mongodb.DuplicateKeyException;
import java.util.List;
import org.springframework.data.mongodb.core.MongoTemplate;
import redditmemepuller.models.MemeModel;

public class RedditMemesProcessor extends MemesProcessor{

  private final MongoTemplate mongoTemplate;

  public RedditMemesProcessor(MongoTemplate template) {
    mongoTemplate = template;
  }

  @Override
  void saveData(List<MemeModel> models) {
    for(MemeModel memeModel : models)
      try {
        mongoTemplate.insert(memeModel, "redditMemes");
      } catch (Exception exception) {}
  }
}
