package com.example.authorization.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Users extends BaseModel{
    private String email;
    private String password;
    @OneToMany(mappedBy = "user")
    private List<Sessions> sessionsList;

}
