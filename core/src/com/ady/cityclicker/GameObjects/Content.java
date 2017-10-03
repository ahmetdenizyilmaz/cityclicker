package com.ady.cityclicker.GameObjects;

import com.ady.cityclicker.GameHelpers.GameResourceController;
import com.ady.cityclicker.GameHelpers.IC;
import com.ady.cityclicker.GameHelpers.ItemType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

/**
 * Created by ADY on 15.8.2017.
 */

public abstract class Content {
    protected NinePatch npimage;
    protected Vector2 position;
    protected Vector2 parentalposition = new Vector2(0, 0);
    protected int height, width;
    protected Table parent;
    protected boolean touchable = false;
    protected String name;
    protected long uniqueid;
    protected static long id;
    protected GameResourceController gameresource;
    protected Table owner;


    public Content(float x, float y, int width, int height) {
        position = new Vector2(x, y);
        this.width = width;
        this.height = height;
        //  this.name = name;
        uniqueid = generateId();
        npimage = new NinePatch(new Texture(Gdx.files.internal("ninepatchcontent2.png")), 20, 20, 20, 34);


        npimage.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
    }

    public Content(float x, float y, int width, int height, boolean touchable) {
        this(x, y, width, height);
        this.touchable = touchable;
    }

    public long generateId() {
        return id++;
    }

    public void setPosition(float x, float y) {
        position.set(x, y);
    }

    public Vector2 getGlobalPosition() {
        return position.cpy().add(parentalposition);
    }

    public Vector2 getLocalPosition() {
        return position;
    }

    public void setTouchable(boolean touch) {
        touchable = touch;
    }

    public abstract void touchPoint(float xx, float yy);

    public void setParentalPosition(Vector2 vector2) {
        parentalposition.set(vector2);
    }

    public void drawself(SpriteBatch batch) {
        float drawpositionx = parentalposition.cpy().add(position).x;
        float drawpositiony = parentalposition.cpy().add(position).y;
        batch.begin();
        //  batch.setColor(new Color(0.59f,0.19f,0.15f,1f));
        npimage.draw(batch, drawpositionx, drawpositiony, width, height);
        // batch.setColor(Color.WHITE);
        batch.end();
    }

    public abstract void draw(SpriteBatch batch);

    public abstract Content clone();

    public void act() {
    }

    public abstract ItemType getItem();

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Content)) {
            return false;
        }
        Content that = (Content) obj;
        return that.uniqueid == this.uniqueid;
    }

    public abstract void dispose();


    public abstract void resume();

    public void setGameResource(GameResourceController gameresource) {
        this.gameresource = gameresource;
    }

    public void allRequired(Array<Content> list) {
        if (!list.contains(this, false)) {
            list.add(this);
        }
        for (IC i : this.getItem().getIngredients()) {
            if (!list.contains(i.getItem().getOwner(), false)) {
                list.add(i.getItem().getOwner());
                i.getItem().getOwner().allRequired(list);
            }
        }


    }

    public void setOwner(Table owner) {
        this.owner = owner;
    }
}
