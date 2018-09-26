package com.ihere.voyage.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author fengshibo
 * @create 2018-07-13 15:07
 * @desc ${DESCRIPTION}
 **/
@Controller
public class BoController {
    private static Logger logger = LoggerFactory.getLogger(BoController.class);


    @GetMapping("to1")
    public String to1() {
        return "/index";
    }

    @GetMapping("to")
    public String to() {
        return "/index1";
    }

}
