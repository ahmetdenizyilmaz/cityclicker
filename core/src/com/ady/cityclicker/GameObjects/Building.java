package com.ady.cityclicker.GameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by ADY on 14.6.2017.
 */

public class Building {
    public String name;
    public int id = 0;
    public String info = "info0001";
    public int production;
    public int worth;
    public Texture icon;

    public Building(int production,int worth, String name, String info) {
        //this.id = id;
        this.name = name;
        this.info=info;
        this.production=production;
        this.worth=worth;
        icon=  new Texture(Gdx.files.internal(name+".png"));
        icon.setFilter(Texture.TextureFilter.Linear,Texture.TextureFilter.Linear);
    }


}
