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
    private int touchX, touchY;

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
        touchX =-1;
        touchY = -1;

        if (game.isTouchDown(0)){
            touchX = game.getTouchX(0);
            touchY = game.getTouchY(0);

            // Move Left
            if (touchX < 50 && touchY < 400-50 && touchY > 50
                    && errandBoy.movingState == MovingStates.Still )
            {
                System.out.println("************************************************* User Touches Left");//TODO GetCalled when not touched.. Stack??
                errandBoy.move(MovingStates.Left);
            }
            //Move Right
            else if (touchX > 710-50 && touchY < 400-50 && touchX > 50
                    && errandBoy.movingState == MovingStates.Still)
            {
                System.out.println("************************************************* User Touches Right");//TODO GetCalled when not touched.. Stack??
                errandBoy.move(MovingStates.Right);
            }
            //Move Up
            else if (touchX < 710-50 && touchX > 50 && touchY < 50
                    && errandBoy.movingState == MovingStates.Still)
            {
                System.out.println("************************************************* User Touches Up");//TODO GetCalled when not touched.. Stack??
                errandBoy.move(MovingStates.Up);
            }
            //Move Down
            else if (touchX < 710-50 && touchX > 50 && touchY > 400-50 &&
                    errandBoy.movingState == MovingStates.Still)
            {
                System.out.println("************************************************* User Touches Down");//TODO GetCalled when not touched.. Stack??
                errandBoy.move(MovingStates.Down);
            }
        }

        errandBoy.update(deltaTime);
        collideWalls();

        game.drawBitmap(background, 0, 0);

        game.drawBitmap(errandBoy.picture,(int)errandBoy.x,(int)errandBoy.y,
                errandBoy.currentBitmap.getSrcX(),errandBoy.currentBitmap.getSrcY(),
                errandBoy.currentBitmap.getWidth(),errandBoy.currentBitmap.getHeight());
    }

    private void collideWalls()
    {
        //Right Wall
        if (errandBoy.x + errandBoy.currentBitmap.getWidth() > 710)
        {
            errandBoy.x = 709 - errandBoy.currentBitmap.getWidth();
            errandBoy.currentBitmap = errandBoy.moveLeft.getBitmap(1);
        }
        // Left Wall
        else if (errandBoy.x < 0)
        {
            errandBoy.x = 1;
            errandBoy.currentBitmap = errandBoy.moveRight.getBitmap(1);
        }
        // Lower Wall
        else if (errandBoy.y + errandBoy.currentBitmap.getHeight() > 375)
        {
            errandBoy.y = 374-errandBoy.currentBitmap.getHeight();
            errandBoy.currentBitmap = errandBoy.moveUp.getBitmap(1);
        }
        //Upper wall
        else if (errandBoy.y < 25)
        {
            errandBoy.y = 26;
            errandBoy.currentBitmap = errandBoy.moveDown.getBitmap(1);
        }
        else { return; }

        errandBoy.movingState = MovingStates.Still;
        //TODO Play Wall-Collision Sound..

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
