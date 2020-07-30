package redditmemepuller.processors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import java.util.ArrayList;
import java.util.List;
import org.apache.camel.Exchange;
import  org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redditmemepuller.models.MemeModel;

abstract class MemesProcessor implements Processor {

  private final ObjectMapper objectMapper;
  private final Logger logger;

  public MemesProcessor() {
    this.objectMapper = new ObjectMapper();
    this.logger = LoggerFactory.getLogger(MemesProcessor.class);
  }

  public List<MemeModel> getDataFromExchange(Exchange exchange) throws JsonProcessingException {
    List<MemeModel> mappedPayload =  new ArrayList<>();
    ArrayNode payload = (ArrayNode) objectMapper.readTree(exchange.getIn().getBody(String.class)).get("data").get("children");
    for(int i = 0; i < payload.size(); i++) {
      mappedPayload.add(new MemeModel(payload.get(i).get("data").get("title").asText(),
              payload.get(i).get("data").get("url").asText()));
    }
    return mappedPayload;
  }

  abstract void saveData(List<MemeModel> models);

  @Override
  public void process(Exchange exchange) throws Exception {
    List<MemeModel> payload = getDataFromExchange(exchange);
    saveData(payload);
    exchange.getIn().setBody(payload);
    logger.info("Obtained memes from "+exchange.getMessage().getHeaders());
  }
}
