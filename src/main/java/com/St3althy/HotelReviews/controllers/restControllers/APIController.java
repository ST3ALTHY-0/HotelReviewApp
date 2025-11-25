package com.St3althy.HotelReviews.controllers.restControllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.St3althy.HotelReviews.database.service.HotelCategoryPolarityService;
import com.St3althy.HotelReviews.dto.HotelRecord;
import com.St3althy.HotelReviews.dto.Options;

/*
 * Controller to connect frontend to backend with API's
 */

@RestController
//maybe leave as controller
@RequestMapping("/api")
public class APIController {

    private final HotelCategoryPolarityService hotelCategoryPolarityService;

    public APIController(HotelCategoryPolarityService hotelCategoryPolarityService){
        this.hotelCategoryPolarityService = hotelCategoryPolarityService;
    }




    @GetMapping("/hotels/search/rating")
    public ResponseEntity<HotelRecord> searchHotelRatingByName(@ModelAttribute Options options) {
        if  (options.getHotel() == null) {
                                System.out.println(options);

            throw new IllegalArgumentException("Hotel name is required");
        }

        String hotelName = options.getHotel();
        HotelRecord result = hotelCategoryPolarityService.getHotelRecordByName(hotelName);
        System.out.println("Result: " + result);
        return ResponseEntity.ok(result);
    }
}
