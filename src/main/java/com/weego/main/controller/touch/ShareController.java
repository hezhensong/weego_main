package com.weego.main.controller.touch;

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

        ModelAndView mv = pgcService.getSpecifiedPgc(pgcId);

//        ModelAndView mv = new ModelAndView("PGC");

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
