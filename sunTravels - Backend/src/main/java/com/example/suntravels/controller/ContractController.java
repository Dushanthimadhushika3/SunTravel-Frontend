package com.example.suntravels.controller;

import com.example.suntravels.dto.ContractDTO;
import com.example.suntravels.dto.SearchResults;
import com.example.suntravels.model.Hotel;
import com.example.suntravels.model.Response;
import com.example.suntravels.repository.ContractRepo;
import com.example.suntravels.repository.HotelRepo;
import com.example.suntravels.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
@CrossOrigin( origins = "*", allowedHeaders = "*" )
@RequestMapping( "/contracts" )
public class ContractController
{
    private static final Logger logger = LoggerFactory.getLogger( ContractController.class );
    @Autowired
    ContractService contractService;
    @Autowired
    HotelRepo hotelRepo;


    /**
     * add new contract for a new hotel
     * save both contract and hotel details
     *
     * @param newContract
     * @return response mgs as Contract Save Successfully on success with saved contract details
     */
    @PostMapping( "/newContractHotel" )
    public Response addNewContractHotel( @RequestBody ContractDTO newContract )
    {
        Hotel hotel = hotelRepo.findByHotelName( newContract.getHotelName() );

        if( hotel == null )
        {
            logger.info( "New contract added with new hotel" );
            return contractService.addNewContractHotel( newContract );
        }
        else
        {
            logger.warn( "Hotel already exist with given hotel name" );
            return new Response( "Failed", "Hotel already exist", null );
        }
    }

    /**
     * add new contract for existing hotel
     *
     * @param newContract
     * @return response mgs as Contract Save Successfully on success with saved contract details
     * response mgs as Hotel does not exist on fail
     */
    @PostMapping( "/newContract" )
    public Response addNewContract( @RequestBody ContractDTO newContract )
    {
        Hotel hotel = hotelRepo.findByHotelName( newContract.getHotelName() );

        if( hotel != null )
        {
            logger.info( "New contract added for an existing hotel" );
            return contractService.addNewContract( newContract, hotel );
        }
        else
        {
            logger.warn( "Hotel with the given hotel name does not exist" );
            return new Response( "Failed", "Hotel does not exist", null );
        }

    }

    /**
     * search for hotels as per the client requirements
     *
     * @param contract
     * @return hotel details with prices and room types
     */
    @PostMapping( "/hotelDetails" )
    public List<SearchResults> getResults( @RequestBody ContractDTO contract )
    {
        logger.info( "Return search results" );
        return contractService.getResults( contract );
    }

}
