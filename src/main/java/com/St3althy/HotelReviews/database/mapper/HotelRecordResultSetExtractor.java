package com.St3althy.HotelReviews.database.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.St3althy.HotelReviews.dto.HotelAddress;
import com.St3althy.HotelReviews.dto.HotelRecord;
import com.St3althy.HotelReviews.dto.RatingCategory;

/**
 * ResultSetExtractor that aggregates rows (one per category) into a list of
 * HotelRecord objects (one per hotel). Expects the result set to contain at
 * least: hotel_id (long), hotel (name), country, region, state, city,
 * category (nullable), score (nullable float).
 */
public class HotelRecordResultSetExtractor implements ResultSetExtractor<HotelRecord> {

    private static final Logger log = LoggerFactory.getLogger(HotelRecordResultSetExtractor.class);

     @Override
    public HotelRecord extractData(ResultSet rs) throws SQLException {
        HotelRecord hotelRecord = null;

        while (rs.next()) {
            //for the first row we set the name of the obj, after the first row we can just worry about categories and polarities
            if (hotelRecord == null) {
                hotelRecord = new HotelRecord();

                hotelRecord.setHotelName(rs.getString("hotelName"));
            }

            String cat = rs.getString("categoryName");
            if (cat != null) {
                try {
                    RatingCategory r = RatingCategory.valueOf(cat.trim().toUpperCase());
                    float raw = rs.getFloat("normalizedPolarity");
                    Float score = rs.wasNull() ? null : Float.valueOf(raw);
                    //put the RatingCategory and score in the hotelRecord.Rating map
                    hotelRecord.getRating().put(r, score);
                } catch (IllegalArgumentException iae) {
                    // unknown category name from DB, log and skip
                    if (log.isDebugEnabled()) log.debug("Unknown rating category from DB: {}", cat);
                }
            }
        }

        return hotelRecord;
    }
}
