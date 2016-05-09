package dk.kea.class2016february.emilmadsen.ErrandBoy.ErrandBoy;

import android.graphics.Bitmap;

import java.util.List;

import dk.kea.class2016february.emilmadsen.ErrandBoy.BitmapAction;
import dk.kea.class2016february.emilmadsen.ErrandBoy.BitmapCoordinates;
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
    private Bitmap mainmenu, boy;
    private BitmapAction runningBoy;
    private int actionCounter = 0;
    private float resetTime = 0.05f;
    private int xTest = 50;

    public MainMenuScreen(Game game)
    {
        super(game);
        music = game.loadMusic("mainmenu.mp3");
        mainmenu = game.loadBitmap("mainMenuBackground.jpg");
        readySound = game.loadSound("gameStart.mp3");
        boy = game.loadBitmap("backgroundBoy.png");
        gameScreen = new GameScreen(game,this);
        recordScreen = new RecordScreen(game,this);

        BitmapCoordinates[] runningCords = new BitmapCoordinates[6];
        runningCords[0] = new BitmapCoordinates(2,12,43,73);
        runningCords[1] = new BitmapCoordinates(72,15,52,70);
        runningCords[2] = new BitmapCoordinates(145,13,48,72);
        runningCords[3] = new BitmapCoordinates(212,10,44,75);
        runningCords[4] = new BitmapCoordinates(268,10,30,75);
        runningCords[5] = new BitmapCoordinates(315,15,48,70);
        runningBoy = new BitmapAction(runningCords);

        music.setLooping(true);
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

        resetTime = resetTime - deltaTime;
        if(resetTime <= 0)
        {
            actionCounter++;
            resetTime = 0.10f;
            if(actionCounter >= 6)
            {
                actionCounter = 0;
            }
        }
        if(xTest > 700) xTest = 0;
        xTest++;
        BitmapCoordinates bc = runningBoy.getBitmap(actionCounter);
        game.drawBitmap(boy, xTest-(bc.getWidth()/2), 300, bc.getSrcX(), bc.getSrcY(), bc.getWidth(), bc.getHeight());
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
