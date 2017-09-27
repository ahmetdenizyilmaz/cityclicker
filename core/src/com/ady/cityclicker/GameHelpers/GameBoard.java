package com.ady.cityclicker.GameHelpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

import static com.ady.cityclicker.GameHelpers.Constants.*;

/**
 * Created by ADY on 27.7.2017.
 */
/*
public class GameBoard {

    private final Texture boardbg;
    private  Texture bg;
    private Texture empty;
    private int size = 4;
    public int width;

    public int x;
    public int y;
    private float itemwidth;
    public RecipeIngredients[][] array = new RecipeIngredients[8][8];

    public GameBoard(int x, int y, int width,int size) {
        this.x = x;
        this.y = y;
        this.size=size;
        this.width = width;
        itemwidth=width/(float)size;
        empty = new Texture(Gdx.files.internal("Empty.png"));
        bg = new Texture(Gdx.files.internal("WoodBackground.png"));
        empty.setFilter(Texture.TextureFilter.Linear,Texture.TextureFilter.Linear);
        boardbg = new Texture(Gdx.files.internal("Board0Background.png"));
        boardbg.setFilter(Texture.TextureFilter.Linear,Texture.TextureFilter.Linear);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
               // array[i][j] = new RecipeIngredients(0, 0, 0, 0, 0);

            }
        }

      array[1][3] = new RecipeIngredients(1, 0, 0, 0, 0);
        array[1][1] = new RecipeIngredients(1, 0, 0, 0, 0);
    }
    public void changeSize(int size)
    {

        this.size=size;
        itemwidth=width/(float)size;
    }
    public void up() {
        boolean[][] combines = new boolean[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                combines[i][j] = false;
            }

        }
        for (int k = 0; k < size; k++) {
            for (int i = 0; i < size; i++) {
                for (int j = size - 2; j >= 0; j--) {
                    if (array[i][j + 1].isEmpty()) {
                        array[i][j + 1] = array[i][j];
                        array[i][j] = array[i][j].clear();

                    }
                }
            }
        }
        for (int k = 0; k < size; k++) {
            for (int i = 0; i < size; i++) {
                for (int j = size - 2; j >= 0; j--) {
                    if (buildings.containsKey(array[i][j + 1].add(array[i][j])) && !combines[i][j]) {
                        System.out.println("ady" + i + ".." + j + array[i][j + 1].isEmpty());
                        array[i][j + 1] = array[i][j + 1].add(array[i][j]);
                        array[i][j] = array[i][j].clear();
                        combines[i][j + 1] = true;
                    }
                }
            }
        }
        putrandomnumber();


    }

    public void down() {
        boolean[] combines = new boolean[8];
        for (int i = 0; i < 8; i++) {
            combines[i] = false;
        }
        for (int k = 0; k < size; k++) {
            for (int i = 0; i < size; i++) {
                for (int j = 1; j < size; j++) {
                    if (array[i][j - 1].isEmpty()) {
                        array[i][j - 1] = array[i][j];
                        array[i][j] = array[i][j].clear();
                    } else if (buildings.containsKey(array[i][j - 1].add(array[i][j])) && !combines[i]) {
                        array[i][j - 1] = array[i][j - 1].add(array[i][j]);
                        array[i][j] = array[i][j].clear();
                        combines[i] = true;
                    }
                }
            }
        }
        putrandomnumber();
    }

    public void right() {
        boolean[] combines = new boolean[8];
        for (int i = 0; i < 8; i++) {
            combines[i] = false;
        }
        for (int k = 0; k < size; k++) {
            for (int i = size - 2; i >= 0; i--) {
                for (int j = 0; j < size; j++) {
                    if (array[i + 1][j].isEmpty()) {
                        array[i + 1][j] = array[i][j];
                        array[i][j] = array[i][j].clear();
                    } else if (buildings.containsKey(array[i + 1][j].add(array[i][j])) && !combines[j]) {
                        array[i + 1][j] = array[i + 1][j].add(array[i][j]);
                        array[i][j] = array[i][j].clear();
                        combines[j] = true;
                    }
                }
            }
        }

        putrandomnumber();
    }

    public void left() {
        boolean[] combines = new boolean[8];
        for (int i = 0; i < 8; i++) {
            combines[i] = false;
        }
        for (int k = 0; k < size; k++) {
            for (int i = 1; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (array[i - 1][j].isEmpty()) {
                        array[i - 1][j] = array[i][j];
                        array[i][j] = array[i][j].clear();
                    } else if (buildings.containsKey(array[i - 1][j].add(array[i][j])) && !combines[j]) {
                        array[i - 1][j] = array[i - 1][j].add(array[i][j]);
                        array[i][j] = array[i][j].clear();
                        combines[j] = true;
                    }
                }
            }
        }
        putrandomnumber();
    }

    private void putrandomnumber() {
        int a, b;
        a = MathUtils.random(size - 1);
        b = MathUtils.random(size - 1);
        int c = 0;
        while (array[a][b].isNotEmpty() && c < 1000) {
            a = MathUtils.random(size - 1);
            b = MathUtils.random(size - 1);
            c++;
        }
        if (c >= 1000) {
            changeSize(size+1);
        }
        System.out.println(a + "___" + b);
        array[a][b] = keys.get(0);
        System.out.println(array[a][b]);

    }

    public void drawBoard(SpriteBatch batch) {

        batch.begin();
        batch.draw(bg,0,0);
        batch.draw(boardbg,x-12,y-12,width+24,width+24);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (array[i][j].isNotEmpty()) {

                   batch.draw(buildings.get(array[i][j]).icon, x + i * itemwidth , y + j * itemwidth, itemwidth, itemwidth);
                } else {
                    batch.draw(empty, x + i * itemwidth, y + j * itemwidth,itemwidth,itemwidth);
                }
            }
        }
        batch.end();
    }


    public void dispose() {
      //  empty.dispose();
    }
}*/
