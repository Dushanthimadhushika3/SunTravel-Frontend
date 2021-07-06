package com.example.suntravels.dto;

import com.example.suntravels.model.ContactNo;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class HotelDTO
{
    private Long hotelId;
    private String address1;
    private String state;
    private String city;
    private int zipCode;
    private String hotelName;
    private String email;
    private String address2;
    private List<ContactNo> contactNoList;

}
