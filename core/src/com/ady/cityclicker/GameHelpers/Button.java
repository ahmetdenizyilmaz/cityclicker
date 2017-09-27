package com.ady.cityclicker.GameHelpers;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by ADY on 3.8.2017.
 */

public class Button {
    protected Vector2 position;
    protected Rectangle area=new Rectangle(0,0,0,0);


    public Button(float x, float y, int width, int height)
    {
        position=new Vector2(x,y);
        area.set(x,y,width,height);
    }

    public boolean contains(float x,float y)
    {
        return area.contains(x,y);
    }

    public void setPosition(float x,float y)
    {
        position.set(x,y);
        area.setPosition(x,y);
    }
    public void setSize(float xy)
    {
        area.setSize(xy);
    }
    public void setSize(float x,float y)
    {
        area.setSize(x,y);
    }
    public Vector2 getPosition() {
        return position;
    }

}
