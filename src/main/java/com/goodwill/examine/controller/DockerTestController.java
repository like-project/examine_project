package com.goodwill.examine.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: ke.li
 * @Date: Created in 2025/1/17 11:55
 * @Version: 1.0
 */
@RestController
@RequestMapping(value = "/docker")
public class DockerTestController {

  @GetMapping(value = "/test")
  public String test() {
    return "test";
  }


}
