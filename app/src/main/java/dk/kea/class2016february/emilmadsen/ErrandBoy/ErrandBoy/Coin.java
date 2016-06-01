package dk.kea.class2016february.emilmadsen.ErrandBoy.ErrandBoy;

import android.graphics.Bitmap;

import dk.kea.class2016february.emilmadsen.ErrandBoy.BitmapAction;
import dk.kea.class2016february.emilmadsen.ErrandBoy.BitmapCoordinates;

public class Coin
{
    Bitmap picture;
    BitmapCoordinates currentBitmap;
    BitmapAction rotateCoin;
    int x, y, actionCounter;
    float timeReset = 0.1f,timeCounter = timeReset;

    public Coin(Bitmap picture)
    {
        this.picture = picture;
        BitmapCoordinates[] rotateCoords = new BitmapCoordinates[6];
        rotateCoords[0] = new BitmapCoordinates(0,2,40,40);
        rotateCoords[1] = new BitmapCoordinates(47,2,35,40);
        rotateCoords[2] = new BitmapCoordinates(89,2,26,40);
        rotateCoords[3] = new BitmapCoordinates(121,2,10,40);
        rotateCoords[4] = new BitmapCoordinates(138,2,26,40);
        rotateCoords[5] = new BitmapCoordinates(171,2,35,40);
        rotateCoin = new BitmapAction(rotateCoords);
    }

    public void respawnCoin(int lane)
    {
        actionCounter = 0;
        currentBitmap = rotateCoin.getBitmap(actionCounter);
        timeCounter = timeReset;

        x = (int)(Math.random() * (720-currentBitmap.width));
        if (lane == 1) { y = 30; }
        else { y = 330; }
    }

    public void update(float deltaTime)
    {
        timeCounter = timeCounter-deltaTime;
        if (timeCounter <= 0)
        {
            timeCounter = timeReset;
            actionCounter++;
            if (actionCounter == 6){ actionCounter = 0; }
            currentBitmap = rotateCoin.getBitmap(actionCounter);
        }

    }
}
