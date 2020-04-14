package com.vilin.jpa.service;

import com.vilin.jpa.entity.Inspection;
import com.vilin.jpa.repository.InspectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class InspectionService {

    @Autowired
    private InspectionRepository inspectionRepository;

    public Inspection saveInspection(Inspection inspection){
        return inspectionRepository.saveAndFlush(inspection);
    }

    public List<Inspection> findAllInspectionByDate(String date){
        System.out.println("======" + date);
        return inspectionRepository.findAllInspectionByDate(date);
    }
}
