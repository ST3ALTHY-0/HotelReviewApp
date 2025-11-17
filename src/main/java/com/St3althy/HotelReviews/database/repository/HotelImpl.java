package com.St3althy.HotelReviews.database.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.St3althy.HotelReviews.database.mapper.HotelAddressResultSetExtractor;
import com.St3althy.HotelReviews.database.mapper.HotelRecordResultSetExtractor;
import com.St3althy.HotelReviews.dto.HotelAddress;
import com.St3althy.HotelReviews.dto.HotelRecord;

@Repository
public class HotelImpl implements HotelRepository {

    private final NamedParameterJdbcTemplate jdbc;
    private final HotelAddressResultSetExtractor extractor = new HotelAddressResultSetExtractor();

    public HotelImpl(NamedParameterJdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public List<HotelAddress> findAllHotelAddresses() {
        final String sql = """
                    SELECT
                    h.hotelid,
                    h.hotelname,
                    c.cityname,
                    r.regionname,
                    co.countryname
                FROM
                    hotel h
                    LEFT JOIN city c ON h.cityid = c.cityid
                    LEFT JOIN region r ON c.regionid = r.regionid
                    LEFT JOIN country co ON r.countryid = co.countryid
                    ORDER BY h.hotelname
                            """;

        return jdbc.query(sql, extractor);
    }

}
