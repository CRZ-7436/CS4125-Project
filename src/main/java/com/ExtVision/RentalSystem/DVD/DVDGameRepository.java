package com.ExtVision.RentalSystem.DVD;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DVDGameRepository extends JpaRepository<DVDGame, Integer> {
    List<DVDGame> findByGenre(String genre);
    List<DVDGame> findByStateIdentifier(String stateIdentifier);
    List<DVDGame> findByTitleContaining(String title);

    // Use this if itemID is the primary key or a unique field
    Optional<DVDGame> findByItemID(Integer itemID);

    // Keep this as is if itemID is the primary key
    @Override
    void deleteById(Integer itemID);
}