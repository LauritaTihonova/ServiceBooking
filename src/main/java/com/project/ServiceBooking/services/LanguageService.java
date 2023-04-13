package com.project.ServiceBooking.services;

import com.project.ServiceBooking.data.Language;
import com.project.ServiceBooking.repositories.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LanguageService {

    @Autowired
    LanguageRepository languageRepository;

    public List<Language> findByUser(Integer userId){
        return languageRepository.findByUser(userId);
    }

    public void deleteById(Integer languageId){
        languageRepository.deleteById(languageId);
    }

    public void save(ArrayList<Language> languages){
        languageRepository.saveAll(languages);
    }
}
