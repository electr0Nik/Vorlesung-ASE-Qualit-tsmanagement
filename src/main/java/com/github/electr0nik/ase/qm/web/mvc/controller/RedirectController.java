package com.github.electr0nik.ase.qm.web.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by nik on 23.06.15.
 */

@Controller
public class RedirectController {

  @RequestMapping(value = "/")
  public String getRedirectToJokes() {
    return "redirect:/joke";
  }
}
