package com.example.suntravels.repository;

import com.example.suntravels.dto.ContractDTO;
import com.example.suntravels.model.Contract;
import com.example.suntravels.model.RoomType;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomQueryRepoImp implements CustomQueryRepo
{
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<Contract> customQuery( ContractDTO contractDTO )
    {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Contract> query = criteriaBuilder.createQuery( Contract.class );
        //reference to join tables
        Root<Contract> contractRoot = query.from( Contract.class );
        //Join contract and roomtype tables - inner join
        Join<Contract,RoomType> joinedTable = contractRoot.join( "roomTypes", JoinType.INNER );

        //references to attribute paths
        Path<Integer> noOfAdultsPath = joinedTable.get( "noOfAdults" );
        Path<Integer> noOfRoomsPath = joinedTable.get( "noOfRooms" );
        Path<LocalDate> startDatePath = contractRoot.get( "startDate" );
        Path<LocalDate> endDatePath = contractRoot.get( "endDate" );


        List<Predicate> roomCriteriaList = new ArrayList<>();

        Integer noOfRooms = 0;
        //loop over room criteria
        for( RoomType rooms : contractDTO.getRoomTypeList() )
        {
            //no of adults predicate
            roomCriteriaList.add( criteriaBuilder.greaterThanOrEqualTo( noOfAdultsPath, rooms.getNoOfAdults() ) );
            //no of rooms predicate
            roomCriteriaList.add( criteriaBuilder.greaterThanOrEqualTo( noOfRoomsPath, rooms.getNoOfRooms() ) );
            noOfRooms += rooms.getNoOfRooms();
        }

        //checking date duration
        roomCriteriaList.add( criteriaBuilder.lessThanOrEqualTo( startDatePath, contractDTO.getStartDate() ) );
        roomCriteriaList.add( criteriaBuilder.greaterThanOrEqualTo( endDatePath, contractDTO.getEndDate() ) );
        query.select( contractRoot ).distinct( true )
             .where( criteriaBuilder.and( roomCriteriaList.toArray( new Predicate[0] ) ) );

        //group by contract id
        query.groupBy( contractRoot.get( "cId" ) );
        query.having( criteriaBuilder.greaterThanOrEqualTo( criteriaBuilder.sum( noOfRoomsPath ), noOfRooms ) );

        return entityManager.createQuery( query ).getResultList();

    }
}

