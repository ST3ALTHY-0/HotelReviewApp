package com.St3althy.HotelReviews.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.EnumMap;
import java.util.Map;

/* 
 * DTO for options user can select when searching hotel reviews
 */

 @AllArgsConstructor
 @NoArgsConstructor
 @Data
public class Options {

    // any of these could be null i.e.(Not selected by the user) in which
    // case we don't care about it when searching

    private HotelAddress hotelAddress;
    private LocalDateTime localDateTime;

    /**
     * Note: I did look up how to use multiple enum values like this and
     * found EnumMap
     * Per category min/max rating values. Key is the Rating enum constant.
     * Used if the user wants to find hotels with some rating
     */
    private Map<RatingCategory, Float> minRatings = new EnumMap<>(RatingCategory.class);
    private Map<RatingCategory, Float> maxRatings = new EnumMap<>(RatingCategory.class);

}
