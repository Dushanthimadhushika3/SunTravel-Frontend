package com.example.suntravels.service;

import com.example.suntravels.dto.ContractDTO;
import com.example.suntravels.model.ContactNo;
import com.example.suntravels.model.Contract;
import com.example.suntravels.model.Hotel;
import com.example.suntravels.model.Response;
import com.example.suntravels.model.RoomType;
import com.example.suntravels.repository.ContractRepo;
import com.example.suntravels.repository.CustomQueryRepoImp;
import com.example.suntravels.repository.HotelRepo;
import com.example.suntravels.repository.RoomTypeRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith( SpringExtension.class )
@DataJpaTest
class ContractServiceTest
{
    @Autowired
    ContractRepo contractRepo;
    @Autowired
    HotelRepo hotelRepo;
    @Autowired
    RoomTypeRepo roomTypeRepo;
    @MockBean
    CustomQueryRepoImp customQueryRepoImp;


    @TestConfiguration
    static class ContractServiceTestConfiguration{
        @Bean
        public ContractService contractService(){
            return new ContractService();
        }
    }

    @Autowired
    ContractService contractService;


    ContractDTO contractDTO = new ContractDTO();
    List<ContactNo> contactNo = new ArrayList<>();
    LocalDate startDate = LocalDate.of(2020,07,13);
    LocalDate endDate = LocalDate.of( 2020,10,12 );
    List<RoomType> roomTypes = new ArrayList<>(  );
    Contract contract = new Contract(  );
    Hotel hotel = new Hotel(  );
    ContactNo a = new ContactNo( "0666789965" );
    ContactNo b = new ContactNo( "4489096548" );

    //test injections before other testings
    @Test
    void testInjections() {
        assertThat( contractService ).isNotNull();
        assertThat( contractRepo ).isNotNull();
        assertThat( contract ).isNotNull();
        assertThat( roomTypes ).isNotNull();
    }

    //test contract adding with hotel
    @Test
    void addNewContractHotelTest(){
        //create contractDTO
        contractDTO.setHotelName( "Hilton" );
        contractDTO.setMarkup( 15 );
        contractDTO.setAddress1( "Hilton" );
        contractDTO.setCity( "Colombo" );

        contractDTO.setContactNoList(contactNo );
        contractDTO.setEmail( "hilton@gmail.com" );
        contractDTO.setEndDate( endDate );
        contractDTO.setStartDate( startDate );
        contractDTO.setState( "xyz" );
        contractDTO.setZipCode( 23 );
        RoomType r1 = new RoomType(  );
        r1.setNoOfAdults( 4 );
        r1.setNoOfRooms( 1 );
        r1.setPrice( 100 );
        r1.setType( "standard" );
        roomTypes.add( r1 );
        contractDTO.setRoomTypeList( roomTypes );

        //test contract service
        Response response = contractService.addNewContractHotel( contractDTO );

        assertThat(response.getPayload()).isSameAs( contractDTO );
        assertThat(response.getMessage()).isSameAs( "Contract Save Successfully" );
    }

    //test contract adding for existing hotel
    @Test
    void addNewContractTest(){
        //create contractDTO
        contractDTO.setHotelName( "Hilton" );
        contractDTO.setMarkup( 15 );
        contractDTO.setAddress1( "Hilton" );
        contractDTO.setCity( "Colombo" );

        contractDTO.setContactNoList(contactNo );
        contractDTO.setEmail( "hilton@gmail.com" );
        contractDTO.setEndDate( endDate );
        contractDTO.setStartDate( startDate );
        contractDTO.setState( "xyz" );
        contractDTO.setZipCode( 23 );
        RoomType r1 = new RoomType(  );
        r1.setNoOfAdults( 4 );
        r1.setNoOfRooms( 1 );
        r1.setPrice( 100 );
        r1.setType( "standard" );
        roomTypes.add( r1 );
        contractDTO.setRoomTypeList( roomTypes );

        //hotel values
        hotel.setZipCode( contractDTO.getZipCode() );
        hotel.setState( contractDTO.getState() );
        hotel.setCity( contractDTO.getCity() );
        hotel.setHotelName( contractDTO.getHotelName() );
        hotel.setAddress1( contractDTO.getAddress1() );
        hotel.setAddress2( contractDTO.getAddress2() );
        hotel.setContacts( contractDTO.getContactNoList() );
        hotel.setEmail( contractDTO.getEmail() );
        hotel.setHId( 1l );

        //save hotel before for testing contract
        Hotel h = hotelRepo.save( hotel );
        assertThat( h.getHotelName()).isSameAs(hotel.getHotelName() );
        //test existing hotel contract service
        Response response = contractService.addNewContract( contractDTO,h );
        assertThat(response.getMessage()).isSameAs( "Contract Save Successfully" );

    }

}
