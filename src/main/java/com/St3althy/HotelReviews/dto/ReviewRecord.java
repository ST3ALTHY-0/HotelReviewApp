package com.St3althy.HotelReviews.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


 @AllArgsConstructor
 @NoArgsConstructor
 @Data
public class ReviewRecord{

    private HotelAddress address;

    // maybe change to EnumMap instead of RatingScore
    private RatingScore ratingScore;
    private Review review;
    
}
