package com.ExtVision.RentalSystem.Observer;


public interface Observer {
    public void update(Observer observer, int itemID, State state);
}
