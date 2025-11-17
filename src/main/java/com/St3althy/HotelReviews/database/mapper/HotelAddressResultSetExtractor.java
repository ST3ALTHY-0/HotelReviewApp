package com.St3althy.HotelReviews.database.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.St3althy.HotelReviews.dto.HotelAddress;


/*
 * Extract all the hotels returned from HotelImpl.java
 */


public class HotelAddressResultSetExtractor implements ResultSetExtractor<List<HotelAddress>> {

    @Override
    public List<HotelAddress> extractData(ResultSet rs) throws SQLException, DataAccessException {
        List<HotelAddress> hotelAddresses = new ArrayList<>();

        while (rs.next()) {
            HotelAddress hotelAddress = new HotelAddress();

            hotelAddress.setHotelId(rs.getLong("hotelid"));
            hotelAddress.setHotel(rs.getString("hotelname"));
            hotelAddress.setCity(rs.getString("cityname"));
            hotelAddress.setRegion(rs.getString("regionname"));
            hotelAddress.setCountry(rs.getString("countryname"));

            hotelAddresses.add(hotelAddress);
        }

        return hotelAddresses;
    }
    
}
