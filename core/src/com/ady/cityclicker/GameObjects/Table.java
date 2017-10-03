package com.ady.cityclicker.GameObjects;

import com.ady.cityclicker.GameHelpers.GameResourceController;
import com.ady.cityclicker.GameHelpers.IC;
import com.ady.cityclicker.GameHelpers.ItemType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.OrderedMap;

import java.util.Iterator;

/**
 * Created by ADY on 15.8.2017.
 */

public class Table {


    private final Rectangle scissors = new Rectangle();
    private final Rectangle clipBounds = new Rectangle();
    private NinePatch npimage;
    private NinePatch npimageicon;
    private OrderedMap<Long, ItemType> items;
    private Array<IC> chest = new Array<IC>();
    private Icon[] icons = new Icon[7];
    //private Array<Content> contents;
    private OrderedMap<Content, Vector2> contents;
    private OrderedMap<Content, Vector2> contentsshow;
    private Vector2 position = new Vector2();
    private int height, width;
    private boolean scrollvertical = true;
    private Vector2 currentposition;
    private Vector2 lastposition = new Vector2(0, 0);
    private float verticalgap = 0f;
    private float horizantalgap = 0f;
    private int verticalalign = 0;
    private int horizontalalign = 0;
    private float currentscroll = 0;
    private float scrollchange = 0;
    private float scrolllimit = 0;
    private int totallength = 0;
    private Vector2 draglastpoint = new Vector2(0, 0);
    private Vector2 dragstartpoint = new Vector2(0, 0);
    private float slide = 1.2f;
    private float slideamount = 0;
    private boolean istouched = false;
    private boolean justtouch = false;
    private GameResourceController gameresources;
    private float scrollchangebank=0;

    public Table() {
        contents = new OrderedMap<Content, Vector2>();
        contentsshow = new OrderedMap<Content, Vector2>();
        npimage = new NinePatch(new Texture(Gdx.files.internal("ninepatch.png")), 8, 8, 8, 8);
        npimageicon = new NinePatch(new Texture(Gdx.files.internal("ninepatchround.png")), 17, 17, 17, 20);

        npimage.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        npimageicon.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
    }

    /**
     * @param x
     * @param y
     * @param width
     * @param height
     */
    public Table(float x, float y, int width, int height, boolean scrollvertical) {
        this();
        this.position.set(x, y);
        this.width = width;
        this.height = height;
        clipBounds.set(x, y, width, height);
        // lastposition.add(position);
        this.scrollvertical = scrollvertical;

        if (scrollvertical) {
            lastposition.add(0, height + verticalgap);
            totallength -= verticalgap;
        } else {
            totallength -= horizantalgap;
        }
        for (int i = 0; i < 7; i++) {
            icons[i] = new Icon(position.x + 5 + i * 65, position.y - 65, 50, Gdx.files.internal("items/" + i + ".png"));
        }
    }

    public void setGamesources(GameResourceController resources) {
        this.gameresources = resources;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(float x, float y) {
        position.set(x, y);
        this.setBound(position.x, position.y, this.width, this.height);
    }

    public void movePosition(float x, float y) {
        position.add(x, y);
        this.setBound(position.x, position.y, this.width, this.height);
    }

    public void setSlide(float slide) {
        this.slide = slide;
    }

    public void setBound(float x, float y, int width, int height) {
        this.position.set(x, y);
        this.width = width;
        this.height = height;
        clipBounds.set(x, y, width, height);
    }

    //DEBUG ONLY
    @Deprecated
    public void scroll(int scrollamo) {
        currentscroll -= scrollamo * 30;
    }

    public void act() {

        if (!istouched) {
            currentscroll += slide * slideamount;

            if (scrolllimit > 0) {
                if (currentscroll > scrolllimit) {
                    currentscroll -= (currentscroll - scrolllimit) / 5f;
                }
                else if(scrolllimit-currentscroll<3) {
                    currentscroll=scrolllimit;
                }
            } else {
                currentscroll -= currentscroll / 5f;
            }
            if (currentscroll < 0) {
                currentscroll += -currentscroll / 5f;
                //   System.out.println("uyarı");
            }
            slideamount /= 1.06f;
        }

        Iterator<Content> iteratoract = contents.keys();
        while (iteratoract.hasNext()) {
            Content temp = iteratoract.next();
            temp.act();
        }
    }

    public void addContent(Content content) {

        //  System.out.println("lp" + lastposition);
        content.setOwner(this);
        content.setGameResource(gameresources);
        if (scrollvertical) {
            lastposition.sub(0, content.height + verticalgap);
            totallength += content.height + verticalgap;
            scrolllimit = totallength - height;

            contents.put(content, lastposition.cpy());
            contentsshow.put(content, lastposition.cpy());
        } else {
            contents.put(content, lastposition.cpy());
            contentsshow.put(content, lastposition.cpy());
            lastposition.add(content.width + horizantalgap, 0);
            totallength += content.width + horizantalgap;
            scrolllimit = totallength - width;
        }

    }

    public void filterContent(Content content) {
        if (scrollvertical) {
            lastposition.sub(0, content.height + verticalgap);
            totallength += content.height + verticalgap;
            scrolllimit = totallength - height;
            contentsshow.put(content, lastposition.cpy());
        } else {
            contentsshow.put(content, lastposition.cpy());
            lastposition.add(content.width + horizantalgap, 0);
            totallength += content.width + horizantalgap;
            scrolllimit = totallength - width;
        }

    }

    private float scaleTexture(Texture text, float size) {
        float iconheight = text.getHeight();
        float iconwidth = text.getWidth();
        float scale = 1f;
        if (iconheight > iconwidth) {
            scale = size / iconheight;
        } else {
            scale = size / iconwidth;
        }
        return scale;
    }

    public Table clone() {
        Table temp = new Table(position.x, position.y, width, height, scrollvertical);
        this.addAllContents(temp);
        return temp;
    }

    public Table cloneRotate(boolean scrollvertical) {

        Table temp = new Table(position.x, position.y, height, width, scrollvertical);
        this.addAllContents(temp);

        return temp;
    }

    public void setItems(OrderedMap<Long, ItemType> items) {
        this.items = items;
    }

    private void addAllContents(Table temp) {
        Iterator<Content> iteratorclone = contents.keys();
        while (iteratorclone.hasNext()) {
            temp.addContent(iteratorclone.next().clone());
        }

        //  System.out.println("size:"+contents.size);
    }

    public void touchDown(float xx, float yy) {
        if (clipBounds.contains(xx, yy)) {
            dragstartpoint.set(xx, yy);
            draglastpoint.set(xx, yy);
            istouched = true;
            justtouch = true;
        }
        for (int i = 0; i < 7; i++) {
            if (icons[i].contains(xx, yy)) {
                icons[i].toggleSelected();
                System.out.println("selected icon"+i);
               //Icon.trueToggle=null;
                addSome();
            }
        }
    }


    public void filterTree(Content itemtotree) {
        contentsshow.clear();
        currentscroll = 0;
        totallength = 0;
        if (scrollvertical) {
            lastposition.set(0, height + verticalgap);
            totallength -= verticalgap;
        } else {
            totallength -= horizantalgap;
        }
        Content itemtosearch=itemtotree;
        //ObjectMap.Keys<Content> itemiterator = contents.keys();
        Array<Content> contentstoput=new Array<Content>();
        itemtotree.allRequired(contentstoput);
        System.out.println("elemansayısı"+contentstoput.size);
        Iterator<Content> iteratortoput= contentstoput.iterator();
        while(iteratortoput.hasNext())
        {
            filterContent(iteratortoput.next());
        }
    }

    public void addSome() {
        contentsshow.clear();
        currentscroll = 0;
        totallength = 0;
        if (scrollvertical) {
            lastposition.set(0, height + verticalgap);
            totallength -= verticalgap;
        } else {
            totallength -= horizantalgap;
        }

        ObjectMap.Keys<Content> itemiterator = contents.keys();

        while (itemiterator.hasNext()) {
            Content temp = itemiterator.next();
            int all = 0;
            for (int i = 0; i < 7; i++) {
                all += icons[i].getSelected() ? 0 : 1;
            }
            if (all == 7) {
                filterContent(temp);
            } else {
                for (int i = 0; i < 7; i++) {
                    if (icons[i].getSelected() && temp.getItem().getItemtype() == i) {
                        filterContent(temp);
                    }
                }
            }
        }
    }

    public void addRequired() {

    }

    public void touchDragged(float xx, float yy) {
        if (clipBounds.contains(xx, yy) && istouched) {
            if (scrollvertical) {
                scrollchange = yy - draglastpoint.y;

            } else {
                scrollchange = draglastpoint.x - xx;
            }
            scrollchangebank+=scrollchange;
            currentscroll += scrollchangebank/5f;
            scrollchangebank-=scrollchangebank/5f;
            draglastpoint.set(xx, yy);
            justtouch = dragstartpoint.dst2(draglastpoint) < 36;

            slideamount = scrollchange;

        } else {
            istouched = false;
        scrollchangebank=0f;

        }


    }

    public void touchUp(float xx, float yy) {

        if (justtouch && clipBounds.contains(xx, yy)) {
            // System.out.println("tıklama123");
            Iterator<Content> iteratortouch = contentsshow.keys();
            boolean touched = false;

            while (iteratortouch.hasNext() && !touched) {
                Content temp = iteratortouch.next();
                if(temp.getGlobalPosition().y<-100&& temp.getGlobalPosition().y>800 )
                    continue;
                int height = temp.height;
                int width = temp.width;
                Vector2 temppos = temp.getGlobalPosition();
                if (xx >= temppos.x && xx <= temppos.x + width && yy >= temppos.y && yy <= temppos.y + height) {
                    // System.out.println("tıklama" + temp.name);
                    temp.touchPoint(xx, yy);
                    touched = true;//dokunduğu anda liste değişme ihtimalinden dolayı çık
                }


            }
        }
        // System.out.println(slideamount + "");
        istouched = false;
        scrollchangebank=0;
    }


    public void draw(SpriteBatch batch, OrthographicCamera camera) {
        batch.begin();
        npimageicon.draw(batch, position.x, position.y - 70, 60, 60);
        // hicon,aicon,cicon,sicon,wicon;

      /*  npimageicon.draw(batch,position.x+65,position.y-70,60,60);
        npimageicon.draw(batch,position.x+130,position.y-70,60,60);
        npimageicon.draw(batch,position.x+195,position.y-70,60,60);
        npimageicon.draw(batch,position.x+260,position.y-70,60,60);
        npimageicon.draw(batch,position.x+325,position.y-70,60,60);*/

        for (int i = 0; i < 7; i++) {
            if (icons[i].getSelected()) {
                batch.setColor(Color.BROWN);
            }
            npimageicon.draw(batch, position.x + i * 65, position.y - 70, 60, 60);
            batch.setColor(Color.WHITE);
            float scale = scaleTexture(icons[i].currenticontexture, 40);
            float iconwidth = scale * icons[i].currenticontexture.getWidth();
            float iconheight = scale * icons[i].currenticontexture.getHeight();

            batch.draw(icons[i].currenticontexture, icons[i].getPosition().x + 5 + (40 - iconwidth) / 2, icons[i].getPosition().y + 5 + (40 - iconheight) / 2, iconwidth, iconheight);

        }

        batch.setColor(Color.BROWN);
        npimage.draw(batch, position.x - 8, position.y - 8, width + 16, height + 16);
        batch.setColor(Color.WHITE);


        batch.end();
        Iterator<Content> iteratordraw = contentsshow.keys();
        while (iteratordraw.hasNext()) {
            Content temp = iteratordraw.next();

            if (scrollvertical) {
                temp.setParentalPosition(contentsshow.get(temp).cpy().add(0, currentscroll).add(position));
            } else {
                temp.setParentalPosition(contentsshow.get(temp).cpy().add(-currentscroll, 0).add(position));
            }

            temp.draw(batch);

            ScissorStack.calculateScissors(camera, batch.getTransformMatrix(), clipBounds, scissors);
            ScissorStack.pushScissors(scissors);
            if (scrollvertical) {
                temp.setParentalPosition(contentsshow.get(temp).cpy().add(0, currentscroll).add(position));
             if(temp.getGlobalPosition().y>-100&& temp.getGlobalPosition().y<800 )
             { temp.drawself(batch);}
            } else {

              //  temp.drawself(batch);
            }
            //  System.out.println(temp.getGlobalPosition()+"");
            batch.flush();
            ScissorStack.popScissors();
        }


    }

    public void dispose() {
        Iterator<Content> iteratordispose = contents.keys();
        while (iteratordispose.hasNext()) {
            iteratordispose.next().dispose();
        }
    }

    public void resume() {
        Iterator<Content> iteratorresume = contents.keys();
        while (iteratorresume.hasNext()) {
            iteratorresume.next().resume();
            //   System.out.println("123123");
        }
    }
}
