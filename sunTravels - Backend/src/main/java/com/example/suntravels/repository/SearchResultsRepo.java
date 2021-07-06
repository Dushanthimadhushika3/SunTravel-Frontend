package com.example.suntravels.repository;


import com.example.suntravels.model.Hotel;
import org.springframework.data.repository.CrudRepository;

public interface SearchResultsRepo extends CrudRepository<Hotel, Long>
{
    
}