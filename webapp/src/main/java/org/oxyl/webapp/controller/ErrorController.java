package org.oxyl.webapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorController {

    public static final Logger logger = LoggerFactory.getLogger(ErrorController.class);

    @GetMapping("*")
    public String error() {
        logger.error("error 404");
        return "404";
    }
}
