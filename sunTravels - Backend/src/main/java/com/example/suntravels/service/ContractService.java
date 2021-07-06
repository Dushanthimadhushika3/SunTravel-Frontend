package com.example.suntravels.service;

import com.example.suntravels.dto.ContractDTO;
import com.example.suntravels.dto.SearchResults;
import com.example.suntravels.model.Contract;
import com.example.suntravels.model.Hotel;
import com.example.suntravels.model.Response;
import com.example.suntravels.model.RoomType;
import com.example.suntravels.repository.ContractRepo;
import com.example.suntravels.repository.CustomQueryRepoImp;
import com.example.suntravels.repository.HotelRepo;
import com.example.suntravels.repository.RoomTypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Service
public class ContractService
{

    @Autowired
    ContractRepo contractRepo;
    @Autowired
    HotelRepo hotelRepo;
    @Autowired
    RoomTypeRepo roomTypeRepo;
    @Autowired
    CustomQueryRepoImp customQueryRepoImp;

    /**
     * add new contract for a new hotel
     * save both contract and hotel details
     *
     * @param newContract
     * @return response mgs as Contract Save Successfully on success
     */
    public Response addNewContractHotel( ContractDTO newContract )
    {
        Contract contract = new Contract();
        contract.setMarkup( newContract.getMarkup() );
        contract.setEndDate( newContract.getEndDate() );
        contract.setStartDate( newContract.getStartDate() );

        Hotel hotel = new Hotel();
        hotel.setHotelName( newContract.getHotelName() );
        hotel.setAddress1( newContract.getAddress1() );
        hotel.setAddress2( newContract.getAddress2() );
        hotel.setCity( newContract.getCity() );
        hotel.setState( newContract.getState() );
        hotel.setZipCode( newContract.getZipCode() );
        hotel.setEmail( newContract.getEmail() );
        hotel.setContacts( newContract.getContactNoList() );
        Hotel newHotel = hotelRepo.save( hotel );

        contract.setHotel( newHotel );//add hotel to contracts
        Contract newContractAdded = contractRepo.save( contract );

        newContract.getRoomTypeList().stream().forEach( room ->
        {
            room.setContract( contract );
            roomTypeRepo.save( room );
        } );

        return new Response( "Success", "Contract Save Successfully", newContract );
    }

    /**
     * add new contract for existing hotel
     *
     * @param newContract
     * @return response mgs as Contract Save Successfully on success
     */
    public Response addNewContract( ContractDTO newContract, Hotel hotel )
    {
        Contract contract = new Contract();
        contract.setMarkup( newContract.getMarkup() );
        contract.setEndDate( newContract.getEndDate() );
        contract.setStartDate( newContract.getStartDate() );
        contract.setHotel( hotel );//add hotel to contracts
        Contract newContractAdded = contractRepo.save( contract );

        newContract.getRoomTypeList().stream().forEach( room ->
        {
            room.setContract( newContractAdded );
            roomTypeRepo.save( room );
        } );

        return new Response( "Success", "Contract Save Successfully", newContract );


    }

    /**
     * search for hotels as per the client requirements
     *
     * @param contractDTO
     * @return hotel details with prices and room types
     */
    public List<SearchResults> getResults( ContractDTO contractDTO )
    {
        List<SearchResults> finalOut = new ArrayList<>();
        //List<SearchResults> finalOutDup = new ArrayList<>();
        //List<SearchResults> out = new ArrayList<>();
        Period period = Period.between( contractDTO.getStartDate(), contractDTO.getEndDate() );
        long noOfNights = period.getDays();//no of nights staying
        List<Contract> results = customQueryRepoImp.customQuery( contractDTO );

        if( !results.isEmpty() )
        {
            results.stream().forEach( c ->
            {
                //retrieve data on contract id
                List<Object[]> obj = contractRepo.findByCId( c.getCId() );

                obj.stream().forEach( r ->
                        {
                            //loop over room combination
                            contractDTO.getRoomTypeList().stream().forEach( roomType ->
                            {
                                SearchResults searchResults = new SearchResults();
                                //filter results
                                if( ( int ) r[3] >= roomType.getNoOfRooms() && ( int ) r[4] >= roomType.getNoOfAdults() )
                                {
                                    searchResults.setHotelName( ( String ) r[0] );
                                    //calculate room price
                                    float roomPrice = ( ( c.getMarkup() + 100 ) * roomType.getNoOfRooms() * roomType.getNoOfAdults() * ( ( float ) r[2] ) * noOfNights ) / 100;
                                    searchResults.setPrice( roomPrice );
                                    searchResults.setNoOfAdults( ( int ) r[4] );
                                    searchResults.setNoOfRooms( ( int ) r[3] );
                                    searchResults.setType( ( String ) r[1] );
                                    searchResults.setRequiredRooms( roomType.getNoOfRooms() );
                                    searchResults.setRequiredCapacity( roomType.getNoOfAdults() );
                                    searchResults.setCity( ( String ) r[5] );
                                    finalOut.add( searchResults );
                                    //finalOutDup.add(searchResults);
                                }

                            } );

                        finalOut.sort( (SearchResults s1, SearchResults s2)->(int)(s1.getPrice()-s2.getPrice()) );
                        }
                );


            } );

            return finalOut;
        }
        else
            return null;

    }
}

