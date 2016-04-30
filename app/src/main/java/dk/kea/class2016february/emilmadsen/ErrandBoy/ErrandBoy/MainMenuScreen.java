package dk.kea.class2016february.emilmadsen.ErrandBoy.ErrandBoy;

import android.graphics.Bitmap;

import java.util.List;

import dk.kea.class2016february.emilmadsen.ErrandBoy.Music;
import dk.kea.class2016february.emilmadsen.ErrandBoy.Screen;
import dk.kea.class2016february.emilmadsen.ErrandBoy.Game;
import dk.kea.class2016february.emilmadsen.ErrandBoy.Sound;
import dk.kea.class2016february.emilmadsen.ErrandBoy.TouchEvent;

/**
 * Created by Nikol_000 on 25-04-2016.
 */
public class MainMenuScreen extends Screen
{

    private GameScreen gameScreen;
    private RecordScreen recordScreen;
    private Music music;
    private Sound readySound;
    private Bitmap mainmenu;

    public MainMenuScreen(Game game)
    {
        super(game);
        music = game.loadMusic("mainmenu.mp3");
        mainmenu = game.loadBitmap("mainMenu.png");
        readySound = game.loadSound("gameStart.mp3");
        gameScreen = new GameScreen(game,this);
        recordScreen = new RecordScreen(game,this);
        music.play();
    }

    @Override
    public void update(float deltaTime)
    {

        game.drawBitmap(mainmenu,0,0);

        List<TouchEvent> events = game.getTouchEvents();
        int stop = events.size();
        for (int i = 0; i < stop; i++)
        {
            if(events.get(i).type == TouchEvent.TouchEventType.Up)
            {
                if (music.isPlaying()) { music.stop(); }
                readySound.play(0.7f);
                game.setScreen(gameScreen);
            }
        }

    }

    @Override
    public void pause()
    {

    }

    @Override
    public void resume()
    {

    }

    @Override
    public void dispose()
    {

    }
}
