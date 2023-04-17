package com.project.ServiceBooking.services;

import com.project.ServiceBooking.data.Skill;
import com.project.ServiceBooking.repositories.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SkillService {
    @Autowired
    SkillRepository skillRepository;

    public List<Skill> findBySeller(Integer sellerId){
        return skillRepository.findBySeller(sellerId);
    }

    public void deleteById(Integer skillId){
        skillRepository.deleteById(skillId);
    }

    public void save(ArrayList<Skill> skills){
        skillRepository.saveAll(skills);
    }

    public void saveOne(Skill skill){ skillRepository.save(skill);}
}
