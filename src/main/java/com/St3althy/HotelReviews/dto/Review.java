package com.St3althy.HotelReviews.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


 @AllArgsConstructor
 @NoArgsConstructor
 @Data
public class Review {

    private String title;
    private String review;
    private LocalDateTime date;
    
}
