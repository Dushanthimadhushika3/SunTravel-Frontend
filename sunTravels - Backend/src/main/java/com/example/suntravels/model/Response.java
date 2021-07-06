package com.example.suntravels.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Response
{
    String status;
    String message;
    Object payload;

    public Response(String status, String message, Object payload) {
        super();
        this.status = status;
        this.message = message;
        this.payload = payload;
    }
}
