package com.St3althy.HotelReviews.controllers.webControllers;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.St3althy.HotelReviews.database.service.HotelService;
import com.St3althy.HotelReviews.dto.HotelAddress;
import com.St3althy.HotelReviews.dto.Options;
import com.St3althy.HotelReviews.dto.RatingCategory;

@Controller
public class HomeController {

    private final HotelService hotelService;

    // we use constructor injection to get HotelService obj
    public HomeController(HotelService hotelService){
        this.hotelService = hotelService;
    }

    @GetMapping("/")
    public String home(Model model) {
        // My thoughts:
        /*
         * Send all basic hotel info to client at start, like countries, cities,
         * regions, etc, maybe hotel name(check how long that takes)
         * so that we can use that info to allow the user to set options
         * This should be pretty short and take under 300-500ms 
         *
         * 
         * and when the user submits the options,
         * retrieve the hotel data from DB with those options
         */

         List<HotelAddress> hotels = hotelService.getAllHotels();

        // We use Sets so we arnt getting duplicate entries (We use this for the drop down menu in home/html)
        Set<String> countries = new LinkedHashSet<>();
        Set<String> regions = new LinkedHashSet<>();
        Set<String> cities = new LinkedHashSet<>();
        List<String> hotelNames = new ArrayList<>();

        for (HotelAddress h : hotels) {
            if (h.getCountry() != null) countries.add(h.getCountry());
            if (h.getRegion() != null) regions.add(h.getRegion());
            if (h.getCity() != null) cities.add(h.getCity());
            if (h.getHotel() != null) hotelNames.add(h.getHotel());
        }

        //eh this wont work in long term, we should probably use JS to get hotel names in the passed 'hotels' 
        //and display limited hotels based on options that way
         List<String> limitedHotelNames = hotelNames.stream().distinct().limit(50).toList();

        model.addAttribute("ratingCategories", RatingCategory.values());
        model.addAttribute("hotels", hotels);
        model.addAttribute("countries", countries);
        model.addAttribute("regions", regions);
        model.addAttribute("cities", cities);
        model.addAttribute("hotelNames", limitedHotelNames);
        return "home";
    }
    
    @GetMapping("/error")
    public String error() {
        return "error";
    }

    @PostMapping("/hotel-reviews/options/submit")
    public String optionsSubmit(@ModelAttribute Options options, Model model) {
        // TODO: process options and return a result in the model

        model.addAttribute("results", null);
        return "home";
    }

}
