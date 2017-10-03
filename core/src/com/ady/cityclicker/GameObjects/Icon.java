package com.ady.cityclicker.GameObjects;

import com.ady.cityclicker.GameHelpers.Button;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by ADY on 26.8.2017.
 */


public class Icon extends Button {
    public Texture currenticontexture;
    public Texture icontexture;
    public Texture icontexture2;
    public long id = 0;
    private boolean selected = false;
    private boolean doubletexture = false;
    private static Icon trueToggle = null;
    // private Rectangle area = new Rectangle(0, 0, 0, 0);

    public Icon(float x, float y, int size, FileHandle file) {
        super(x, y, size, size);
        currenticontexture = new Texture(file);
        currenticontexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
    }

    public Icon(float x, float y, int size, FileHandle file, FileHandle file2) {
        super(x, y, size, size);
        icontexture = new Texture(file);
        icontexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        currenticontexture = icontexture;
        icontexture2 = new Texture(file2);
        icontexture2.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        doubletexture = true;
    }

    public Icon(float x, float y, int size, FileHandle file, FileHandle file2, long id) {
        this(x, y, size, file, file2);
        this.id = id;

    }

    public int getSize() {
        return (int) area.getWidth();
    }

    public void toggleSelected() {
        selected = !getSelected();

        if(trueToggle!= null && trueToggle!=this && trueToggle.doubletexture)
        {
            trueToggle.currenticontexture=trueToggle.icontexture;
        }


        if (selected) {
            trueToggle = this;
        } else {
            trueToggle = null;
        }

        if (doubletexture) {

            currenticontexture = getSelected() ? icontexture2 : icontexture;
        }
        else
        {
            //trueToggle.currenticontexture=trueToggle.icontexture;
        }

    }


    public boolean getSelected() {
        return trueToggle == this;
    /*    if (doubletexture) {
            return trueToggle == this;
        } else {
            return selected;
        }*/
    }

}
