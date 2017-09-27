package com.ady.cityclicker.GameHelpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.OrderedMap;

/**
 * Created by ADY on 30.8.2017.
 */

public class ItemTypeParser {
    private OrderedMap<Long, ItemType> items;


    public ItemTypeParser() {
        items = new OrderedMap<Long, ItemType>();
    }


    public void write() {
         read();
        Json json = new Json();
        //0 resource
        //1 club
        //2shield
        //3armor
        //4helm


        long itemid = 3;
        int itemtype = 3;
        String name = "Hard Hat";
        String info = "Basic helm";
        Stat[] stats = new Stat[3];
        stats[0]=new Stat();
        stats[0].name = "Power";
        stats[0].stat = 0;
        stats[1]=new Stat();
        stats[1].name = "Defence";
        stats[1].stat = 1;
        stats[2]=new Stat();
        stats[2].name = "Time";
        stats[2].stat = 12;
        IC[] ingredients = new IC[1];
        ingredients[0]=new IC();
        ingredients[0].itemid = 0;
        ingredients[0].number = 1;
        ingredients[0].quality = 0;
        // ingredients[1].itemid=0;
        //  ingredients[1].number=0;
        // ingredients[1].quality=0;
        ItemType newitem = new ItemType(itemid, itemtype, name, info, stats, ingredients);

        items.put(itemid, newitem);

      //  FileHandle file = Gdx.files.local("items.json");
      //  file.writeString(json.prettyPrint(items.values().toArray()), false);


    }

    public OrderedMap<Long, ItemType> read() {
        Json json = new Json();
        JsonReader jsonreader = new JsonReader();
        JsonValue base = jsonreader.parse(Gdx.files.internal("items.json"));
        //  json.readFields(items,base);


        for (JsonValue itemvalue : base) {
            ItemType temp = json.readValue(ItemType.class, itemvalue);
            items.put(temp.getItemid(), temp);
            System.out.println(temp.getName() + "ismi" + temp.getPower());
        }

        // items.putAll(json.fromJson(OrderedMap.class, Gdx.files.local("items.json")));
        // System.out.println(items.get(1l).getIngredients().get(0));

        ObjectMap.Values<ItemType> itemiterator = items.values();
        while (itemiterator.hasNext()) {
            ItemType temptype = itemiterator.next();
            temptype.init();
            temptype.setConnections(items);

        }


        return items;

    }
}
