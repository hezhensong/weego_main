package com.weego.main.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by hezhe on 2016-03-01.
 */
@RestController
@RequestMapping("/")
public class HelloController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "this is weego backend.";
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String getName() {
        return "this is a test.";
    }
}
