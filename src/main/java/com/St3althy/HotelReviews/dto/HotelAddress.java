package com.St3althy.HotelReviews.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 *  Hotel info obj
 * 
 * TODO: could probably save mem if we split this class down into its components, test speed/memory later
 * 
 * maybe caching for repeated countries, regions, states etc to speed up 
 * 
 * Must have some level of caching built in, because reloads are super quick
 * First load is quite bad >1000ms
 */

 @AllArgsConstructor
 @NoArgsConstructor
 @Data
public class HotelAddress {

    private Long hotelId;
    private String country;
    private String region;
    private String city;
    private String hotel;

    
}
