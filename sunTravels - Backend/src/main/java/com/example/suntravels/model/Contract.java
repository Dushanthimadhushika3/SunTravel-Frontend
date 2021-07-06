package com.example.suntravels.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;


@Getter
@Setter
@Data
@Entity
@Table( name = "contract" )
public class Contract implements Serializable
{

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long cId;

    @NotNull
    private int markup;
    private LocalDate endDate;
    private LocalDate startDate;

    @JsonIgnore
    @OneToMany( mappedBy = "contract", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL )
    private List<RoomType> roomTypes;

    @JsonIgnore
    @ManyToOne( fetch = FetchType.LAZY, optional = false )
    @JoinColumn( name = "hotel_id", nullable = false )
    private Hotel hotel;


    public Contract()
    {
    }

}
