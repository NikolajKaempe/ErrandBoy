package dk.kea.class2016february.emilmadsen.ErrandBoy.ErrandBoy;

import android.graphics.Bitmap;
import android.graphics.Color;

import java.util.List;

import dk.kea.class2016february.emilmadsen.ErrandBoy.Game;
import dk.kea.class2016february.emilmadsen.ErrandBoy.Music;
import dk.kea.class2016february.emilmadsen.ErrandBoy.Screen;
import dk.kea.class2016february.emilmadsen.ErrandBoy.TouchEvent;

/**
 * Created by Nikol_000 on 25-04-2016.
 */
public class GameScreen extends Screen
{
    private MainMenuScreen mainMenuScreen; //TODO to use when gameOver to not make a new MainMenuScreen
    private Bitmap background, soundIcon;
    private World world;
    private WorldRenderer renderer;
    private Music music;
    private int touchX, touchY, transparency = 5;
    private float levelTimeReset = 0.05f, levelTimeCount = levelTimeReset;
    private boolean drawnNiveau = false, niveauFadeIn = true;

    public GameScreen(Game game, MainMenuScreen mainMenuScreen)
    {
        super(game);
        world = new World(game);
        renderer = new WorldRenderer(world,game);
        this.mainMenuScreen = mainMenuScreen;
        background = game.loadBitmap("background.png");
        soundIcon = game.loadBitmap("soundIcon.png");
        music = game.loadMusic("mainmenu.mp3");
        music.setLooping(true);
    }

    @Override
    public void update(float deltaTime)
    {
        if (!music.isPlaying() && !game.isMuted())
        {
            music.play();
        }

        touchX =-1;
        touchY = -1;

        if (game.isTouchDown(0)){
            touchX = game.getTouchX(0);
            touchY = game.getTouchY(0);

            // Move Left
            if (touchX < 70 && touchY < 400-70 && touchY > 70
                    && world.errandBoy.movingState == MovingStates.Still )
            {
                world.errandBoy.move(MovingStates.Left);
            }
            //Move Right
            else if (touchX > 710-70 && touchY < 400-70 && touchY > 70
                    && world.errandBoy.movingState == MovingStates.Still)
            {
                world.errandBoy.move(MovingStates.Right);
            }
            //Move Up
            else if (touchX < 710-70 && touchX > 70 && touchY < 70
                    && world.errandBoy.movingState == MovingStates.Still)
            {
                world.errandBoy.move(MovingStates.Up);
            }
            //Move Down
            else if (touchX < 710-70 && touchX > 70 && touchY > 400-70 &&
                    world.errandBoy.movingState == MovingStates.Still)
            {
                world.errandBoy.move(MovingStates.Down);
            }
        }

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
            }
        }

        world.update(deltaTime);

        game.drawBitmap(background, 0, 0);

        renderer.render();

        if (world.niveau == 1 && world.niveauChanged ||
                world.niveau == 2 && world.niveauChanged  ||
                world.niveau == 3 && world.niveauChanged )// TODO Change Coins and niveau
        {
            if (!drawnNiveau ){ drawnNiveau = true;}
        }

        if (drawnNiveau)
        {
            levelTimeCount = levelTimeCount-deltaTime;
            if (levelTimeCount <= 0)
            {
                levelTimeCount = levelTimeReset;
                if (niveauFadeIn)
                {
                    transparency = transparency + 10;
                    if (transparency == 255){ niveauFadeIn = false; }
                }else
                {
                    transparency = transparency -10;
                    if (transparency == 5) { drawnNiveau = false;niveauFadeIn = true; }
                }
            }
            game.drawText(game.loadFont("game-font.ttf"),"Niveau " + world.niveau,50,20, Color.BLACK,100,transparency);
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
