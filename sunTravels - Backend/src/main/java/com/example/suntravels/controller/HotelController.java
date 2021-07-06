package com.example.suntravels.controller;

import com.example.suntravels.model.Hotel;
import com.example.suntravels.service.HotelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin( origins = "*", allowedHeaders = "*" )
@RequestMapping( "/hotels" )
public class HotelController
{
    private static final Logger logger = LoggerFactory.getLogger( ContractController.class );
    @Autowired
    HotelService hotelService;

    /**
     * All hotels saved
     *
     * @return hotel details
     */
    @GetMapping( "/hotels" )
    public List<Hotel> getHotels()
    {
        logger.info( "Return All the hotel details exist" );
        return hotelService.getHotels();
    }
}
