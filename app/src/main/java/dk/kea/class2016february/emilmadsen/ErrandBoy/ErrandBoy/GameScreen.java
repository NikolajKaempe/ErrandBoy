package dk.kea.class2016february.emilmadsen.ErrandBoy.ErrandBoy;

import android.graphics.Bitmap;

import dk.kea.class2016february.emilmadsen.ErrandBoy.BitmapAction;
import dk.kea.class2016february.emilmadsen.ErrandBoy.BitmapCoordinates;
import dk.kea.class2016february.emilmadsen.ErrandBoy.Game;
import dk.kea.class2016february.emilmadsen.ErrandBoy.Screen;

/**
 * Created by Nikol_000 on 25-04-2016.
 */
public class GameScreen extends Screen
{
    private MainMenuScreen mainMenuScreen; //TODO to use when gameOver to not make a new MainMenuScreen
    private ErrandBoy errandBoy;
    private Bitmap background;

    public GameScreen(Game game, MainMenuScreen mainMenuScreen)
    {
        super(game);
        this.mainMenuScreen = mainMenuScreen;
        errandBoy = new ErrandBoy(game.loadBitmap("walkingMan.png"));
        background = game.loadBitmap("background.png");
    }

    @Override
    public void update(float deltaTime)
    {
        game.drawBitmap(background, 0, 0);

        System.out.println("************************************************* MovingState = " +errandBoy.movingState );//TODO GetCalled when not touched.. Stack??


        // Move Left
        if (game.getTouchX(0) < 50 && game.getTouchY(0) < 400-50 && game.getTouchY(0) > 50
                && errandBoy.movingState == MovingStates.Still )
        {
            System.out.println("************************************************* User Touches Left");//TODO GetCalled when not touched.. Stack??
            errandBoy.move(MovingStates.Left);
        }
        //Move Right
        if (game.getTouchX(0) > 710-50 && game.getTouchY(0) < 400-50 && game.getTouchX(0) > 50
                && errandBoy.movingState == MovingStates.Still)
        {
            System.out.println("************************************************* User Touches Right");//TODO GetCalled when not touched.. Stack??
            errandBoy.move(MovingStates.Right);
        }
        //Move Up
        if (game.getTouchX(0) < 710-50 && game.getTouchX(0) > 50 && game.getTouchY(0) < 50
                && errandBoy.movingState == MovingStates.Still)
        {
            System.out.println("************************************************* User Touches Up");//TODO GetCalled when not touched.. Stack??
            errandBoy.move(MovingStates.Up);
        }
        //Move Down
        if (game.getTouchX(0) < 710-50 && game.getTouchX(0) > 50 && game.getTouchY(0) > 400-50 &&
                errandBoy.movingState == MovingStates.Still)
        {
            System.out.println("************************************************* User Touches Down");//TODO GetCalled when not touched.. Stack??
            errandBoy.move(MovingStates.Down);
        }
        //TODO
        errandBoy.update(deltaTime);

        collideWalls();

        game.drawBitmap(errandBoy.picture,(int)errandBoy.x,(int)errandBoy.y,
                errandBoy.currentBitmap.getSrcX(),errandBoy.currentBitmap.getSrcY(),
                errandBoy.currentBitmap.getWidth(),errandBoy.currentBitmap.getHeight());
    }

    private void collideWalls()
    {
        if (errandBoy.x + errandBoy.currentBitmap.getWidth() > 710) { errandBoy.x = 710 - errandBoy.currentBitmap.getWidth(); }
        else if (errandBoy.x < 0){errandBoy.x = 0;}
        else if (errandBoy.y + errandBoy.currentBitmap.getHeight() > 375) { errandBoy.y = 375-errandBoy.currentBitmap.getHeight(); }
        else if (errandBoy.y < 25) { errandBoy.y = 25; }
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
