package com.ExtVision.RentalSystem.DVD;

import org.springframework.stereotype.Component;

@Component
public interface StateFactory {
    State createAvailableState();
    State createRentedState();
    State createState(String identifier);
}