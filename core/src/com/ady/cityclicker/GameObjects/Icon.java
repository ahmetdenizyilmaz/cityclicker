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
    public Texture icontexture;
    private boolean selected = false;
   // private Rectangle area = new Rectangle(0, 0, 0, 0);

    public Icon(float x, float y, int size, FileHandle file) {
        super(x, y, size, size);
        icontexture = new Texture(file);
        icontexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
    }

    public int getSize() {
        return (int)area.getWidth();
    }

    public void toggleSelected() {
        selected = !selected;
    }

    public boolean getSelected() {
        return selected;
    }

}
