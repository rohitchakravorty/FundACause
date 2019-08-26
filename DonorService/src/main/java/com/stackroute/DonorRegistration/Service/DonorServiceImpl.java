package com.stackroute.DonorRegistration.Service;

import com.stackroute.DonorRegistration.Domain.Donor;
import com.stackroute.DonorRegistration.Exceptions.DonorAlreadyExistsException;
import com.stackroute.DonorRegistration.Exceptions.DonorNotFoundException;
import com.stackroute.DonorRegistration.Repository.DonorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DonorServiceImpl implements DonorService {

    private DonorRepository donorRepository;

    @Autowired
    public DonorServiceImpl(DonorRepository donorRepository) {
        this.donorRepository = donorRepository;
    }

    /* saves a new donor */
    @Override
    public Donor saveNewDonor(Donor donor) throws DonorAlreadyExistsException {
//        if(donorRepository.existsById(donor.getId())){
//            throw new DonorAlreadyExistsException("Donor already exists!");
//        }
        Donor newDonor = donorRepository.save(donor);
        return newDonor;
    }

    /* retrieves all donors */
    @Override
    public List<Donor> getAllDonors()  throws DonorNotFoundException {
        List<Donor> donorList = donorRepository.findAll();
        if(donorList.isEmpty()){
            throw new DonorNotFoundException("Donors not found");
        }
        return donorList;
    }

    @Override
    public List<Donor> deleteById(String id) throws DonorNotFoundException {
       Optional<Donor> donorId = donorRepository.findById(id);
        if(donorId.isEmpty()){
           throw new DonorNotFoundException("Donor not found!");
        }
        donorRepository.deleteById(id);
        return donorRepository.findAll();
    }
    @Override
    public Donor updateById(Donor donor) throws DonorNotFoundException {
       Optional<Donor> userOptional = donorRepository.findById(donor.getId());

        if(userOptional.isEmpty()){
            throw new DonorNotFoundException("Donor not found!");
        }

       donor.setId(donor.getId());

        donorRepository.save(donor);
        return userOptional.get();

    }


    @Override
    public List<Donor> getByName(String name) throws DonorNotFoundException{
        List<Donor> donorId = donorRepository.findByName(name);
        if(donorId.isEmpty()){
            throw new DonorNotFoundException("Donor(s) not found!");
        }
        return donorId;
    }
}
