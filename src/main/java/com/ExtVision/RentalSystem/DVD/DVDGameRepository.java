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

    Optional<DVDGame> findByItemID(Integer itemID);

    @Override
    void deleteById(Integer itemID);
}
