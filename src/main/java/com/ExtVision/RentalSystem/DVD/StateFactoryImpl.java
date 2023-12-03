package com.ExtVision.RentalSystem.DVD;

import org.springframework.stereotype.Component;

// StateFactoryImpl is an implementation of the StateFactory interface.
@Component
public class StateFactoryImpl implements StateFactory {

    // This method creates and returns an instance of AvailableState.
    @Override
    public State createAvailableState() {
        return new AvailableState();
    }

    // This method creates and returns an instance of RentedState.
    @Override
    public State createRentedState() {
        return new RentedState();
    }

    // This method creates a State instance based on the provided identifier.
    // It supports creating AvailableState and RentedState.
    @Override
    public State createState(String identifier) {
        // If the identifier is "AVAILABLE", it returns an AvailableState instance.
        if ("AVAILABLE".equals(identifier)) {
            return new AvailableState();
        } 
        // If the identifier is "RENTED", it returns a RentedState instance.
        else if ("RENTED".equals(identifier)) {
            return new RentedState();
        }
        // If the identifier does not match any known state, it throws an IllegalArgumentException.
        throw new IllegalArgumentException("Unknown state identifier: " + identifier);
    }
}