package com.example.suntravels.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table( name = "roomtype" )
public class RoomType implements Serializable
{

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long roomTId;

    private int noOfRooms;
    private int noOfAdults;
    private float price;
    private String type;

    @JsonIgnore
    @ManyToOne( fetch = FetchType.LAZY, optional = false )
    @JoinColumn( name = "contract_id", nullable = false )
    private Contract contract;

    public RoomType()
    {
    }


}
