package com.tinybeans.ecommerce.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

/** User: ayoade_farooq@yahoo.com Date: 08/06/2021 Time: 04:06 */
@Controller
public class IndexController {

  @GetMapping
  public String index(HttpServletRequest request) {
    return "redirect:/products";
  }
}
