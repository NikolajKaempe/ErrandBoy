package dk.kea.class2016february.emilmadsen.ErrandBoy.ErrandBoy;

import android.graphics.Bitmap;

import dk.kea.class2016february.emilmadsen.ErrandBoy.BitmapAction;
import dk.kea.class2016february.emilmadsen.ErrandBoy.BitmapCoordinates;
import dk.kea.class2016february.emilmadsen.ErrandBoy.Game;
import dk.kea.class2016february.emilmadsen.ErrandBoy.Screen;
import dk.kea.class2016february.emilmadsen.ErrandBoy.Sound;

/**
 * Created by Nikol_000 on 25-04-2016.
 */
public class GameScreen extends Screen
{
    private MainMenuScreen mainMenuScreen; //TODO to use when gameOver to not make a new MainMenuScreen
    private Bitmap background;
    private World world;
    private WorldRenderer renderer;
    private int touchX, touchY;

    public GameScreen(Game game, MainMenuScreen mainMenuScreen)
    {
        super(game);
        world = new World(game);
        renderer = new WorldRenderer(world,game);
        this.mainMenuScreen = mainMenuScreen;
        background = game.loadBitmap("background.png");
    }

    @Override
    public void update(float deltaTime)
    {
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

        world.update(deltaTime);

        game.drawBitmap(background, 0, 0);

        renderer.render();
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
