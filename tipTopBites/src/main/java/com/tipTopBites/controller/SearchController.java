package com.tipTopBites.controller;

import com.tipTopBites.domain.security.Food;
import com.tipTopBites.domain.security.FoodService;
import com.tipTopBites.domain.security.User;
import com.tipTopBites.domain.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import static com.tipTopBites.controller.HomeController.RADIO_ITEMS;

@Controller
public class SearchController {

    @Autowired
    private UserService userService;

    @Autowired
    private FoodService foodService;

    @RequestMapping("/searchByCategory")
    public String searchByCategory(
            @RequestParam("category") String category,
            Model model, Principal principal
    ) {
        if (principal != null) {
            String username = principal.getName();
            User user = userService.findByUsername(username);
            model.addAttribute("user", user);
        }
        String classActiveCategory = "active" + category;
        classActiveCategory = classActiveCategory.replaceAll("\\s+", "");
        classActiveCategory = classActiveCategory.replaceAll("&", "");
        model.addAttribute(classActiveCategory, true);
        List<Food> foodList = foodService.findByCategory(category);
        if (foodList.isEmpty()) {
            model.addAttribute("emptyList", true);
            return "menu";
        }
        model.addAttribute("foodList", foodList);
        return "menu";
    }

    @RequestMapping("/searchByNumberOfCalories")
    public String searchByNumberOfCalories(
            @RequestParam("numberOfCalories") String category,
            @RequestParam("allergy") String allergy,
            Model model, Principal principal
    ) {
        System.out.println(category + " " + allergy);
        if (principal != null) {
            String username = principal.getName();
            User user = userService.findByUsername(username);
            model.addAttribute("user", user);
        }
        String classActiveCategory;
        if (category.equals("all")) {
            classActiveCategory = "activeAll";
        } else {
            classActiveCategory = "active" + category;
            classActiveCategory = classActiveCategory.replaceAll("\\s+", "");
            classActiveCategory = classActiveCategory.replaceAll("&", "");
            classActiveCategory = classActiveCategory.substring(0, 10);
        }
        model.addAttribute(classActiveCategory, true);
        model.addAttribute("allergyItems", RADIO_ITEMS);
        model.addAttribute("allergy", allergy);
        List<Food> foodList1;
        if (category.equals("all")) {
            foodList1 = foodService.findAll();
        } else {
            foodList1 = foodService.findByNumberOfCalories(category);
        }
        List<Food> foodList = new ArrayList<>();
        if (allergy.toLowerCase().equals("none")) {
            foodList.addAll(foodList1);
        } else {
            for (Food f : foodList1) {
                if (!f.getAllergyType().toLowerCase().equals(allergy.toLowerCase())) {
                    foodList.add(f);
                }
            }
        }
        if (foodList.isEmpty()) {
            model.addAttribute("emptyList", true);
            return "calories";
        }
        model.addAttribute("foodList", foodList);
        return "calories";
    }

    @RequestMapping("/searchFood")
    public String searchFood(
            @ModelAttribute("keyword") String keyword,
            Principal principal, Model model
    ) {
        if (principal != null) {
            String username = principal.getName();
            User user = userService.findByUsername(username);
            model.addAttribute("user", user);
        }
        List<Food> foodList = foodService.blurrySearch(keyword);
        if (foodList.isEmpty()) {
            model.addAttribute("emptyList", true);
            return "menu";
        }
        model.addAttribute("foodList", foodList);
        return "menu";
    }
}
