package com.example.suntravels.repository;

import com.example.suntravels.model.Contract;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ContractRepo extends CrudRepository<Contract,Long>, JpaSpecificationExecutor<Contract>
{
    //return details with the given contract id
    @Query( value = "select h.hotel_name,r.type,r.price,r.no_of_rooms,r.no_of_adults,h.city from hotel h JOIN contract c on h.h_id=c.hotel_id JOIN roomtype r on r.contract_id=c.c_id where c.c_id=:id ", nativeQuery = true )
    List<Object[]> findByCId( @Param( "id" ) Long cId );
}
