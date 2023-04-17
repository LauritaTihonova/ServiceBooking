package com.project.ServiceBooking.data;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PrivateEditForm {
    public User user;
    public List<Language> languageList;

    public String newLanguage;

    public List<Skill> skillList;

    public String newSkill;

    public List<Education> educationList;

    public String newEducation;
}
