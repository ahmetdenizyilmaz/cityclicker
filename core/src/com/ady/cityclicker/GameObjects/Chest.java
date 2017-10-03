package com.ady.cityclicker.GameObjects;


import com.badlogic.gdx.utils.Array;

/**
 * Created by ADY on 5.9.2017.
 */

public class Chest {

    private long itemnumber=0;

    public Chest() {
    }

    public void add() {
        itemnumber++;
    }

    public void add(long number) {
        itemnumber += number;
    }

    public void take(long number) {
            itemnumber -= number;
    }
    public long getCount()
    {
        return itemnumber;
    }
    public boolean isThere(long number)
    {
        return itemnumber>=number;
    }

}
