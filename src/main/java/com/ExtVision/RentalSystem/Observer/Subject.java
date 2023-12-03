package com.ExtVision.RentalSystem.Observer;

public interface Subject {
    public void registerObserver(Observer observer);
    public void removeObserver(Observer observer);
    public void notifyObservers();
    public void markAsAvailable();
    public void markAsRented();
}
