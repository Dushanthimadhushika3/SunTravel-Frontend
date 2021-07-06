package com.example.suntravels.dto;

import com.example.suntravels.model.ContactNo;
import com.example.suntravels.model.RoomType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class ContractDTO
{
    private int markup;
    private LocalDate endDate;
    private LocalDate startDate;
    private Long hotelId;
    private String address1;
    private String address2;
    private String state;
    private String city;
    private int zipCode;
    private String hotelName;
    private String email;
    private List<ContactNo> contactNoList;
    private List<RoomType> roomTypeList;


}
