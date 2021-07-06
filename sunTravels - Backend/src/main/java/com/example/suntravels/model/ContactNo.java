package com.example.suntravels.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Embeddable
public class ContactNo implements Serializable
{

    private String contactNo;

    public ContactNo() {
    }

    public ContactNo(String contactNo) {
        this.contactNo = contactNo;
    }
}
