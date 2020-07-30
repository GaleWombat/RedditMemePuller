package redditmemepuller.models;

import java.io.Serializable;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
public class MemeModel implements Serializable {
  @Field
  @Indexed(unique = true, name = "UNIQUE_URL")
  private String url;
  @Field
  private String title;
  @Field
  private boolean isVideo;

  public MemeModel(String title, String url) {
    this.url = url;
    this.title = title;
    this.isVideo = (url.contains("v.redd") || url.contains("youtu") || url.contains("vimeo"));
    if(url.contains("watch?v=")) url = url.replace("watch?v=", "embed/");
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setIsVideo(boolean video) {
    isVideo = video;
  }

  public boolean getIsVideo() {
    return isVideo;
  }

  @Override
  public String toString() {
    return "MemeModel:" +
            "url=" + url +
            ", title='" + title + "\'";
  }
}
