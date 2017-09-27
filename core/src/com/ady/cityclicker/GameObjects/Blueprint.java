package com.ady.cityclicker.GameObjects;


import com.ady.cityclicker.GameHelpers.IC;
import com.ady.cityclicker.GameHelpers.InfoBox;
import com.ady.cityclicker.GameHelpers.ItemType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;


/**
 * Created by ADY on 15.8.2017
 */

public class Blueprint extends Content {

    private BitmapFont font;
    private BitmapFont font1;
    private Texture icon;
    private Texture[] resourceicons = new Texture[1];
    private float[] statcoef = new float[3];
    private Texture elapsedtimeback;
    private InfoBox[] infobox = new InfoBox[10];
    private Texture[] staticons = new Texture[3];
    private NinePatch innerframe;
    private NinePatch textback;
    private ItemType item;
    private float done = 0;
    private Icon minusicon;
    private Icon plusicon;
    private Texture workericon;
    private int workercount = 0;
    private int maxworker = 6;
    private boolean started = false;

    public Blueprint(float x, float y, int width, int height, ItemType item) {
        super(x, y, width, height);
        init(item);
    }

    public Blueprint(float x, float y, int width, int height, ItemType item, boolean touchable) {
        super(x, y, width, height, touchable);
        init(item);
    }

    private void init(ItemType item) {
        this.item = item;
        for (int i = 0; i < item.getIngredients().size; i++) {
            infobox[i] = new InfoBox(0, 0, 30f, 30f);
        }
        for (int i = 0; i < 3; i++) {
            statcoef[i] = 1f;
        }
        name = item.getName();
        icon = item.getTexture();
        for (int i = 0; i < 3; i++) {
            staticons[i] = new Texture(Gdx.files.internal("stats/stat" + i + ".png"));
            staticons[i].setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        }

        //font = new BitmapFont(Gdx.files.internal("font0.fnt"));
        // font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);


        Texture texture = new Texture(Gdx.files.internal("font0.png"), true);
        texture.setFilter(Texture.TextureFilter.MipMapLinearLinear, Texture.TextureFilter.Linear);
        font = new BitmapFont(Gdx.files.internal("font0.fnt"), new TextureRegion(texture), false);
        font.setUseIntegerPositions(false);

        texture = new Texture(Gdx.files.internal("font1.png"), true);
        texture.setFilter(Texture.TextureFilter.MipMapLinearNearest, Texture.TextureFilter.Linear);
        font1 = new BitmapFont(Gdx.files.internal("font1.fnt"), new TextureRegion(texture), false);
        elapsedtimeback = new Texture(Gdx.files.internal("elapsedtimeback.png"));
        workericon = new Texture(Gdx.files.internal("worker.png"));
        workericon.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        elapsedtimeback.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        plusicon = new Icon(0, 0, 36, Gdx.files.internal("plus.png"));
        minusicon = new Icon(0, 0, 36, Gdx.files.internal("minus.png"));

        // font1.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        font1.setUseIntegerPositions(false);
        innerframe = new NinePatch(new Texture(Gdx.files.internal("ninepatchcontent.png")), 20, 20, 20, 34);
        innerframe.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        textback = new NinePatch(new Texture(Gdx.files.internal("textbackninepatch.png")), 7, 7, 7, 7);
        textback.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        resourceicons[0] = new Texture(Gdx.files.internal("icons/resource0" + ".png"));
        resourceicons[0].setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

    }

    @Override
    public void touchPoint(float xx, float yy) {
     //   System.out.println("local point = " + (xx - parentalposition.x) + "/" + (yy - parentalposition.y));

            if (started) {
                System.out.println(item.getTime()+"asd"                );
                done += Math.max(120 / item.getTime(), 0.001f);
            } else {
                started = true;
            }
        System.out.println("done:" + done);

if(plusicon.contains(xx,yy)) {
    if (workercount < maxworker) {
        workercount++;
        gameresource.worker--;
        statcoef[2] = 1f / (1f + workercount);
    }
}else if(minusicon.contains(xx,yy))
{
    if (workercount > 0) {
        workercount--;
        gameresource.worker++;
        statcoef[2] = 1f / (1f + workercount);
    }
}

    }

    @Override
    public void act() {
        float drawpositionx = parentalposition.cpy().add(position).x;
        float drawpositiony = parentalposition.cpy().add(position).y;
        if ( workercount > 0) {
            done += 1f / (item.getTime() * statcoef[2]);
        }
        if (done >= 1f) {
            done -= 1f;
            gameresource.gainXP((int) (item.getTime()/120));
            //new item
        }

    }

    @Override
    public ItemType getItem() {
        return item;
    }

    @Override
    public void drawself(SpriteBatch batch) {

        super.drawself(batch);
        float drawpositionx = parentalposition.cpy().add(position).x;
        float drawpositiony = parentalposition.cpy().add(position).y;
        batch.begin();
        innerframe.draw(batch, drawpositionx + 8, drawpositiony + height / 8, height * 3 / 4, height * 3 / 4);
      //  System.out.println("done:" + done);
        batch.setColor(Color.FOREST.cpy().mul(1f, 1f, 1f, 0.5f + done / 2));
        batch.draw(elapsedtimeback, drawpositionx + 8, drawpositiony + height / 8, 0f, 0f, (int) (done * height * 3 / 4), height * 3 / 4, 1f, 1f, 0f, 0, 0, (int) (done * 150), 152, false, false);
        batch.setColor(Color.WHITE);
        GlyphLayout string = new GlyphLayout();
        font.getData().setScale(0.6f);
        string.setText(font, name);

        font.draw(batch, name+gameresource.level, drawpositionx + height + 20, drawpositiony + height + string.height / 2 - 25);

        // font1.setColor(Color.BLACK);
        font1.getData().setScale(0.6f);
        //      int a=0;
        for (int i = 0, a = 0; i < 3; i++) {

            if (item.stats[i].getStat() > 0) {

                batch.draw(staticons[i], drawpositionx + height + a % 2 * 80, drawpositiony + height - 80 - (a / 2) * 30, 25, 25);
                font1.draw(batch, roundFloat(item.stats[i].getStat() * statcoef[i]) + "", drawpositionx + height + 35 + a % 2 * 80, drawpositiony + height - 63 - (a / 2) * 30);
                a++;
            }
        }


        float iconheight = icon.getHeight();
        float iconwidth = icon.getWidth();
        float scale = scaleTexture(icon, height / 2);


        batch.draw(icon, drawpositionx + 8 + 3 * height / 8 - scale * iconwidth / 2, drawpositiony + height / 2 - scale * iconheight / 2, scale * iconwidth, scale * iconheight);
        font1.getData().setScale(0.6f);
        if (item.getItemid() % 10 != 0) {
            Array<IC> ing = item.getIngredients();
            textback.draw(batch, drawpositionx + 8 + height - 5, drawpositiony + 8, 40 * ing.size, 50);
            for (int i = 0; i < ing.size; i++) {
                ItemType neededitem = ing.get(i).getItem();
                int number = ing.get(i).getNumber();
                int quality = ing.get(i).getQuality();
                Texture temptexture = neededitem.getTexture();
                float scaleicon = scaleTexture(temptexture, 30);
                float heightscale = scaleicon * temptexture.getHeight();
                float widthscale = scaleicon * temptexture.getWidth();

                string.setText(font1, number + "");


                batch.draw(neededitem.getTexture(), drawpositionx + 8 + height + i * 40 + 15 - widthscale / 2, drawpositiony + 33, widthscale, heightscale);
                font1.draw(batch, number + "", drawpositionx + height + 27 - string.width / 2 + i * 40, drawpositiony + 28);

            }
        }

        font1.getData().setScale(0.6f);
        plusicon.setPosition(drawpositionx + 460, drawpositiony + 80);
        minusicon.setPosition(drawpositionx + 390, drawpositiony + 80);
        batch.draw(workericon, drawpositionx + 429, drawpositiony + 120, 25, 25);
        font1.draw(batch, workercount + "", drawpositionx + 439, drawpositiony + 104);
        batch.draw(plusicon.icontexture, plusicon.getPosition().x, plusicon.getPosition().y, plusicon.getSize(), plusicon.getSize());
        batch.draw(minusicon.icontexture, minusicon.getPosition().x, minusicon.getPosition().y, minusicon.getSize(), minusicon.getSize());
        batch.end();
        font1.getData().setScale(1f);
    }

    private float scaleTexture(Texture text, float size) {
        float iconheight = text.getHeight();
        float iconwidth = text.getWidth();
        float scale;
        if (iconheight > iconwidth) {
            scale = size / iconheight;
        } else {
            scale = size / iconwidth;
        }
        return scale;
    }

    @Override
    public void draw(SpriteBatch batch) {

    }

    private float roundFloat(float ft) {
        return (float) (int) (ft * 10) / 10f;
    }

    @Override
    public Content clone() {
        Content temp = new Blueprint(position.x, position.y, width, height, item, touchable);
        return temp;

    }

    @Override
    public void dispose() {
        // icon.dispose();
        item.dispose();
    }

    @Override
    public void resume() {
        item.resume();
    }
}
