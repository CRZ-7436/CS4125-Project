package com.ExtVision.RentalSystem.DVD;

public class RentedState implements State {
    @Override
    public void markAsRented(DVDGame dvdGame) {
        // Implementation for marking as rented
    }

    @Override
    public void markAsAvailable(DVDGame dvdGame) {
        // Implementation for marking as available
    }

    @Override
    public String toString() {
        return "RENTED";
    }
}
