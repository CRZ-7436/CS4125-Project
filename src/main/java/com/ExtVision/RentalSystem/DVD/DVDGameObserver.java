package com.ExtVision.RentalSystem.DVD;

public interface DVDGameObserver {
    void update(DVDGame dvdGame);
}

public class DVDGameService {
    private List<DVDGameObserver> observers = new ArrayList<>();

    public void registerObserver(DVDGameObserver observer) {
        observers.add(observer);
    }

    public void notifyObservers(DVDGame dvdGame) {
        for (DVDGameObserver observer : observers) {
            observer.update(dvdGame);
        }
    }

    // Other service methods like save, delete, etc.
}
