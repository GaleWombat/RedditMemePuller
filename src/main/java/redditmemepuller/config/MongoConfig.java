package redditmemepuller.config;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import com.mongodb.WriteConcern;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.Index;

@Configuration
public class MongoConfig {

  @Bean("mongoBean")
  public MongoClient getMongoClient(){
    return new MongoClient(new ServerAddress("localhost"), new MongoClientOptions.Builder().build());
  }

  @Bean("mongoTemplate")
  public MongoTemplate getMongoTemplate(MongoClient client) {
    MongoTemplate template = new MongoTemplate(client, "memes");
    template.setWriteConcern(WriteConcern.ACKNOWLEDGED);
    template.indexOps("redditMemes").ensureIndex(new Index("url", Sort.Direction.ASC).unique());
    template.indexOps("redditFunny").ensureIndex(new Index("url", Sort.Direction.ASC).unique());
    template.indexOps("redditProgrammerHumor").ensureIndex(new Index("url", Sort.Direction.ASC).unique());
    return template;
  }
}
