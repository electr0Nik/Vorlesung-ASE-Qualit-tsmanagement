package com.github.electr0nik.ase.qm.web.mvc.controller;

import com.github.electr0nik.ase.qm.service.JokeService;
import com.github.electr0nik.ase.qm.service.dto.domain.JokeDomain;
import com.github.electr0nik.ase.qm.web.mvc.form.JokeTO;
import org.apache.catalina.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nik on 05.06.15.
 */
@Controller
@SessionAttributes("lastJoke")
public class MainController {

  private JokeService jokeService;

  @Autowired
  public MainController(JokeService jokeService) {
    this.jokeService = jokeService;
  }

  @ModelAttribute("jokeModel")
  public JokeTO getJokeToModel(@Value("${com.github.electr0nik.joke.categorylist}") List<String> categoryList) {
    JokeTO joke = new JokeTO();
    joke.setCategoryList(categoryList);
    return joke;
  }

  @RequestMapping(value = "/joke", method = RequestMethod.GET)
  public String getDefaultView(@ModelAttribute("jokeModel") JokeTO jokeModel) {
    return "index";
  }

  @RequestMapping(value = "/simplejoke", method = {RequestMethod.GET, RequestMethod.POST})
  public String getSimpleJokoResult(Model model, HttpSession session) {

    List<Integer> idList = new ArrayList<>();
    if (session.getAttribute("idList") != null && !((List<Integer>) session.getAttribute("idList")).isEmpty()) {
      List<Integer> tmpList = (List<Integer>) session.getAttribute("idList");
      idList.add(tmpList.get(tmpList.size() - 1));
    }

    List<JokeDomain> jokeDomainList = this.jokeService.getSimpleRandomJoke(idList);

    idList.clear();

    jokeDomainList.forEach(jokeDomain -> idList.add(jokeDomain.getId()));
    session.setAttribute("idList", idList);

    model.addAttribute("returnValue", jokeDomainList);
    return "index";
  }

  @RequestMapping(value = "/joke", method = RequestMethod.POST)
  public String getExtendedJokoResult(
      Model model, HttpSession session, @Valid @ModelAttribute("jokeModel") JokeTO jokeModel, BindingResult result) {

    if (!result.hasErrors()) {
      List<Integer> idList = new ArrayList<>();
      if (session.getAttribute("idList") != null) {
        idList.addAll((List<Integer>) session.getAttribute("idList"));
      }

      List<JokeDomain> jokeDomainList = this.jokeService.getSpecificJoke(idList, jokeModel);

      idList.clear();

      jokeDomainList.forEach(jokeDomain -> idList.add(jokeDomain.getId()));
      session.setAttribute("idList", idList);

      model.addAttribute("returnValue", jokeDomainList);
    }

    return "index";
  }
}
