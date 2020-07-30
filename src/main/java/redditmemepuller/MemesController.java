package redditmemepuller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import redditmemepuller.services.MemesService;

@Controller
public class MemesController {

  private final MemesService memesService;

  public MemesController(MemesService memesService) {
    this.memesService = memesService;
  }

  @GetMapping({"", "/", "/index", "/redditMemes", "/reddit_memes", "/reddit/memes"})
  public String getMemesFromRedditMemes(Model model) {
    model.addAttribute("memes", memesService.getAllFromRedditMemes());
    return "index";
  }

  @GetMapping({"/redditFunny", "/reddit_funny", "/reddit/funny"})
  public String getMemesFromRedditFunny(Model model) {
    model.addAttribute("memes", memesService.getAllFromRedditFunny());
    return "index";
  }

  @GetMapping({"/redditProgrammerHumor", "/reddit_programmer_humor", "/reddit/programmerHumor", "/reddit/ProgrammerHumor"})
  public String getMemesFromRedditProgrammerHumor(Model model) {
    model.addAttribute("memes", memesService.getAllFromRedditProgrammerHumor());
    return "index";
  }
}
