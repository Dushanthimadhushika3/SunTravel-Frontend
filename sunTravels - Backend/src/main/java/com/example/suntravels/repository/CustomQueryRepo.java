package com.example.suntravels.repository;

import com.example.suntravels.dto.ContractDTO;
import com.example.suntravels.model.Contract;

import java.util.List;

public interface CustomQueryRepo
{
    public List<Contract> customQuery( ContractDTO contractDTO );
}
