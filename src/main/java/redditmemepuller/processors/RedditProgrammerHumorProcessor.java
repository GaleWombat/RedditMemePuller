package redditmemepuller.processors;

import java.util.List;
import java.util.function.Supplier;
import org.apache.camel.Processor;
import org.springframework.data.mongodb.core.MongoTemplate;
import redditmemepuller.models.MemeModel;

public class RedditProgrammerHumorProcessor extends MemesProcessor {
  private final MongoTemplate mongoTemplate;

  public RedditProgrammerHumorProcessor(MongoTemplate template) {
    mongoTemplate = template;
  }

  @Override
  void saveData(List<MemeModel> models) {
    for(MemeModel memeModel : models)
      try {
        mongoTemplate.insert(memeModel, "redditProgrammerHumor");
      } catch (Exception exception) {}

  }
}
