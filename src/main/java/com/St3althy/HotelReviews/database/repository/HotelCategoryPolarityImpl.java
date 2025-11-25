package com.St3althy.HotelReviews.database.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.St3althy.HotelReviews.database.mapper.HotelRecordResultSetExtractor;
import com.St3althy.HotelReviews.dto.HotelRecord;

@Repository
public class HotelCategoryPolarityImpl implements HotelCategoryPolarityRepository {

    private final NamedParameterJdbcTemplate jdbc;
    private final HotelRecordResultSetExtractor extractor = new HotelRecordResultSetExtractor();

    public HotelCategoryPolarityImpl(NamedParameterJdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public HotelRecord findByHotelName(String name) {
        //:name is just a jdbc safe way of inserting the name, No SQL injection here baby
        final String sql = "SELECT * FROM hotel_category_polarity WHERE hotelname = :name";

        // Map the parameter
        Map<String, Object> params = new HashMap<>();
        params.put("name", name);

        // Use your ResultSetExtractor to get a single result
        return jdbc.query(sql, params, extractor);
    }

}