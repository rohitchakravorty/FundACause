package com.stackroute.DonorRegistration.Domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Donor {

    @Id
    private String id;
    private String name;
    @Transient private String email;
    @Transient private String password;
    private long phoneNumber;


}