package com.example.authorization.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Sessions extends BaseModel {

    private String token;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;


}
