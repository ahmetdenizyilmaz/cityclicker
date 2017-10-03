package com.ady.cityclicker.GameHelpers;


import com.badlogic.gdx.utils.Array;

import java.util.Objects;

/**
 * Created by ADY on 26.7.2017.
 */




final public class RecipeIngredients  {

    public transient Array<IC> recipeitems;
    public Array<ICwrite> recipeitemswrite;

    public RecipeIngredients(ICwrite...recipeitems)
    {
        this.recipeitemswrite=new Array<ICwrite>();
        for(ICwrite i:recipeitems)
        {
            this.recipeitemswrite.add(i);
        }
    }

}
