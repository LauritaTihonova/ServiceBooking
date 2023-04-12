package com.project.ServiceBooking.controllers;

import com.project.ServiceBooking.data.Subcategory;
import com.project.ServiceBooking.services.SubcategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class SubcategoryController {

    private final SubcategoryService subcategoryService;

    public SubcategoryController(SubcategoryService subcategoryService) {
        this.subcategoryService = subcategoryService;
    }

    @GetMapping("/subcategories")
    public String showSubcategory(Model model){
        List<Subcategory> subcategories=subcategoryService.findAll();
        model.addAttribute("subcategories",subcategories);
        return "listSubcategories";
    }

    @GetMapping("/createSub")
    public String createSubForm(){
        return "createSub";
    }
    @PostMapping("/createSub")
    public String createSub(Subcategory subcategory){
        subcategoryService.saveSubcategory(subcategory);
        return "listSubcategories";
    }
    @GetMapping("/updateSub/{id}")
    public String updateSubForm(@PathVariable("id")Integer id, Model model){
        Optional<Subcategory> subcategory=subcategoryService.findById(id);
        model.addAttribute("subcategory",subcategory );
        return "updateSub";
    }
    @PostMapping("/updateSub")
    public String updateSub(Subcategory subcategory){
        subcategoryService.saveSubcategory(subcategory);
        return "listSubcategories";
    }

}
