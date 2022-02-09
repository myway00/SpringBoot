package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class SimpleController {
    private static final Logger logger
            = LoggerFactory.getLogger(SimpleController.class);

    @RequestMapping(
            value = "hello"
    )
    public String hello(@RequestParam(name="id", required = false, defaultValue = "")String id){
        logger.info("Path:Hello");
        logger.info("Query Param id : " + id);
        return "hello.html";
    }

    @GetMapping(
            value = "/hello/{id}"
    )
    public String helloPath(@PathVariable("id") String id){
        logger.info("Path variable is" + id);
        return "/hello.html";
    }

    @GetMapping(
            "/get-profile"
    )
    public @ResponseBody SimplePayload getProfile(){
        return new SimplePayload("custard", 23,"Developer");
    }
}
