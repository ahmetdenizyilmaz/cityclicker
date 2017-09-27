package com.ady.cityclicker.GameHelpers;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by ADY on 31.8.2017.
 */

public class InfoBox {
    private float width;
    private float height;
    public Rectangle box;
    public Vector2 position;
    public String message;
    private boolean show=false;
    public InfoBox()
    {
        position=new Vector2(0,0);
        height=0;
        width=0;
    }
    public InfoBox(float x,float y,float width,float height)
    {
        position=new Vector2(x,y);
        this.width=width;
        this.height=height;
    }
}
