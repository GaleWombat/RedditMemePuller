package redditmemepuller.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;
import redditmemepuller.processors.RedditFunnyProcessor;
import redditmemepuller.processors.RedditProgrammerHumorProcessor;

@Component
public class RedditProgrammerHumorRestRoute extends RouteBuilder {

  private final MongoTemplate template;

  public RedditProgrammerHumorRestRoute(ApplicationContext context) {
    this.template = context.getBean("mongoTemplate", MongoTemplate.class);
  }

  @Override
  public void configure() throws Exception {
    from("timer:mytimer?repeatCount=0&fixedRate=true&period=60000")
            .setHeader("User-Agent", constant("script:reddit.reader:v0.14 (by /u/Ptk7l2)"))
            .toD("https://api.reddit.com/r/ProgrammerHumor/new.json?limit=1000&t=year")
            .process(new RedditProgrammerHumorProcessor(template));
  }
}

