package com.ather.model;

import java.security.SecureRandom;
import java.util.List;

public class BotPlayer implements Player{

    private final SecureRandom random = new SecureRandom();

    public Board move(Board input) {
        List<Tile> emptyTiles = input.emptyTiles();


        int indexToPlace = random.nextInt(emptyTiles.size());
        Tile tileToPlace = emptyTiles.get(indexToPlace);

        return input.newNumber(tileToPlace, (System.currentTimeMillis() % 2 == 0
                && (System.currentTimeMillis() / 10) > 5) ? 4 : 2);
    }
}