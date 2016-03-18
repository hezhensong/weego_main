package com.weego.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/share/v1")
public class ShareController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView getShare() {

        ModelAndView mv = new ModelAndView("index");
        mv.addObject("name", "Weego");

        return mv;
    }

    @RequestMapping(value = "/pgc", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView getPgc() {

        ModelAndView mv = new ModelAndView("PGC");
        mv.addObject("author", "刘钰");
        mv.addObject("head", "1.jpg");
        mv.addObject("article_1", "aaaxxxcccc");
        mv.addObject("article_2", "dddddxxxcccc");
        mv.addObject("article_3", "aavvvxxcccc");
        mv.addObject("head", "1.jpg");
        mv.addObject("head", "1.jpg");

        return mv;
    }

    @RequestMapping(value = "/activity", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView getActivity() {

        ModelAndView mv = new ModelAndView("activity");
        mv.addObject("test", "test");

        return mv;
    }
}
