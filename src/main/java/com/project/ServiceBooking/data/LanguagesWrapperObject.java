package com.project.ServiceBooking.data;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class LanguagesWrapperObject {

    private List<Language> languages;

   public LanguagesWrapperObject(List<Language> languages){
       addLanguages(languages);
   }

    public void addLanguages(List<Language> language) {
        this.languages = new ArrayList<>(language);
    }

}

