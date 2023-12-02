package com.ExtVision.RentalSystem.Observer;

import com.ExtVision.RentalSystem.DVD.State;

public interface Observer {
    public void update(Observer observer, int itemID, State state);
}
