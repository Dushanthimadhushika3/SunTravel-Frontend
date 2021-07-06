package com.example.suntravels.service;

import com.example.suntravels.model.Hotel;
import com.example.suntravels.repository.HotelRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith( SpringExtension.class )
@DataJpaTest
public class HotelServiceTest
{
    @Autowired
    HotelRepo hotelRepo;

    @TestConfiguration
    static class ContractServiceTestConfiguration{
        @Bean
        public HotelService hotelService(){
            return new HotelService();
        }
    }

    @Autowired
    HotelService hotelService;

    //test injections before other testings
    @Test
    void testInjections() {
        assertThat( hotelService ).isNotNull();
        assertThat( hotelRepo ).isNotNull();
    }

    @Test
    void getHotelsTest(){
        List<Hotel> hotel =  new ArrayList<>(  );
        hotel.add( new Hotel( "Galadari","gdchds",null,"colombo","gdjd",12,"galadari@gmail.com" )  );
        hotel.add( new Hotel( "Hilton","gdchds",null,"colombo","gdjd",19,"hilton@gmail.com" ) );

        for(Hotel newlyadded:hotel){
            hotelRepo.save( newlyadded );
        }
        List<Hotel> hotels = hotelService.getHotels();
        assertThat(hotels).isNotNull();

    }
}
