package com.ExtVision.RentalSystem.DVD;

public class RentedState implements State {
    @Override
    public void markAsRented(DVDGame dvdGame) {
        // Maybe add some logging if we have time
    }

    @Override
    public void markAsAvailable(DVDGame dvdGame) {
        // Change the state of the DVDGame to AvailableState
        dvdGame.setState(new AvailableState());
    }

    @Override
    public String toString() {
        return "RENTED";
    }
}
