package com.example.suntravels.repository;


import com.example.suntravels.model.Contract;
import com.example.suntravels.model.RoomType;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RoomTypeRepo extends CrudRepository<RoomType, Long>
{

}