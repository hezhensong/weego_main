package com.weego.main.controller.www;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/web/v1")
public class WebIndexController {

	@ResponseBody
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView getIndex() {

        ModelAndView mv = new ModelAndView("index");
        mv.addObject("name", "Weego Web ");

        return mv;
    }
}
