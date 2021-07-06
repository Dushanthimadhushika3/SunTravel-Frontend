package com.example.suntravels.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchResults
{

    private String hotelName;
    private int noOfRooms;
    private int noOfAdults;
    private float price;
    private String type;
    private int requiredRooms;
    private int requiredCapacity;
    private String city;


}
