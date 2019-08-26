package com.stackroute.DonorRegistration.Repository;

import com.stackroute.DonorRegistration.Domain.Donor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DonorRepository extends MongoRepository<Donor, String> {

    /* query to get donor by name */
    @Query("{name:'?0'}")
    List<Donor> findByName(String name);

}
