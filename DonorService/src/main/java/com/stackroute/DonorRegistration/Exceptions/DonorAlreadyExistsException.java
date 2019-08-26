package com.stackroute.DonorRegistration.Exceptions;

/*Exception thrown when Donor already Exists*/
public class DonorAlreadyExistsException  extends Exception{
    public DonorAlreadyExistsException(String message) {

        // Call constructor of parent Exception
        super(message);

    }
}