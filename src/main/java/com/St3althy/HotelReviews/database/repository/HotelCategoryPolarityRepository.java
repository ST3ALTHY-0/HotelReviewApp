package com.St3althy.HotelReviews.database.repository;

import org.springframework.stereotype.Repository;

import com.St3althy.HotelReviews.dto.HotelRecord;

@Repository
public interface HotelCategoryPolarityRepository {

    // find rating/polarity of a hotel by its name
    HotelRecord findByHotelName(String name);

}
