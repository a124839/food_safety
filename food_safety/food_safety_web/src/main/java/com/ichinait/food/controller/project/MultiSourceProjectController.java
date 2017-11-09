package com.ichinait.food.controller.project;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by IChinait on 2016/7/18.
 */
@Controller
@RequestMapping("msProject")
public class MultiSourceProjectController {

    @RequestMapping("list")
    public String list(){
        return "/ms_project/list";
    }
}
