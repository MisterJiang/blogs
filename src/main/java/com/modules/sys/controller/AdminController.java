package com.modules.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2018/2/6.
 */
@Controller
@RequestMapping(value = {"admin"})
public class AdminController {

    @RequestMapping(value = "index")
    public String login(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();

        return "modules/sys/index";
    }



}
