package com.project.ServiceBooking.services;

import com.project.ServiceBooking.data.Language;
import com.project.ServiceBooking.data.User;
import com.project.ServiceBooking.repositories.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LanguageService {

    @Autowired
    LanguageRepository languageRepository;

    public List<Language> findAll(){
        return languageRepository.findAll();
    }

    public List<Language> findByUser(Integer userId){
        return languageRepository.findByUser(userId);
    }
}
