package com.ExtVision.RentalSystem.DVDTest;

import com.ExtVision.RentalSystem.DVD.DVDGame;
import com.ExtVision.RentalSystem.DVD.State;
import com.ExtVision.RentalSystem.DVD.StateFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class DVDGameTest {

    @Mock
    private StateFactory stateFactory;

    @Mock
    private State availableState;

    @Mock
    private State rentedState;

    private DVDGame dvdGame;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(stateFactory.createState("AVAILABLE")).thenReturn(availableState);
        when(stateFactory.createState("RENTED")).thenReturn(rentedState);

        // Simulate state transitions
        doAnswer(invocation -> {
            DVDGame game = invocation.getArgument(0);
            game.setState(rentedState);
            return null;
        }).when(availableState).markAsRented(any(DVDGame.class));

        doAnswer(invocation -> {
            DVDGame game = invocation.getArgument(0);
            game.setState(availableState);
            return null;
        }).when(rentedState).markAsAvailable(any(DVDGame.class));

        dvdGame = new DVDGame(1, "Test Game", "Action", stateFactory);
    }

    @Test
    void testInitialState() {
        assertEquals("AVAILABLE", dvdGame.getStateIdentifier());
        assertEquals(availableState, dvdGame.getState());
    }

    @Test
    void testMarkAsRented() {
        dvdGame.markAsRented();
        verify(availableState).markAsRented(dvdGame);
        assertEquals("rentedState", dvdGame.getStateIdentifier());
        assertEquals(rentedState, dvdGame.getState());
    }

    @Test
    void testMarkAsAvailable() {
        dvdGame.setState(rentedState);
        dvdGame.markAsAvailable();
        verify(rentedState).markAsAvailable(dvdGame);
        assertEquals("availableState", dvdGame.getStateIdentifier());
        assertEquals(availableState, dvdGame.getState());
    }

    @Test
    void testCreateDVDGame() {
        DVDGame dvdGame = new DVDGame(1, "Test Game", "Action", stateFactory);

        assertNotNull(dvdGame);
        assertEquals(1, dvdGame.getItemID());
        assertEquals("Test Game", dvdGame.getTitle());
        assertEquals("Action", dvdGame.getGenre());
        assertNotNull(dvdGame.getState());
        assertEquals("AVAILABLE", dvdGame.getStateIdentifier());
    }

    @Test
    void testEditDVDGame() {
        DVDGame dvdGame = new DVDGame(1, "Original Title", "Action", stateFactory);

        dvdGame.setTitle("New Title");
        dvdGame.setGenre("Adventure");

        assertEquals("New Title", dvdGame.getTitle());
        assertEquals("Adventure", dvdGame.getGenre());
    }

    // Additional tests to be be added for other methods and scenarios
}
