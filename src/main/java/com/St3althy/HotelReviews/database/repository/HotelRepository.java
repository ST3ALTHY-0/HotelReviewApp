package com.St3althy.HotelReviews.database.repository;

import com.St3althy.HotelReviews.dto.HotelAddress;
import com.St3althy.HotelReviews.dto.HotelRecord;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;


@Repository
public interface HotelRepository {


    // Return all hotels so we can allow user to select hotel from a list
    List<HotelAddress> findAllHotelAddresses();


}