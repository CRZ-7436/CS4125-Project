package com.ExtVision.RentalSystem.DVD;

public class AvailableState implements State {
    @Override
    public void markAsRented(DVDGame dvdGame) {
        // Change the state of the DVDGame to RentedState
        dvdGame.setState(new RentedState());
    }

    @Override
    public void markAsAvailable(DVDGame dvdGame) {
        // Maybe add some logging if we have time
    }

    @Override
    public String toString() {
        return "AVAILABLE";
    }
}
