package com.ady.cityclicker.GameHelpers;

import com.ady.cityclicker.GameObjects.Chest;
import com.ady.cityclicker.GameObjects.Content;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonWriter;
import com.badlogic.gdx.utils.OrderedMap;
import com.badlogic.gdx.utils.OrderedSet;

/**
 * Created by ADY on 18.8.2017.
 */

public class ItemType {

    private long id;
    private transient final int statcount = 3;
    private int itemtype;
    private String name;
    private String info = "info0001";
    private transient Texture itemtexture;
    private transient Content owner;
    public transient Chest chest = new Chest();
    public Stat[] stats = new Stat[statcount];
    private Array<IC> ingredients = new Array<IC>();


    public ItemType() {
        //   ingredients = new Array<IC>();
    }

    /**
     * @param itemid
     * @param name
     * @param info
     * @param ingredients
     */
    public ItemType(long itemid, int itemtype, String name, String info, Stat[] stats, IC... ingredients) {

        this.id = itemid;
        this.itemtype = itemtype;
        this.name = name;
        this.info = info;
        this.stats = stats;
        this.ingredients = new Array<IC>();

        for (IC i : ingredients) {
            this.ingredients.add(i);
        }
    }

    public void init() {
        itemtexture = new Texture(Gdx.files.internal("items/" + id + ".png"));
        itemtexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
    }

    public Array<IC> getIngredients() {
        return ingredients;
    }

    public Texture getTexture() {
        return itemtexture;
    }

    public String getName() {
        return name;
    }

    public int getItemtype() {
        return itemtype;
    }

    public long getItemid() {
        return id;
    }

    public int getStatnumber() {
        int a = 0;
        for (int i = 0; i < statcount; i++) {
            a += stats[0].getStat() > 0 ? 1 : 0;
        }
        return a;
    }

    public void setConnections(OrderedMap<Long, ItemType> items) {
        System.out.println(ingredients.size);
        for (IC i : ingredients) {

            i.setItem(items);

        }
    }

    public boolean isThereEnoughMaterial() {
        boolean temp = true;
        for (IC i : ingredients) {
            if (!i.getItem().chest.isThere(i.getNumber())) {
                temp = false;
            }
        }
        System.out.println(name + "all yeterli   " + temp);
        return temp;
    }

    public boolean isResource() {
        return ingredients.size == 0;
    }

    public void takeMaterials() {
        for (IC i : ingredients) {
            i.getItem().chest.take(i.getNumber());
        }
    }

    public void dispose() {
        itemtexture.dispose();
    }

    public void resume() {
        itemtexture = new Texture(Gdx.files.internal("items/" + id + ".png"));
        itemtexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
    }


    public float getPower() {
        return stats[0].getStat();
    }

    public float getDefence() {
        return stats[1].getStat();
    }

    public float getTime() {
        return stats[2].getStat() * 60;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof ItemType)) return false;
        ItemType itemobject = (ItemType) other;
        return itemobject.getItemid() == id;
    }

    public void setOwner(Content owner) {
        this.owner=owner;
    }

    public Content getOwner() {
        return owner;
    }
}
