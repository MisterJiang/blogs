package com.modules.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = {"app"})
public class IndexController {

    @RequestMapping(value = "")
    public String app(Model uiModel){



        return "modules/app/index";
    }




}
