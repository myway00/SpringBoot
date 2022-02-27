package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("view")
public class SimpleController {
    private static final Logger logger
            = LoggerFactory.getLogger(SimpleController.class);

    @GetMapping("/simple-jsp")
    public String simpleJsp(Model model){ //model이라는 데이터 추가
        logger.info("in simple jsp");
        //profile arraylist 에 요소들 추가
        List<SimplePayload> profile = new ArrayList<>();
        profile.add(new SimplePayload("custard", 23, "developer"));
        profile.add(new SimplePayload("happy", 22, "teacher"));
        profile.add(new SimplePayload("luckyHappyPretty", 20, "student"));
        
        model.addAttribute("profiles", profile); //전달 요소를 모델에 추가
        
        return "view-jsp";
    }

    @GetMapping("/simple-thyme")
        public ModelAndView simpleThyme(){
            ModelAndView mandv = new ModelAndView();
            logger.info("in simple thyme");
            List<SimplePayload> profile = new ArrayList<>();
            profile.add(new SimplePayload("custard", 23, "developer"));
            profile.add(new SimplePayload("happy", 22, "teacher"));
            profile.add(new SimplePayload("luckyHappyPretty", 20, "student"));

            mandv.addObject("profiles",profile);
            mandv.setViewName("view-thyme");
            return mandv;
        }
    }
