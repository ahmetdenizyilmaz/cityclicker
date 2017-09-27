package com.ady.cityclicker;


//import com.ady.cityclicker.GameHelpers.GameBoard;

import com.ady.cityclicker.GameHelpers.GameResourceController;
import com.ady.cityclicker.GameHelpers.ItemType;
import com.ady.cityclicker.GameHelpers.ItemTypeParser;
import com.ady.cityclicker.GameHelpers.RecipeIngredients;
//import com.ady.cityclicker.GameObjects.Blueprint;
import com.ady.cityclicker.GameObjects.Blueprint;
import com.ady.cityclicker.GameObjects.Content;
import com.ady.cityclicker.GameObjects.Table;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.OrderedMap;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane.ScrollPaneStyle;

import static com.ady.cityclicker.GameHelpers.Constants.*;


public class CityClicker extends Game implements InputProcessor {
    SpriteBatch batch;

    private Viewport viewport;
    private OrthographicCamera camera;
    private int size = 3;
    Table ady;
    Table ady2;
    // GameBoard gameboard0;
    private BitmapFont font;
    private Color color;
    private TextButton.TextButtonStyle style;
    private Viewport view;
    private OrderedMap<Long, ItemType> items;
    private GameResourceController gameresources;
    // private Blueprint a;
    //  private Blueprint b;


    public CityClicker() {
    }

    public void create() {
        batch = new SpriteBatch();
        camera = new OrthographicCamera(540f, 960f);
        camera.setToOrtho(false, 540f, 960f);
        Gdx.input.setInputProcessor(this);
        Gdx.input.setCatchBackKey(true);

        gameresources=new GameResourceController(50);


        ady = new Table(20, 100, 500, 700, true);
        ady.setGamesources(gameresources);
        ItemTypeParser parser = new ItemTypeParser();
        parser.write();
        items = parser.read();
        ObjectMap.Values<ItemType> itemiterator = items.values();
        while (itemiterator.hasNext()) {
            ady.addContent(new Blueprint(0, 0, 500, 200, itemiterator.next() ));
        }
        ady.setItems(items);






    }


    public void render() {

        Gdx.gl.glClearColor(0.5f, 0.3f, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();

        batch.setProjectionMatrix(camera.combined);




        // gameboard0.drawBoard(batch);
        ady.act();


        ady.draw(batch, camera);



    }


    public void show() {

    }


    @Override
    public void resize(int width, int height) {
        //viewport.update(width, height);
    }

    @Override
    public void pause() {
        //   ady.resume();
    }

    @Override
    public void resume() {
        // ady.resume();
    }

    //  @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        batch.dispose();
        ady.dispose();
        // gameboard0.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.BACK) {
            Gdx.app.exit();
            System.out.println("app killed");

        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Vector3 touchPos = new Vector3(screenX, screenY, 0);
        camera.unproject(touchPos);
        ady.touchDown(touchPos.x, touchPos.y);

        System.out.println(touchPos.x + "-----" + touchPos.y);
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        Vector3 touchPos = new Vector3(screenX, screenY, 0);
        camera.unproject(touchPos);
        ady.touchUp(touchPos.x, touchPos.y);
        // ady2.touchUp(touchPos.x,touchPos.y);
        return false;

    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        Vector3 touchPos = new Vector3(screenX, screenY, 0);
        camera.unproject(touchPos);
        ady.touchDragged(touchPos.x, touchPos.y);
        // ady2.touchDragged(touchPos.x,touchPos.y);
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        // ady.scroll(amount);
        //ady2.scroll(amount);
        return false;
    }
}
