package com.example.suntravels.repository;

import com.example.suntravels.model.Hotel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface HotelRepo extends CrudRepository<Hotel, Long>
{
    //return hotel details with given hotel name
    Hotel findByHotelName( String hotelName);
    //return all the hotels saved on db
    List<Hotel> findAll();
}
