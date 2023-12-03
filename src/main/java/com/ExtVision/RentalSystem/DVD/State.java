package com.ExtVision.RentalSystem.DVD;

public interface State {
    void markAsRented(DVDGame dvdGame);
    void markAsAvailable(DVDGame dvdGame);
}
