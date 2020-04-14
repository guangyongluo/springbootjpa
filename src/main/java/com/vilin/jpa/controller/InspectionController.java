package com.vilin.jpa.controller;

import com.vilin.jpa.entity.Inspection;
import com.vilin.jpa.service.InspectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/inspection")
public class InspectionController {

    @Autowired
    private InspectionService inspectionService;

    @RequestMapping("/save")
    @ResponseBody
    public Inspection saveInspection(@RequestBody Inspection inspection){
        return inspectionService.saveInspection(inspection);
    }

    @RequestMapping("/findAllOnToDay")
    @ResponseBody
    public List<Inspection> findAllInspection(String date){
        return inspectionService.findAllInspectionByDate(date);
    }
}
