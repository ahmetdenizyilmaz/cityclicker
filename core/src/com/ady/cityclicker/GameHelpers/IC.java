package com.ady.cityclicker.GameHelpers;

import com.badlogic.gdx.utils.OrderedMap;
import com.badlogic.gdx.utils.OrderedSet;

/**
 * Created by ADY on 19.8.2017.
 */

public class IC {

    public long itemid = 0;
    public int number = 0;
    public int quality = 0;
    private transient ItemType item = new ItemType();


    public IC() {
    }


    public IC(long item, int number, int quality) {
        this();
        this.itemid = item;
        this.number = number;
        this.quality = quality;
    }

    public ItemType getItem() {
        return item;
    }

    public Long getItemid() {
        return itemid;
    }

    public int getNumber() {
        return number;
    }

    public void setItem(OrderedMap<Long, ItemType> items) {
     if(itemid<0) return;
        item = items.get(itemid);

    }

    public int getQuality() {
        return quality;
    }
}
