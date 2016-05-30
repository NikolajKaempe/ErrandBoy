package dk.kea.class2016february.emilmadsen.ErrandBoy.ErrandBoy;

import android.graphics.Bitmap;

import dk.kea.class2016february.emilmadsen.ErrandBoy.BitmapAction;
import dk.kea.class2016february.emilmadsen.ErrandBoy.BitmapCoordinates;

/**
 * Created by Nikol_000 on 26-04-2016.
 */
public class ErrandBoy
{
    Bitmap picture;
    BitmapAction moveUp,moveDown,moveLeft,moveRight, currentAction;
    BitmapCoordinates currentBitmap;
    MovingStates movingState = MovingStates.Still;
    private int actionCounter = 0;
    float x = 337, y = 325, velocityX = 0, velocityY = 0;
    double timeReset = 0.05, timeCount = timeReset;
    static float VELOCITY = 16.66f;

    public ErrandBoy(Bitmap picture)
    {
        this.picture = picture;
        BitmapCoordinates[] moveDownCords = new BitmapCoordinates[4];
        moveDownCords[0] = new BitmapCoordinates(78,13,36,50);
        moveDownCords[1] = new BitmapCoordinates(15,11,35,50);
        moveDownCords[2] = new BitmapCoordinates(204,12,36,50);
        moveDownCords[3] = new BitmapCoordinates(15,11,35,50);
        moveDown = new BitmapAction(moveDownCords);

        BitmapCoordinates[] moveUpCords = new BitmapCoordinates[4];
        moveUpCords[0] = new BitmapCoordinates(76,205,36,50);
        moveUpCords[1] = new BitmapCoordinates(13,203,36,50);
        moveUpCords[2] = new BitmapCoordinates(206,201,36,50);
        moveUpCords[3] = new BitmapCoordinates(13,203,36,50);
        moveUp = new BitmapAction(moveUpCords);

        BitmapCoordinates[] moveLeftCords = new BitmapCoordinates[4];
        moveLeftCords[0] = new BitmapCoordinates(79,74,36,50);
        moveLeftCords[1] = new BitmapCoordinates(144,75,36,50);
        moveLeftCords[2] = new BitmapCoordinates(207,75,36,50);
        moveLeftCords[3] = new BitmapCoordinates(16,75,36,50);
        moveLeft = new BitmapAction(moveLeftCords);

        BitmapCoordinates[] moveRightCords = new BitmapCoordinates[4];
        moveRightCords[0] = new BitmapCoordinates(79,139,36,50);
        moveRightCords[1] = new BitmapCoordinates(13,139,36,50);
        moveRightCords[2] = new BitmapCoordinates(207,137,36,50);
        moveRightCords[3] = new BitmapCoordinates(13,139,36,50);
        moveRight = new BitmapAction(moveRightCords);

        currentBitmap = moveUp.getBitmap(0);
    }

    public void move(MovingStates way)
    {
        if (way.equals(MovingStates.Left))
        {
            movingState = MovingStates.Left;
            currentAction = moveLeft;
            velocityX = -VELOCITY;
        }
        else if (way.equals(MovingStates.Right))
        {
            movingState = MovingStates.Right;
            currentAction = moveRight;
            velocityX = VELOCITY;
        }
        else if (way.equals(MovingStates.Up))
        {
            movingState = MovingStates.Up;
            currentAction = moveUp;
            velocityY = -VELOCITY ;
        }
        else if (way.equals(MovingStates.Down))
        {
            movingState = MovingStates.Down;
            currentAction = moveDown;
            velocityY = VELOCITY;
        }
        else if (way.equals(MovingStates.Still))
        {
            movingState = MovingStates.Still;
            velocityX = 0;
            velocityY = 0;
        }
        resetCounters();
    }

    public void update(float deltaTime)
    {
        if (!movingState.equals(MovingStates.Still))
        {
            timeCount = timeCount - deltaTime;
            if (timeCount <= 0)
            {
                timeCount = timeReset;
                actionCounter++;
                if (actionCounter >= 4)
                {
                    movingState = MovingStates.Still;
                    velocityX = 0;
                    velocityY = 0;
                    resetCounters();
                    return;

                }

                x = x + velocityX;
                y = y + velocityY;
                currentBitmap = currentAction.getBitmap(actionCounter);

            }
        }
    }

    private void resetCounters()
    {
        actionCounter = 0;
        timeCount = timeReset;
    }
}
