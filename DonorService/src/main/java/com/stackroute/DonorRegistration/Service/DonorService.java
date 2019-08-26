package com.stackroute.DonorRegistration.Service;

import com.stackroute.DonorRegistration.Domain.Donor;
import com.stackroute.DonorRegistration.Exceptions.DonorAlreadyExistsException;
import com.stackroute.DonorRegistration.Exceptions.DonorNotFoundException;

import java.util.List;

public interface DonorService {
    public Donor saveNewDonor(Donor donor) throws DonorAlreadyExistsException;

    public List<Donor> getAllDonors() throws DonorNotFoundException;

    public List<Donor> deleteById(String id) throws DonorNotFoundException;

    public Donor updateById(Donor donor) throws DonorNotFoundException;

    public List<Donor> getByName(String name) throws DonorNotFoundException;

}
