package com.ExtVision.RentalSystem.DVD;

public class StateFactoryImpl implements StateFactory {
    @Override
    public State createAvailableState() {
        return new AvailableState();
    }

    @Override
    public State createRentedState() {
        return new RentedState();
    }

    @Override
    public State createState(String identifier) {
        if ("AVAILABLE".equals(identifier)) {
            return new AvailableState();
        } else if ("RENTED".equals(identifier)) {
            return new RentedState();
        }
        throw new IllegalArgumentException("Unknown state identifier: " + identifier);
    }
}


