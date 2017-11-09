package com.ichinait.food.controller.report;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by ichinait on 2016/3/11.
 */
@Controller
@RequestMapping("report")
public class ReportController {

    public String toAddReportPage() {
        return "report/add_report";
    }
}
