package com.example.suntravels.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Getter
@Setter
@Entity
@Table( name = "hotel" )
public class Hotel implements Serializable
{

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long hId;

    @NotNull
    private String hotelName;
    private String address1;
    private String state;
    private String city;
    private int zipCode;
    private String email;

    private String address2;

    @JsonIgnore
    @OneToMany( mappedBy = "hotel", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL )
    private List<Contract> contracts;

    @NotNull
    @ElementCollection( fetch = FetchType.EAGER )
    @CollectionTable( name = "contactNo" )
    private List<ContactNo> contacts;

    public Hotel()
    {
    }

    public Hotel( String hotelName, String address1, String address2, String state, String city, int zipCode, String email )
    {
        this.hotelName = hotelName;
        this.address1 = address1;
        this.address2 = address2;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.email = email;
    }

}

