package com.project.ServiceBooking.services;

import com.project.ServiceBooking.data.Education;
import com.project.ServiceBooking.repositories.EducationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EducationService {

    @Autowired
    EducationRepository educationRepository;

    public List<Education> findBySeller(Integer sellerId){
        return educationRepository.findBySeller(sellerId);
    }

    public void deleteById(Integer educationId){
        educationRepository.deleteById(educationId);
    }

    public void save(ArrayList<Education> educations){
        educationRepository.saveAll(educations);
    }

    public void saveOne(Education education){ educationRepository.save(education);}
}
