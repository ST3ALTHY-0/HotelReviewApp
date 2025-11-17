package com.St3althy.HotelReviews.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO that pairs a Rating category with an optional float score.
 * 
 * Im not sure we need this class at all bc we can always use
 * 
 * private Map<Rating, Float> rating = new EnumMap<>(Rating.class);
 * 
 * and that is needed for multiple categories, and can be used for 1 category as
 * well
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RatingScore {

    private RatingCategory category;
    private Float score;

}
