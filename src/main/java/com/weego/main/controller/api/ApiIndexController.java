package com.weego.main.controller.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class ApiIndexController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getIndex() {

        return "This is Weego.";
    }
}
