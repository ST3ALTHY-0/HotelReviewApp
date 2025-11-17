package com.St3althy.HotelReviews.database.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.St3althy.HotelReviews.database.repository.HotelCategoryPolarityRepository;
import com.St3althy.HotelReviews.dto.HotelRecord;

import com.St3althy.HotelReviews.exceptions.EntityNotFoundException;

@Service
public class HotelCategoryPolarityService {

    private static final Logger log = LoggerFactory.getLogger(HotelService.class);

    private final HotelCategoryPolarityRepository hotelCategoryPolarityRepository;

    public HotelCategoryPolarityService(HotelCategoryPolarityRepository hotelCategoryPolarityRepository) {
        this.hotelCategoryPolarityRepository = hotelCategoryPolarityRepository;
    }

    public HotelRecord getHotelRecordByName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Hotel name cannot be null or empty");
        }

        HotelRecord hotel = hotelCategoryPolarityRepository.findByHotelName(name);

        if (hotel == null) {
            log.debug("No HotelRecord found for name='{}'", name);
            throw new EntityNotFoundException("Hotel not found: " + name);
        }

        return hotel;
    }

}
