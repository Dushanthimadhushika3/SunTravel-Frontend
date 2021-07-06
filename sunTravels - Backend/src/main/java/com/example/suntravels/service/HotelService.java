package com.example.suntravels.service;

import com.example.suntravels.model.Hotel;
import com.example.suntravels.repository.HotelRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class HotelService
{
    @Autowired
    HotelRepo hotelRepo;

    /**
     * All hotels saved
     * @return hotel details
     */
    public List<Hotel> getHotels()
    {
       return hotelRepo.findAll();

    }

}
