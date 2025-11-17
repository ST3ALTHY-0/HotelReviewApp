package com.St3althy.HotelReviews.database.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.St3althy.HotelReviews.database.repository.HotelRepository;
import com.St3althy.HotelReviews.dto.HotelAddress;

import com.St3althy.HotelReviews.exceptions.EntityNotFoundException;

@Service
public class HotelService {

    private static final Logger log = LoggerFactory.getLogger(HotelService.class);

    private final HotelRepository hotelRepository;

    public HotelService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    public List<HotelAddress> getAllHotels() {


        List<HotelAddress> hotels = hotelRepository.findAllHotelAddresses();

        // Works for now
        // for (HotelAddress hotelAddress : hotels) {
        //     System.out.println(hotelAddress);
        // }

        if (hotels == null) {
            log.debug("No hotels found");
            throw new EntityNotFoundException("All hotels not found");
        }

        return hotels;
    }

}
