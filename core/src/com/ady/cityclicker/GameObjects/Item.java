package com.ady.cityclicker.GameObjects;

import com.ady.cityclicker.GameHelpers.ItemType;

/**
 * Created by ADY on 5.9.2017.
 */

public class Item {
    private ItemType itemtype;
    private int quality;


    public Item(ItemType itemtype,int quality)
    {
        this.itemtype=itemtype;
        this.quality=quality;
    }
}
