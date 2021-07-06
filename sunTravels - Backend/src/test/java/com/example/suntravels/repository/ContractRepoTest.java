package com.example.suntravels.repository;

import com.example.suntravels.dto.ContractDTO;
import com.example.suntravels.model.ContactNo;
import com.example.suntravels.model.Contract;
import com.example.suntravels.model.Hotel;
import com.example.suntravels.model.RoomType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith( SpringExtension.class )
@DataJpaTest
@ActiveProfiles( "test" )
class ContractRepoTest
{
    @Autowired
    ContractRepo contractRepo;

    @Autowired
    HotelRepo hotelRepo;
    @Autowired
    RoomTypeRepo roomTypeRepo;


    List<ContactNo> contactNo = new ArrayList<>();
    LocalDate startDate = LocalDate.of( 2020, 07, 13 );
    LocalDate endDate = LocalDate.of( 2020, 10, 12 );
    List<RoomType> roomTypes = new ArrayList<>();
    Contract contract = new Contract();
    Hotel hotel = new Hotel();
    //ContactNo contact = new ContactNo(  );
    ContactNo a = new ContactNo( "0666789965" );
    ContactNo b = new ContactNo( "4489096548" );

    @Test
    void findByCIdTest()
    {
        hotel.setHotelName( "Hilton" );
        contract.setMarkup( 15 );
        hotel.setAddress1( "Hilton" );
        hotel.setCity( "Colombo" );
        hotel.setHId( 1l );
        contract.setCId( 1l );
        hotel.setContacts( contactNo );
        hotel.setEmail( "hilton@gmail.com" );
        contract.setEndDate( endDate );
        contract.setStartDate( startDate );
        hotel.setState( "xyz" );
        hotel.setZipCode( 23 );
        RoomType r1 = new RoomType();
        r1.setNoOfAdults( 4 );
        r1.setNoOfRooms( 1 );
        r1.setPrice( 100 );
        r1.setType( "standard" );
        r1.setRoomTId( 1l );
        roomTypes.add( r1 );

        //save hotel before contract
        Hotel h = hotelRepo.save( hotel );

        //add hotel to contract
        contract.setHotel( h );
        Contract newContractAdded = contractRepo.save( contract );//save contract


        //roomType values
        for( RoomType roomType : roomTypes )
        {
            roomType.setContract( newContractAdded );
            roomTypeRepo.save( roomType );//save hotel
        }
        List<Object[]> result = contractRepo.findByCId( contract.getCId() );
        assertThat( result ).isNotNull();
    }
}
