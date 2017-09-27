package com.ady.cityclicker.GameObjects;


import com.badlogic.gdx.utils.Array;

/**
 * Created by ADY on 5.9.2017.
 */

public class Chest {

    private Array<Item> chest = new Array<Item>();

    public Chest() {
    }

    public void add(Item item) {
        chest.add(item);
    }

    public Array<Item> getChest() {
        return chest;
    }

}
