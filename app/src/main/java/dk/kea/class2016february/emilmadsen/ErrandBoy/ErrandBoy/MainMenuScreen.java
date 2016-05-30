package dk.kea.class2016february.emilmadsen.ErrandBoy.ErrandBoy;

import android.graphics.Bitmap;
import android.graphics.Color;

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
    private Bitmap boy, soundIcon;
    private BitmapAction runningBoy;
    private  ScrollingBackground background;
    private int actionCounter = 0;
    private float resetTime = 0.05f;
    private float textCounter = 0.4f;
    private boolean drawText = true;
    private int xTest = 50;

    public MainMenuScreen(Game game)
    {
        super(game);
        music = game.loadMusic("mainmenu.mp3");
        background = new ScrollingBackground(game.loadBitmap("mainMenuBackground.jpg"));
        readySound = game.loadSound("gameStart.mp3");
        boy = game.loadBitmap("backgroundBoy.png");
        recordScreen = new RecordScreen(game,this);
        soundIcon = game.loadBitmap("soundIcon.png");

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

        List<TouchEvent> events = game.getTouchEvents();
        int stop = events.size();
        for (int i = 0; i < stop; i++)
        {
            if(events.get(i).type == TouchEvent.TouchEventType.Up)
            {
                if (game.getTouchX(0) >= 720-100 && game.getTouchY(0) <= 70)
                {
                    if (game.isMuted())
                    {
                        game.toggleMuted();
                        music.play();
                    }
                    else
                    {
                        game.toggleMuted();
                        music.dispose();
                        music = game.loadMusic("mainmenu.mp3");
                    }
                }
                else
                {
                    if (music.isPlaying()) { music.stop(); }
                    if (!game.isMuted())
                    {
                        readySound.play(0.7f);
                    }
                    gameScreen = new GameScreen(game,this);
                    game.setScreen(gameScreen);
                }
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

        background.move(deltaTime);

        textCounter = textCounter - deltaTime;
        if (textCounter <= 0)
        {
            textCounter = 0.4f;
            if (drawText)
            {
                drawText = false;
            }
            else
            {
                drawText = true;
            }
        }

        BitmapCoordinates cb = background.picture1;
        game.drawBitmap(background.bitmap,0,0,
                cb.srcX,cb.srcY,cb.width,cb.height);
        cb = background.picture2;
        game.drawBitmap(background.bitmap,background.picture1.width,0,
                cb.srcX,cb.srcY,cb.width,cb.height);

        BitmapCoordinates bc = runningBoy.getBitmap(actionCounter);
        game.drawBitmap(boy, xTest - (bc.width / 2), 300, bc.srcX, bc.srcY, bc.width, bc.height);

        if (drawText)
        {
            game.drawText(game.loadFont("outlinePixelFont.ttf"),"TAP TO START",70,50, Color.BLACK,75,200);
        }

        if (game.isMuted())
        {
            game.drawBitmap(soundIcon,720-80,1,0,43,58,43);
        }
        else
        {
            game.drawBitmap(soundIcon,720-80,1,0,0,58,43);

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
