package com.weego.main.controller.touch;

import com.weego.main.service.ActivityService;
import com.weego.main.service.PgcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/share/v1")
public class ShareController {

    @Autowired
    PgcService pgcService;
    
    @Autowired
    ActivityService activityService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView getShare() {

        ModelAndView mv = new ModelAndView("index");
        mv.addObject("name", "Weego");

        return mv;
    }

    @RequestMapping(value = "/pgc", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView getPgc(@RequestParam("pgcId") String pgcId) {

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
    public ModelAndView getActivity(@RequestParam("activityId") String activityId) {

        return activityService.getSpecifiedActivity(activityId);
    }
}
