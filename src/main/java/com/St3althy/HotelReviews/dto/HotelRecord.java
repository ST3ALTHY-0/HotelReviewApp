package com.St3althy.HotelReviews.dto;


import java.util.EnumMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * Obj for if we want to return just a hotel with its aggregated ratings
 */

 @AllArgsConstructor
 @NoArgsConstructor
 @Data
public class HotelRecord {

    /*
     * We are just storing the name here, might change later.
     * 
     * We can just store the name here, and once we return this obj to the client 
     * they should already have all the hotel address and names,
     * so we can match the name to the address client side so we don't have to do
     * a ton of complex/slow joins on the db
     */

    private String hotelName;
    private Map<RatingCategory, Float> rating = new EnumMap<>(RatingCategory.class);
    
}
