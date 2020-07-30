package redditmemepuller.services;

import java.util.Collections;
import java.util.List;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import redditmemepuller.models.MemeModel;

@Service
public class MemesService {

  private final MongoTemplate template;

  public MemesService(MongoTemplate template) {
    this.template = template;
  }

  public List<MemeModel> getAllFromRedditMemes() {
    List<MemeModel> memes = template.findAll(MemeModel.class, "redditMemes");
    Collections.reverse(memes);
    return memes;
  }

  public List<MemeModel> getAllFromRedditFunny() {
    List<MemeModel> memes = template.findAll(MemeModel.class, "redditFunny");
    Collections.reverse(memes);
    return memes;
  }

  public List<MemeModel> getAllFromRedditProgrammerHumor() {
    List<MemeModel> memes = template.findAll(MemeModel.class, "redditProgrammerHumor");
    Collections.reverse(memes);
    return memes;
  }
}
